package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InspectionResultMapperTest {

    private InspectionResultMapper inspectionResultMapper;

    @BeforeEach
    public void setUp() {
        inspectionResultMapper = new InspectionResultMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionResultMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionResultMapper.fromId(null)).isNull();
    }
}
