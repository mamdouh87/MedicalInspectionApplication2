package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.InspectionTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InspectionType} and its DTO {@link InspectionTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InspectionTypeMapper extends EntityMapper<InspectionTypeDTO, InspectionType> {



    default InspectionType fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionType inspectionType = new InspectionType();
        inspectionType.setId(id);
        return inspectionType;
    }
}
