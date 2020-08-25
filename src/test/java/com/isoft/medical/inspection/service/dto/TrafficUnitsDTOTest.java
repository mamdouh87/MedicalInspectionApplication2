package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class TrafficUnitsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrafficUnitsDTO.class);
        TrafficUnitsDTO trafficUnitsDTO1 = new TrafficUnitsDTO();
        trafficUnitsDTO1.setId(1L);
        TrafficUnitsDTO trafficUnitsDTO2 = new TrafficUnitsDTO();
        assertThat(trafficUnitsDTO1).isNotEqualTo(trafficUnitsDTO2);
        trafficUnitsDTO2.setId(trafficUnitsDTO1.getId());
        assertThat(trafficUnitsDTO1).isEqualTo(trafficUnitsDTO2);
        trafficUnitsDTO2.setId(2L);
        assertThat(trafficUnitsDTO1).isNotEqualTo(trafficUnitsDTO2);
        trafficUnitsDTO1.setId(null);
        assertThat(trafficUnitsDTO1).isNotEqualTo(trafficUnitsDTO2);
    }
}
