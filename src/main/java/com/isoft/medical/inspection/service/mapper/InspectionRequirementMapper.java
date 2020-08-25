package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.InspectionRequirementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InspectionRequirement} and its DTO {@link InspectionRequirementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InspectionRequirementMapper extends EntityMapper<InspectionRequirementDTO, InspectionRequirement> {


    @Mapping(target = "requests", ignore = true)
    @Mapping(target = "removeRequests", ignore = true)
    InspectionRequirement toEntity(InspectionRequirementDTO inspectionRequirementDTO);

    default InspectionRequirement fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionRequirement inspectionRequirement = new InspectionRequirement();
        inspectionRequirement.setId(id);
        return inspectionRequirement;
    }
}
