package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LicenseCategoryMapperTest {

    private LicenseCategoryMapper licenseCategoryMapper;

    @BeforeEach
    public void setUp() {
        licenseCategoryMapper = new LicenseCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(licenseCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(licenseCategoryMapper.fromId(null)).isNull();
    }
}
