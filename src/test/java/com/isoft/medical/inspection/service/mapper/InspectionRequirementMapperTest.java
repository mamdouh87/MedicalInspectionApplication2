package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InspectionRequirementMapperTest {

    private InspectionRequirementMapper inspectionRequirementMapper;

    @BeforeEach
    public void setUp() {
        inspectionRequirementMapper = new InspectionRequirementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionRequirementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionRequirementMapper.fromId(null)).isNull();
    }
}
