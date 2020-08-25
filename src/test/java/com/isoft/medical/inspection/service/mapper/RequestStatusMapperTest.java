package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestStatusMapperTest {

    private RequestStatusMapper requestStatusMapper;

    @BeforeEach
    public void setUp() {
        requestStatusMapper = new RequestStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(requestStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(requestStatusMapper.fromId(null)).isNull();
    }
}
