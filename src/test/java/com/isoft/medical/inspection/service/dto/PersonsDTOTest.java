package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class PersonsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonsDTO.class);
        PersonsDTO personsDTO1 = new PersonsDTO();
        personsDTO1.setId(1L);
        PersonsDTO personsDTO2 = new PersonsDTO();
        assertThat(personsDTO1).isNotEqualTo(personsDTO2);
        personsDTO2.setId(personsDTO1.getId());
        assertThat(personsDTO1).isEqualTo(personsDTO2);
        personsDTO2.setId(2L);
        assertThat(personsDTO1).isNotEqualTo(personsDTO2);
        personsDTO1.setId(null);
        assertThat(personsDTO1).isNotEqualTo(personsDTO2);
    }
}
