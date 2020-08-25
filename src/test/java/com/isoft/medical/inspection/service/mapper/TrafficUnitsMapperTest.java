package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TrafficUnitsMapperTest {

    private TrafficUnitsMapper trafficUnitsMapper;

    @BeforeEach
    public void setUp() {
        trafficUnitsMapper = new TrafficUnitsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(trafficUnitsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(trafficUnitsMapper.fromId(null)).isNull();
    }
}
