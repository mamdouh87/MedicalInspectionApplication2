package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class InspectionTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionTypeDTO.class);
        InspectionTypeDTO inspectionTypeDTO1 = new InspectionTypeDTO();
        inspectionTypeDTO1.setId(1L);
        InspectionTypeDTO inspectionTypeDTO2 = new InspectionTypeDTO();
        assertThat(inspectionTypeDTO1).isNotEqualTo(inspectionTypeDTO2);
        inspectionTypeDTO2.setId(inspectionTypeDTO1.getId());
        assertThat(inspectionTypeDTO1).isEqualTo(inspectionTypeDTO2);
        inspectionTypeDTO2.setId(2L);
        assertThat(inspectionTypeDTO1).isNotEqualTo(inspectionTypeDTO2);
        inspectionTypeDTO1.setId(null);
        assertThat(inspectionTypeDTO1).isNotEqualTo(inspectionTypeDTO2);
    }
}
