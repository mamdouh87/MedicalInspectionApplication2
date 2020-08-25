package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.AbdominalInseption;
import com.isoft.medical.inspection.repository.AbdominalInseptionRepository;
import com.isoft.medical.inspection.service.AbdominalInseptionService;
import com.isoft.medical.inspection.service.dto.AbdominalInseptionDTO;
import com.isoft.medical.inspection.service.mapper.AbdominalInseptionMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AbdominalInseptionResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AbdominalInseptionResourceIT {

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private AbdominalInseptionRepository abdominalInseptionRepository;

    @Autowired
    private AbdominalInseptionMapper abdominalInseptionMapper;

    @Autowired
    private AbdominalInseptionService abdominalInseptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAbdominalInseptionMockMvc;

    private AbdominalInseption abdominalInseption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AbdominalInseption createEntity(EntityManager em) {
        AbdominalInseption abdominalInseption = new AbdominalInseption()
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .doctorComments(DEFAULT_DOCTOR_COMMENTS);
        return abdominalInseption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AbdominalInseption createUpdatedEntity(EntityManager em) {
        AbdominalInseption abdominalInseption = new AbdominalInseption()
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .doctorComments(UPDATED_DOCTOR_COMMENTS);
        return abdominalInseption;
    }

    @BeforeEach
    public void initTest() {
        abdominalInseption = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbdominalInseption() throws Exception {
        int databaseSizeBeforeCreate = abdominalInseptionRepository.findAll().size();
        // Create the AbdominalInseption
        AbdominalInseptionDTO abdominalInseptionDTO = abdominalInseptionMapper.toDto(abdominalInseption);
        restAbdominalInseptionMockMvc.perform(post("/api/abdominal-inseptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abdominalInseptionDTO)))
            .andExpect(status().isCreated());

        // Validate the AbdominalInseption in the database
        List<AbdominalInseption> abdominalInseptionList = abdominalInseptionRepository.findAll();
        assertThat(abdominalInseptionList).hasSize(databaseSizeBeforeCreate + 1);
        AbdominalInseption testAbdominalInseption = abdominalInseptionList.get(abdominalInseptionList.size() - 1);
        assertThat(testAbdominalInseption.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testAbdominalInseption.getDoctorComments()).isEqualTo(DEFAULT_DOCTOR_COMMENTS);
    }

    @Test
    @Transactional
    public void createAbdominalInseptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abdominalInseptionRepository.findAll().size();

        // Create the AbdominalInseption with an existing ID
        abdominalInseption.setId(1L);
        AbdominalInseptionDTO abdominalInseptionDTO = abdominalInseptionMapper.toDto(abdominalInseption);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbdominalInseptionMockMvc.perform(post("/api/abdominal-inseptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abdominalInseptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AbdominalInseption in the database
        List<AbdominalInseption> abdominalInseptionList = abdominalInseptionRepository.findAll();
        assertThat(abdominalInseptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAbdominalInseptions() throws Exception {
        // Initialize the database
        abdominalInseptionRepository.saveAndFlush(abdominalInseption);

        // Get all the abdominalInseptionList
        restAbdominalInseptionMockMvc.perform(get("/api/abdominal-inseptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abdominalInseption.getId().intValue())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP)))
            .andExpect(jsonPath("$.[*].doctorComments").value(hasItem(DEFAULT_DOCTOR_COMMENTS)));
    }
    
    @Test
    @Transactional
    public void getAbdominalInseption() throws Exception {
        // Initialize the database
        abdominalInseptionRepository.saveAndFlush(abdominalInseption);

        // Get the abdominalInseption
        restAbdominalInseptionMockMvc.perform(get("/api/abdominal-inseptions/{id}", abdominalInseption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(abdominalInseption.getId().intValue()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP))
            .andExpect(jsonPath("$.doctorComments").value(DEFAULT_DOCTOR_COMMENTS));
    }
    @Test
    @Transactional
    public void getNonExistingAbdominalInseption() throws Exception {
        // Get the abdominalInseption
        restAbdominalInseptionMockMvc.perform(get("/api/abdominal-inseptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbdominalInseption() throws Exception {
        // Initialize the database
        abdominalInseptionRepository.saveAndFlush(abdominalInseption);

        int databaseSizeBeforeUpdate = abdominalInseptionRepository.findAll().size();

        // Update the abdominalInseption
        AbdominalInseption updatedAbdominalInseption = abdominalInseptionRepository.findById(abdominalInseption.getId()).get();
        // Disconnect from session so that the updates on updatedAbdominalInseption are not directly saved in db
        em.detach(updatedAbdominalInseption);
        updatedAbdominalInseption
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .doctorComments(UPDATED_DOCTOR_COMMENTS);
        AbdominalInseptionDTO abdominalInseptionDTO = abdominalInseptionMapper.toDto(updatedAbdominalInseption);

        restAbdominalInseptionMockMvc.perform(put("/api/abdominal-inseptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abdominalInseptionDTO)))
            .andExpect(status().isOk());

        // Validate the AbdominalInseption in the database
        List<AbdominalInseption> abdominalInseptionList = abdominalInseptionRepository.findAll();
        assertThat(abdominalInseptionList).hasSize(databaseSizeBeforeUpdate);
        AbdominalInseption testAbdominalInseption = abdominalInseptionList.get(abdominalInseptionList.size() - 1);
        assertThat(testAbdominalInseption.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testAbdominalInseption.getDoctorComments()).isEqualTo(UPDATED_DOCTOR_COMMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingAbdominalInseption() throws Exception {
        int databaseSizeBeforeUpdate = abdominalInseptionRepository.findAll().size();

        // Create the AbdominalInseption
        AbdominalInseptionDTO abdominalInseptionDTO = abdominalInseptionMapper.toDto(abdominalInseption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbdominalInseptionMockMvc.perform(put("/api/abdominal-inseptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abdominalInseptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AbdominalInseption in the database
        List<AbdominalInseption> abdominalInseptionList = abdominalInseptionRepository.findAll();
        assertThat(abdominalInseptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAbdominalInseption() throws Exception {
        // Initialize the database
        abdominalInseptionRepository.saveAndFlush(abdominalInseption);

        int databaseSizeBeforeDelete = abdominalInseptionRepository.findAll().size();

        // Delete the abdominalInseption
        restAbdominalInseptionMockMvc.perform(delete("/api/abdominal-inseptions/{id}", abdominalInseption.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AbdominalInseption> abdominalInseptionList = abdominalInseptionRepository.findAll();
        assertThat(abdominalInseptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
