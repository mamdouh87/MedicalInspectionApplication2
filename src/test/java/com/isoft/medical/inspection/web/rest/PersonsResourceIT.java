package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.Persons;
import com.isoft.medical.inspection.repository.PersonsRepository;
import com.isoft.medical.inspection.service.PersonsService;
import com.isoft.medical.inspection.service.dto.PersonsDTO;
import com.isoft.medical.inspection.service.mapper.PersonsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonsResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonsResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NATIONAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_NUMBER = "BBBBBBBBBB";

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private PersonsMapper personsMapper;

    @Autowired
    private PersonsService personsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonsMockMvc;

    private Persons persons;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persons createEntity(EntityManager em) {
        Persons persons = new Persons()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .familyName(DEFAULT_FAMILY_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .birthDate(DEFAULT_BIRTH_DATE)
            .nationalId(DEFAULT_NATIONAL_ID)
            .passportNumber(DEFAULT_PASSPORT_NUMBER);
        return persons;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persons createUpdatedEntity(EntityManager em) {
        Persons persons = new Persons()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .familyName(UPDATED_FAMILY_NAME)
            .fullName(UPDATED_FULL_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .nationalId(UPDATED_NATIONAL_ID)
            .passportNumber(UPDATED_PASSPORT_NUMBER);
        return persons;
    }

    @BeforeEach
    public void initTest() {
        persons = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersons() throws Exception {
        int databaseSizeBeforeCreate = personsRepository.findAll().size();
        // Create the Persons
        PersonsDTO personsDTO = personsMapper.toDto(persons);
        restPersonsMockMvc.perform(post("/api/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personsDTO)))
            .andExpect(status().isCreated());

        // Validate the Persons in the database
        List<Persons> personsList = personsRepository.findAll();
        assertThat(personsList).hasSize(databaseSizeBeforeCreate + 1);
        Persons testPersons = personsList.get(personsList.size() - 1);
        assertThat(testPersons.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPersons.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPersons.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testPersons.getFamilyName()).isEqualTo(DEFAULT_FAMILY_NAME);
        assertThat(testPersons.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testPersons.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testPersons.getNationalId()).isEqualTo(DEFAULT_NATIONAL_ID);
        assertThat(testPersons.getPassportNumber()).isEqualTo(DEFAULT_PASSPORT_NUMBER);
    }

    @Test
    @Transactional
    public void createPersonsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personsRepository.findAll().size();

        // Create the Persons with an existing ID
        persons.setId(1L);
        PersonsDTO personsDTO = personsMapper.toDto(persons);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonsMockMvc.perform(post("/api/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persons in the database
        List<Persons> personsList = personsRepository.findAll();
        assertThat(personsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersons() throws Exception {
        // Initialize the database
        personsRepository.saveAndFlush(persons);

        // Get all the personsList
        restPersonsMockMvc.perform(get("/api/persons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persons.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].familyName").value(hasItem(DEFAULT_FAMILY_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].nationalId").value(hasItem(DEFAULT_NATIONAL_ID)))
            .andExpect(jsonPath("$.[*].passportNumber").value(hasItem(DEFAULT_PASSPORT_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getPersons() throws Exception {
        // Initialize the database
        personsRepository.saveAndFlush(persons);

        // Get the persons
        restPersonsMockMvc.perform(get("/api/persons/{id}", persons.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(persons.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.familyName").value(DEFAULT_FAMILY_NAME))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.nationalId").value(DEFAULT_NATIONAL_ID))
            .andExpect(jsonPath("$.passportNumber").value(DEFAULT_PASSPORT_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingPersons() throws Exception {
        // Get the persons
        restPersonsMockMvc.perform(get("/api/persons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersons() throws Exception {
        // Initialize the database
        personsRepository.saveAndFlush(persons);

        int databaseSizeBeforeUpdate = personsRepository.findAll().size();

        // Update the persons
        Persons updatedPersons = personsRepository.findById(persons.getId()).get();
        // Disconnect from session so that the updates on updatedPersons are not directly saved in db
        em.detach(updatedPersons);
        updatedPersons
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .familyName(UPDATED_FAMILY_NAME)
            .fullName(UPDATED_FULL_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .nationalId(UPDATED_NATIONAL_ID)
            .passportNumber(UPDATED_PASSPORT_NUMBER);
        PersonsDTO personsDTO = personsMapper.toDto(updatedPersons);

        restPersonsMockMvc.perform(put("/api/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personsDTO)))
            .andExpect(status().isOk());

        // Validate the Persons in the database
        List<Persons> personsList = personsRepository.findAll();
        assertThat(personsList).hasSize(databaseSizeBeforeUpdate);
        Persons testPersons = personsList.get(personsList.size() - 1);
        assertThat(testPersons.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPersons.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPersons.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testPersons.getFamilyName()).isEqualTo(UPDATED_FAMILY_NAME);
        assertThat(testPersons.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testPersons.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testPersons.getNationalId()).isEqualTo(UPDATED_NATIONAL_ID);
        assertThat(testPersons.getPassportNumber()).isEqualTo(UPDATED_PASSPORT_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingPersons() throws Exception {
        int databaseSizeBeforeUpdate = personsRepository.findAll().size();

        // Create the Persons
        PersonsDTO personsDTO = personsMapper.toDto(persons);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonsMockMvc.perform(put("/api/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persons in the database
        List<Persons> personsList = personsRepository.findAll();
        assertThat(personsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersons() throws Exception {
        // Initialize the database
        personsRepository.saveAndFlush(persons);

        int databaseSizeBeforeDelete = personsRepository.findAll().size();

        // Delete the persons
        restPersonsMockMvc.perform(delete("/api/persons/{id}", persons.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Persons> personsList = personsRepository.findAll();
        assertThat(personsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
