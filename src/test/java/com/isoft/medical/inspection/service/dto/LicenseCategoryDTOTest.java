package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class LicenseCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LicenseCategoryDTO.class);
        LicenseCategoryDTO licenseCategoryDTO1 = new LicenseCategoryDTO();
        licenseCategoryDTO1.setId(1L);
        LicenseCategoryDTO licenseCategoryDTO2 = new LicenseCategoryDTO();
        assertThat(licenseCategoryDTO1).isNotEqualTo(licenseCategoryDTO2);
        licenseCategoryDTO2.setId(licenseCategoryDTO1.getId());
        assertThat(licenseCategoryDTO1).isEqualTo(licenseCategoryDTO2);
        licenseCategoryDTO2.setId(2L);
        assertThat(licenseCategoryDTO1).isNotEqualTo(licenseCategoryDTO2);
        licenseCategoryDTO1.setId(null);
        assertThat(licenseCategoryDTO1).isNotEqualTo(licenseCategoryDTO2);
    }
}
