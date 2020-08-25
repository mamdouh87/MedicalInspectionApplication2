package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class InspectionResultTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionResult.class);
        InspectionResult inspectionResult1 = new InspectionResult();
        inspectionResult1.setId(1L);
        InspectionResult inspectionResult2 = new InspectionResult();
        inspectionResult2.setId(inspectionResult1.getId());
        assertThat(inspectionResult1).isEqualTo(inspectionResult2);
        inspectionResult2.setId(2L);
        assertThat(inspectionResult1).isNotEqualTo(inspectionResult2);
        inspectionResult1.setId(null);
        assertThat(inspectionResult1).isNotEqualTo(inspectionResult2);
    }
}
