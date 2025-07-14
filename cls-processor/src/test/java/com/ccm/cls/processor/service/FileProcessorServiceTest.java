package com.ccm.cls.processor.service;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileProcessorServiceTest {

    private final FileProcessorService fileProcessorService = new FileProcessorService();

    @Test
    void testProcessFile_validInput_shouldLogJsonPerLine() throws Exception{
        // Load test file from resources
        URL resource = getClass().getClassLoader().getResource("test-input.txt");
        assertNotNull(resource, "Test input file not found");

        String filePath = Paths.get(resource.toURI()).toString();

        // Call the processor
        fileProcessorService.processFile(filePath);

        // No assertion here – just verify no exceptions & check logs
    }
}
