package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.RequestBiometricDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestBiometricData} and its DTO {@link RequestBiometricDataDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RequestBiometricDataMapper extends EntityMapper<RequestBiometricDataDTO, RequestBiometricData> {



    default RequestBiometricData fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestBiometricData requestBiometricData = new RequestBiometricData();
        requestBiometricData.setId(id);
        return requestBiometricData;
    }
}
