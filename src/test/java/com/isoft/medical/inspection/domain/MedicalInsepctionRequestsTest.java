package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class MedicalInsepctionRequestsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalInsepctionRequests.class);
        MedicalInsepctionRequests medicalInsepctionRequests1 = new MedicalInsepctionRequests();
        medicalInsepctionRequests1.setId(1L);
        MedicalInsepctionRequests medicalInsepctionRequests2 = new MedicalInsepctionRequests();
        medicalInsepctionRequests2.setId(medicalInsepctionRequests1.getId());
        assertThat(medicalInsepctionRequests1).isEqualTo(medicalInsepctionRequests2);
        medicalInsepctionRequests2.setId(2L);
        assertThat(medicalInsepctionRequests1).isNotEqualTo(medicalInsepctionRequests2);
        medicalInsepctionRequests1.setId(null);
        assertThat(medicalInsepctionRequests1).isNotEqualTo(medicalInsepctionRequests2);
    }
}
