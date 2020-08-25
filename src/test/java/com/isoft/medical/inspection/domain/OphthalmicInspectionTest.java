package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class OphthalmicInspectionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OphthalmicInspection.class);
        OphthalmicInspection ophthalmicInspection1 = new OphthalmicInspection();
        ophthalmicInspection1.setId(1L);
        OphthalmicInspection ophthalmicInspection2 = new OphthalmicInspection();
        ophthalmicInspection2.setId(ophthalmicInspection1.getId());
        assertThat(ophthalmicInspection1).isEqualTo(ophthalmicInspection2);
        ophthalmicInspection2.setId(2L);
        assertThat(ophthalmicInspection1).isNotEqualTo(ophthalmicInspection2);
        ophthalmicInspection1.setId(null);
        assertThat(ophthalmicInspection1).isNotEqualTo(ophthalmicInspection2);
    }
}
