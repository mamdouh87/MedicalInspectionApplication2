package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.TrafficUnitsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrafficUnits} and its DTO {@link TrafficUnitsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrafficUnitsMapper extends EntityMapper<TrafficUnitsDTO, TrafficUnits> {



    default TrafficUnits fromId(Long id) {
        if (id == null) {
            return null;
        }
        TrafficUnits trafficUnits = new TrafficUnits();
        trafficUnits.setId(id);
        return trafficUnits;
    }
}
