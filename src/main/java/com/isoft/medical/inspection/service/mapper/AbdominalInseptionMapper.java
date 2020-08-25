package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.AbdominalInseptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AbdominalInseption} and its DTO {@link AbdominalInseptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalConditionMapper.class, InspectionResultMapper.class})
public interface AbdominalInseptionMapper extends EntityMapper<AbdominalInseptionDTO, AbdominalInseption> {

    @Mapping(source = "medicalCondition.id", target = "medicalConditionId")
    @Mapping(source = "inspectionResult.id", target = "inspectionResultId")
    AbdominalInseptionDTO toDto(AbdominalInseption abdominalInseption);

    @Mapping(source = "medicalConditionId", target = "medicalCondition")
    @Mapping(source = "inspectionResultId", target = "inspectionResult")
    AbdominalInseption toEntity(AbdominalInseptionDTO abdominalInseptionDTO);

    default AbdominalInseption fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbdominalInseption abdominalInseption = new AbdominalInseption();
        abdominalInseption.setId(id);
        return abdominalInseption;
    }
}
