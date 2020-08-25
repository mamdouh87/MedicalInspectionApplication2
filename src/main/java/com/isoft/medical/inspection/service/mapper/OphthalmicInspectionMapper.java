package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.OphthalmicInspectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OphthalmicInspection} and its DTO {@link OphthalmicInspectionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalConditionMapper.class, InspectionResultMapper.class})
public interface OphthalmicInspectionMapper extends EntityMapper<OphthalmicInspectionDTO, OphthalmicInspection> {

    @Mapping(source = "medicalCondition.id", target = "medicalConditionId")
    @Mapping(source = "inspectionResult.id", target = "inspectionResultId")
    OphthalmicInspectionDTO toDto(OphthalmicInspection ophthalmicInspection);

    @Mapping(source = "medicalConditionId", target = "medicalCondition")
    @Mapping(source = "inspectionResultId", target = "inspectionResult")
    OphthalmicInspection toEntity(OphthalmicInspectionDTO ophthalmicInspectionDTO);

    default OphthalmicInspection fromId(Long id) {
        if (id == null) {
            return null;
        }
        OphthalmicInspection ophthalmicInspection = new OphthalmicInspection();
        ophthalmicInspection.setId(id);
        return ophthalmicInspection;
    }
}
