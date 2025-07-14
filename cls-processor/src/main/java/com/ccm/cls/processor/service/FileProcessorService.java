package com.ccm.cls.processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class FileProcessorService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void processFile(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String headerLine = reader.readLine(); // Step 1: read field names
            if (headerLine == null || headerLine.isBlank()) {
                log.warn("File is empty or missing headers.");
                return;
            }

            String[] headers = headerLine.split("\\|");

            String line;
            while ((line = reader.readLine()) != null) { // Step 2: stream line-by-line
                if (line.isBlank()) continue; // skip empty lines

                String[] values = line.split("\\|");

                ConcurrentMap<String, String> recordMap = new ConcurrentHashMap<>();
                for (int index = 0; index < headers.length && index < values.length; index++) {
                    recordMap.put(headers[index], values[index]);
                }

                String json = objectMapper.writeValueAsString(recordMap); // Step 3: log as JSON
                log.info(json);
            }

        } catch (IOException e) {
            log.error("Error processing file '{}'", filePath, e);
        }
    }
}

