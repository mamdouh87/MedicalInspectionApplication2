package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.MedicalConditionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalCondition} and its DTO {@link MedicalConditionDTO}.
 */
@Mapper(componentModel = "spring", uses = {InspectionTypeMapper.class})
public interface MedicalConditionMapper extends EntityMapper<MedicalConditionDTO, MedicalCondition> {

    @Mapping(source = "inspectionType.id", target = "inspectionTypeId")
    MedicalConditionDTO toDto(MedicalCondition medicalCondition);

    @Mapping(source = "inspectionTypeId", target = "inspectionType")
    MedicalCondition toEntity(MedicalConditionDTO medicalConditionDTO);

    default MedicalCondition fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalCondition medicalCondition = new MedicalCondition();
        medicalCondition.setId(id);
        return medicalCondition;
    }
}
