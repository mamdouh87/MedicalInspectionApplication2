package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class MedicalInsepctionRequestsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalInsepctionRequestsDTO.class);
        MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO1 = new MedicalInsepctionRequestsDTO();
        medicalInsepctionRequestsDTO1.setId(1L);
        MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO2 = new MedicalInsepctionRequestsDTO();
        assertThat(medicalInsepctionRequestsDTO1).isNotEqualTo(medicalInsepctionRequestsDTO2);
        medicalInsepctionRequestsDTO2.setId(medicalInsepctionRequestsDTO1.getId());
        assertThat(medicalInsepctionRequestsDTO1).isEqualTo(medicalInsepctionRequestsDTO2);
        medicalInsepctionRequestsDTO2.setId(2L);
        assertThat(medicalInsepctionRequestsDTO1).isNotEqualTo(medicalInsepctionRequestsDTO2);
        medicalInsepctionRequestsDTO1.setId(null);
        assertThat(medicalInsepctionRequestsDTO1).isNotEqualTo(medicalInsepctionRequestsDTO2);
    }
}
