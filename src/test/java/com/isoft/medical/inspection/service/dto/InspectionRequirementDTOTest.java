package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class InspectionRequirementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionRequirementDTO.class);
        InspectionRequirementDTO inspectionRequirementDTO1 = new InspectionRequirementDTO();
        inspectionRequirementDTO1.setId(1L);
        InspectionRequirementDTO inspectionRequirementDTO2 = new InspectionRequirementDTO();
        assertThat(inspectionRequirementDTO1).isNotEqualTo(inspectionRequirementDTO2);
        inspectionRequirementDTO2.setId(inspectionRequirementDTO1.getId());
        assertThat(inspectionRequirementDTO1).isEqualTo(inspectionRequirementDTO2);
        inspectionRequirementDTO2.setId(2L);
        assertThat(inspectionRequirementDTO1).isNotEqualTo(inspectionRequirementDTO2);
        inspectionRequirementDTO1.setId(null);
        assertThat(inspectionRequirementDTO1).isNotEqualTo(inspectionRequirementDTO2);
    }
}
