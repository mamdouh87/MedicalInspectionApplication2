package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class InspectionTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionType.class);
        InspectionType inspectionType1 = new InspectionType();
        inspectionType1.setId(1L);
        InspectionType inspectionType2 = new InspectionType();
        inspectionType2.setId(inspectionType1.getId());
        assertThat(inspectionType1).isEqualTo(inspectionType2);
        inspectionType2.setId(2L);
        assertThat(inspectionType1).isNotEqualTo(inspectionType2);
        inspectionType1.setId(null);
        assertThat(inspectionType1).isNotEqualTo(inspectionType2);
    }
}
