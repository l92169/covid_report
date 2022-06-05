package main.java.edu.au.covidreporter.service;

import edu.au.covidreporter.dto.ReportDto;
import edu.au.covidreporter.model.ReportEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReportUtils {
    static ReportDto toDto(ReportEntity entity) {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(entity.getId());
        reportDto.setFileName(entity.getFile_name());
        return reportDto;
    }

    static List<ReportDto> toDtoList(Collection<ReportEntity> list) {
        return list.stream()
                .map(ReportUtils::toDto)
                .collect(Collectors.toList());
    }
}
