package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OphthalmicInspectionMapperTest {

    private OphthalmicInspectionMapper ophthalmicInspectionMapper;

    @BeforeEach
    public void setUp() {
        ophthalmicInspectionMapper = new OphthalmicInspectionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ophthalmicInspectionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ophthalmicInspectionMapper.fromId(null)).isNull();
    }
}
