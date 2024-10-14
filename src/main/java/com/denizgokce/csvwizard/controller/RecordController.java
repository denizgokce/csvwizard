package com.denizgokce.csvwizard.controller;

import com.denizgokce.csvwizard.dto.RecordDTO;
import com.denizgokce.csvwizard.dto.generic.Result;
import com.denizgokce.csvwizard.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    private RecordService recordService;

    @PostMapping("/upload")
    public ResponseEntity<Result<RecordDTO>> uploadData(@RequestParam("file") MultipartFile file) {
        Result<RecordDTO> result = new Result<>();

        if (file.isEmpty()) {
            result.setSucceeded(false);
            result.getResultMessage().add("File is empty.");
            logger.warn("Upload failed: File is empty.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        if (!file.getContentType().equals("text/csv")) {
            result.setSucceeded(false);
            result.getResultMessage().add("File type is not CSV.");
            logger.warn("Upload failed: File type is not CSV.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        try {
            result = recordService.uploadData(file);
            logger.info("File uploaded successfully.");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setSucceeded(false);
            result.getResultMessage().add("Error processing file: " + e.getMessage());
            logger.error("Error processing file", e);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public Result<RecordDTO> getAllRecords() {
        logger.info("Fetching all records");
        return recordService.getAllRecords();
    }

    @GetMapping("/{code}")
    public Result<RecordDTO> getRecordByCode(@PathVariable String code) {
        logger.info("Fetching record with code: {}", code);
        return recordService.getRecordByCode(code);
    }

    @DeleteMapping
    public Result<Boolean> deleteAllRecords() {
        logger.info("Deleting all records");
        return recordService.deleteAllRecords();
    }
}