package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.PersonsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Persons} and its DTO {@link PersonsDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface PersonsMapper extends EntityMapper<PersonsDTO, Persons> {

    @Mapping(source = "passportIssueCountry.id", target = "passportIssueCountryId")
    PersonsDTO toDto(Persons persons);

    @Mapping(source = "passportIssueCountryId", target = "passportIssueCountry")
    Persons toEntity(PersonsDTO personsDTO);

    default Persons fromId(Long id) {
        if (id == null) {
            return null;
        }
        Persons persons = new Persons();
        persons.setId(id);
        return persons;
    }
}
