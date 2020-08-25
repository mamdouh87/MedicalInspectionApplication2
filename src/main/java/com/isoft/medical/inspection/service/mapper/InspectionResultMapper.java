package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.InspectionResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InspectionResult} and its DTO {@link InspectionResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InspectionResultMapper extends EntityMapper<InspectionResultDTO, InspectionResult> {



    default InspectionResult fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionResult inspectionResult = new InspectionResult();
        inspectionResult.setId(id);
        return inspectionResult;
    }
}
