package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class RequestBiometricDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestBiometricData.class);
        RequestBiometricData requestBiometricData1 = new RequestBiometricData();
        requestBiometricData1.setId(1L);
        RequestBiometricData requestBiometricData2 = new RequestBiometricData();
        requestBiometricData2.setId(requestBiometricData1.getId());
        assertThat(requestBiometricData1).isEqualTo(requestBiometricData2);
        requestBiometricData2.setId(2L);
        assertThat(requestBiometricData1).isNotEqualTo(requestBiometricData2);
        requestBiometricData1.setId(null);
        assertThat(requestBiometricData1).isNotEqualTo(requestBiometricData2);
    }
}
