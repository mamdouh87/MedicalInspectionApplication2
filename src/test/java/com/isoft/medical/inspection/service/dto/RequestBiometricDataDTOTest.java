package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class RequestBiometricDataDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestBiometricDataDTO.class);
        RequestBiometricDataDTO requestBiometricDataDTO1 = new RequestBiometricDataDTO();
        requestBiometricDataDTO1.setId(1L);
        RequestBiometricDataDTO requestBiometricDataDTO2 = new RequestBiometricDataDTO();
        assertThat(requestBiometricDataDTO1).isNotEqualTo(requestBiometricDataDTO2);
        requestBiometricDataDTO2.setId(requestBiometricDataDTO1.getId());
        assertThat(requestBiometricDataDTO1).isEqualTo(requestBiometricDataDTO2);
        requestBiometricDataDTO2.setId(2L);
        assertThat(requestBiometricDataDTO1).isNotEqualTo(requestBiometricDataDTO2);
        requestBiometricDataDTO1.setId(null);
        assertThat(requestBiometricDataDTO1).isNotEqualTo(requestBiometricDataDTO2);
    }
}
