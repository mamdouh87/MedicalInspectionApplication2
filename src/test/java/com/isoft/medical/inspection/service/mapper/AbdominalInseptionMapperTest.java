package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AbdominalInseptionMapperTest {

    private AbdominalInseptionMapper abdominalInseptionMapper;

    @BeforeEach
    public void setUp() {
        abdominalInseptionMapper = new AbdominalInseptionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(abdominalInseptionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(abdominalInseptionMapper.fromId(null)).isNull();
    }
}
