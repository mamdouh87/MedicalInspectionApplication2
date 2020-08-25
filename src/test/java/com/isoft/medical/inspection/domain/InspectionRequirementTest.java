package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class InspectionRequirementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionRequirement.class);
        InspectionRequirement inspectionRequirement1 = new InspectionRequirement();
        inspectionRequirement1.setId(1L);
        InspectionRequirement inspectionRequirement2 = new InspectionRequirement();
        inspectionRequirement2.setId(inspectionRequirement1.getId());
        assertThat(inspectionRequirement1).isEqualTo(inspectionRequirement2);
        inspectionRequirement2.setId(2L);
        assertThat(inspectionRequirement1).isNotEqualTo(inspectionRequirement2);
        inspectionRequirement1.setId(null);
        assertThat(inspectionRequirement1).isNotEqualTo(inspectionRequirement2);
    }
}
