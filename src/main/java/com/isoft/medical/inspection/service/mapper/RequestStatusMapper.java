package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.RequestStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestStatus} and its DTO {@link RequestStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RequestStatusMapper extends EntityMapper<RequestStatusDTO, RequestStatus> {



    default RequestStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setId(id);
        return requestStatus;
    }
}
