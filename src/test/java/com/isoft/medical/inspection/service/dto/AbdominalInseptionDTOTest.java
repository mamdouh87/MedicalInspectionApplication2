package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class AbdominalInseptionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbdominalInseptionDTO.class);
        AbdominalInseptionDTO abdominalInseptionDTO1 = new AbdominalInseptionDTO();
        abdominalInseptionDTO1.setId(1L);
        AbdominalInseptionDTO abdominalInseptionDTO2 = new AbdominalInseptionDTO();
        assertThat(abdominalInseptionDTO1).isNotEqualTo(abdominalInseptionDTO2);
        abdominalInseptionDTO2.setId(abdominalInseptionDTO1.getId());
        assertThat(abdominalInseptionDTO1).isEqualTo(abdominalInseptionDTO2);
        abdominalInseptionDTO2.setId(2L);
        assertThat(abdominalInseptionDTO1).isNotEqualTo(abdominalInseptionDTO2);
        abdominalInseptionDTO1.setId(null);
        assertThat(abdominalInseptionDTO1).isNotEqualTo(abdominalInseptionDTO2);
    }
}
