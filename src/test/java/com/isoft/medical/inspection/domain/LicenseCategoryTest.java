package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class LicenseCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LicenseCategory.class);
        LicenseCategory licenseCategory1 = new LicenseCategory();
        licenseCategory1.setId(1L);
        LicenseCategory licenseCategory2 = new LicenseCategory();
        licenseCategory2.setId(licenseCategory1.getId());
        assertThat(licenseCategory1).isEqualTo(licenseCategory2);
        licenseCategory2.setId(2L);
        assertThat(licenseCategory1).isNotEqualTo(licenseCategory2);
        licenseCategory1.setId(null);
        assertThat(licenseCategory1).isNotEqualTo(licenseCategory2);
    }
}
