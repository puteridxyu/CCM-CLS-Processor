package com.ccm.cls.processor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class FileProcessorService {

    public String processUploadedFile(MultipartFile file) {
        try {
            // Rename using timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String originalFilename = file.getOriginalFilename();
            String newFilename = originalFilename != null
                    ? originalFilename.replace(".txt", "_" + timestamp + ".txt")
                    : "uploaded_" + timestamp + ".txt";

            Path tempFile = Files.createTempFile("upload_", newFilename);
            file.transferTo(tempFile.toFile());

            processFile(tempFile.toFile());

            return "File successfully uploaded and processed: " + originalFilename + "\n\n";

        } catch (IOException e) {
            log.error("Failed to process uploaded file", e);
            return "Error processing file";
        }
    }

    public void processFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                log.warn("Empty file or missing header.");
                return;
            }

            String[] headers = headerLine.split("\\|");
            String line;
            
            // Read line-by-line
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                Map<String, String> map = new ConcurrentHashMap<>();

                // Map header to values using ConcurrentMap 
                for (int x = 0; x < headers.length && x < values.length; x++) {
                    map.put(headers[x], values[x]);
                }

                // Log each line as JSON
                log.info("{}", new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(map));
            }
        } catch (IOException e) {
            log.error("Error processing file", e);
        }
    }
}
