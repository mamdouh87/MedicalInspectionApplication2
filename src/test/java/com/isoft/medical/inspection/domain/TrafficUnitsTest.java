package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class TrafficUnitsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrafficUnits.class);
        TrafficUnits trafficUnits1 = new TrafficUnits();
        trafficUnits1.setId(1L);
        TrafficUnits trafficUnits2 = new TrafficUnits();
        trafficUnits2.setId(trafficUnits1.getId());
        assertThat(trafficUnits1).isEqualTo(trafficUnits2);
        trafficUnits2.setId(2L);
        assertThat(trafficUnits1).isNotEqualTo(trafficUnits2);
        trafficUnits1.setId(null);
        assertThat(trafficUnits1).isNotEqualTo(trafficUnits2);
    }
}
