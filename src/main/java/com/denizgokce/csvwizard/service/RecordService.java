package com.denizgokce.csvwizard.service;

import com.denizgokce.csvwizard.dto.RecordDTO;
import com.denizgokce.csvwizard.dto.generic.Result;
import com.denizgokce.csvwizard.mapper.RecordMapper;
import com.denizgokce.csvwizard.model.Record;
import com.denizgokce.csvwizard.repository.RecordRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Result<RecordDTO> uploadData(MultipartFile file) throws Exception {
        List<String> errorLines = new ArrayList<>();
        List<Record> successfulRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(reader)
                    .withType(Record.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Record> records = csvToBean.parse();
            for (Record record : records) {
                try {
                    // Validate the record if necessary
                    successfulRecords.add(record);
                } catch (Exception e) {
                    errorLines.add("Error processing record: " + record + " - " + e.getMessage());
                }
            }
            recordRepository.saveAll(successfulRecords);
        } catch (Exception e) {
            errorLines.add("Error reading CSV file: " + e.getMessage());
        }

        Result<RecordDTO> result = new Result<>();
        result.setSucceeded(errorLines.isEmpty());
        result.setTransactionResultList(successfulRecords.stream()
                .map(RecordMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));
        result.setResultMessage(errorLines);

        return result;
    }

    public Result<RecordDTO> getAllRecords() {
        Result<RecordDTO> result = new Result<>();
        try {
            List<Record> records = recordRepository.findAll();
            result.setTransactionResultList(records.stream()
                    .map(RecordMapper.INSTANCE::toDTO)
                    .collect(Collectors.toList()));
            result.setSucceeded(true);
            result.getResultMessage().add("Records retrieved successfully.");
        } catch (Exception e) {
            result.setSucceeded(false);
            result.getResultMessage().add("Error retrieving records: " + e.getMessage());
        }
        return result;
    }

    public Result<RecordDTO> getRecordByCode(String code) {
        Result<RecordDTO> result = new Result<>();
        try {
            Record record = recordRepository.findById(code).orElse(null);
            if (record != null) {
                result.setTransactionResult(RecordMapper.INSTANCE.toDTO(record));
                result.setSucceeded(true);
                result.getResultMessage().add("Record retrieved successfully.");
            } else {
                result.setSucceeded(false);
                result.getResultMessage().add("Record not found.");
            }
        } catch (Exception e) {
            result.setSucceeded(false);
            result.getResultMessage().add("Error retrieving record: " + e.getMessage());
        }
        return result;
    }

    public Result<Boolean> deleteAllRecords() {
        Result<Boolean> result = new Result<>();
        try {
            recordRepository.deleteAll();
            result.setTransactionResult(true);
            result.setSucceeded(true);
            result.getResultMessage().add("All records deleted successfully.");
        } catch (Exception e) {
            result.setSucceeded(false);
            result.setTransactionResult(false);
            result.getResultMessage().add("Error deleting records: " + e.getMessage());
        }
        return result;
    }
}