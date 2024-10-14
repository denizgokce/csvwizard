package com.denizgokce.csvwizard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denizgokce.csvwizard.model.Record;

public interface RecordRepository extends JpaRepository<Record, String> {
}