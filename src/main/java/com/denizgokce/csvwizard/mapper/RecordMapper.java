package com.denizgokce.csvwizard.mapper;

import com.denizgokce.csvwizard.dto.RecordDTO;
import com.denizgokce.csvwizard.model.Record;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecordMapper {

    RecordMapper INSTANCE = Mappers.getMapper(RecordMapper.class);

    RecordDTO toDTO(Record record);

    Record toEntity(RecordDTO recordDTO);
}