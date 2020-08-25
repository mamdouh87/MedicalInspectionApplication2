package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class OphthalmicInspectionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OphthalmicInspectionDTO.class);
        OphthalmicInspectionDTO ophthalmicInspectionDTO1 = new OphthalmicInspectionDTO();
        ophthalmicInspectionDTO1.setId(1L);
        OphthalmicInspectionDTO ophthalmicInspectionDTO2 = new OphthalmicInspectionDTO();
        assertThat(ophthalmicInspectionDTO1).isNotEqualTo(ophthalmicInspectionDTO2);
        ophthalmicInspectionDTO2.setId(ophthalmicInspectionDTO1.getId());
        assertThat(ophthalmicInspectionDTO1).isEqualTo(ophthalmicInspectionDTO2);
        ophthalmicInspectionDTO2.setId(2L);
        assertThat(ophthalmicInspectionDTO1).isNotEqualTo(ophthalmicInspectionDTO2);
        ophthalmicInspectionDTO1.setId(null);
        assertThat(ophthalmicInspectionDTO1).isNotEqualTo(ophthalmicInspectionDTO2);
    }
}
