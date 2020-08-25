package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InspectionTypeMapperTest {

    private InspectionTypeMapper inspectionTypeMapper;

    @BeforeEach
    public void setUp() {
        inspectionTypeMapper = new InspectionTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionTypeMapper.fromId(null)).isNull();
    }
}
