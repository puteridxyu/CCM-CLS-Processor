package com.ccm.cls.processor.controller;

import com.ccm.cls.processor.service.FileProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class UploadController {

    private final FileProcessorService fileProcessorService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String result = fileProcessorService.processUploadedFile(file);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        StringBuilder result = new StringBuilder();

        for (MultipartFile file : files) {
            String response = fileProcessorService.processUploadedFile(file);
            result.append(response).append("\n");
        }

        return ResponseEntity.ok(result.toString());
    }

}
