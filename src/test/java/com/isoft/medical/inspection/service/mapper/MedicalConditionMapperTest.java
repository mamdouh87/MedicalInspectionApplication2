package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalConditionMapperTest {

    private MedicalConditionMapper medicalConditionMapper;

    @BeforeEach
    public void setUp() {
        medicalConditionMapper = new MedicalConditionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalConditionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalConditionMapper.fromId(null)).isNull();
    }
}
