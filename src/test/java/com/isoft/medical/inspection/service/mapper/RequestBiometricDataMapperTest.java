package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestBiometricDataMapperTest {

    private RequestBiometricDataMapper requestBiometricDataMapper;

    @BeforeEach
    public void setUp() {
        requestBiometricDataMapper = new RequestBiometricDataMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(requestBiometricDataMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(requestBiometricDataMapper.fromId(null)).isNull();
    }
}
