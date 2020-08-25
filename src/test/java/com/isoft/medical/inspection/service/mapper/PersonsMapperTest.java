package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonsMapperTest {

    private PersonsMapper personsMapper;

    @BeforeEach
    public void setUp() {
        personsMapper = new PersonsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(personsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personsMapper.fromId(null)).isNull();
    }
}
