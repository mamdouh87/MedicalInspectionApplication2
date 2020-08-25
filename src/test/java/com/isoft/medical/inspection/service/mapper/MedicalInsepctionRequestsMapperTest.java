package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalInsepctionRequestsMapperTest {

    private MedicalInsepctionRequestsMapper medicalInsepctionRequestsMapper;

    @BeforeEach
    public void setUp() {
        medicalInsepctionRequestsMapper = new MedicalInsepctionRequestsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalInsepctionRequestsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalInsepctionRequestsMapper.fromId(null)).isNull();
    }
}
