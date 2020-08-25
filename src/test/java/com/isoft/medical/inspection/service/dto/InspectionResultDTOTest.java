package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class InspectionResultDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionResultDTO.class);
        InspectionResultDTO inspectionResultDTO1 = new InspectionResultDTO();
        inspectionResultDTO1.setId(1L);
        InspectionResultDTO inspectionResultDTO2 = new InspectionResultDTO();
        assertThat(inspectionResultDTO1).isNotEqualTo(inspectionResultDTO2);
        inspectionResultDTO2.setId(inspectionResultDTO1.getId());
        assertThat(inspectionResultDTO1).isEqualTo(inspectionResultDTO2);
        inspectionResultDTO2.setId(2L);
        assertThat(inspectionResultDTO1).isNotEqualTo(inspectionResultDTO2);
        inspectionResultDTO1.setId(null);
        assertThat(inspectionResultDTO1).isNotEqualTo(inspectionResultDTO2);
    }
}
