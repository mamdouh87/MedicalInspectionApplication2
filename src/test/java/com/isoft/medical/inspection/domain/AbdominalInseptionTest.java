package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class AbdominalInseptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbdominalInseption.class);
        AbdominalInseption abdominalInseption1 = new AbdominalInseption();
        abdominalInseption1.setId(1L);
        AbdominalInseption abdominalInseption2 = new AbdominalInseption();
        abdominalInseption2.setId(abdominalInseption1.getId());
        assertThat(abdominalInseption1).isEqualTo(abdominalInseption2);
        abdominalInseption2.setId(2L);
        assertThat(abdominalInseption1).isNotEqualTo(abdominalInseption2);
        abdominalInseption1.setId(null);
        assertThat(abdominalInseption1).isNotEqualTo(abdominalInseption2);
    }
}
