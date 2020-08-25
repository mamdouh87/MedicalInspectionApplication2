package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.MedicalCondition;
import com.isoft.medical.inspection.repository.MedicalConditionRepository;
import com.isoft.medical.inspection.service.MedicalConditionService;
import com.isoft.medical.inspection.service.dto.MedicalConditionDTO;
import com.isoft.medical.inspection.service.mapper.MedicalConditionMapper;

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
 * Integration tests for the {@link MedicalConditionResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalConditionResourceIT {

    private static final String DEFAULT_CONDITION_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_CONDITION_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_CONDITION_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_CONDITION_NAME_AR = "BBBBBBBBBB";

    @Autowired
    private MedicalConditionRepository medicalConditionRepository;

    @Autowired
    private MedicalConditionMapper medicalConditionMapper;

    @Autowired
    private MedicalConditionService medicalConditionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalConditionMockMvc;

    private MedicalCondition medicalCondition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCondition createEntity(EntityManager em) {
        MedicalCondition medicalCondition = new MedicalCondition()
            .conditionNameEn(DEFAULT_CONDITION_NAME_EN)
            .conditionNameAr(DEFAULT_CONDITION_NAME_AR);
        return medicalCondition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCondition createUpdatedEntity(EntityManager em) {
        MedicalCondition medicalCondition = new MedicalCondition()
            .conditionNameEn(UPDATED_CONDITION_NAME_EN)
            .conditionNameAr(UPDATED_CONDITION_NAME_AR);
        return medicalCondition;
    }

    @BeforeEach
    public void initTest() {
        medicalCondition = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalCondition() throws Exception {
        int databaseSizeBeforeCreate = medicalConditionRepository.findAll().size();
        // Create the MedicalCondition
        MedicalConditionDTO medicalConditionDTO = medicalConditionMapper.toDto(medicalCondition);
        restMedicalConditionMockMvc.perform(post("/api/medical-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalConditionDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalCondition in the database
        List<MedicalCondition> medicalConditionList = medicalConditionRepository.findAll();
        assertThat(medicalConditionList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalCondition testMedicalCondition = medicalConditionList.get(medicalConditionList.size() - 1);
        assertThat(testMedicalCondition.getConditionNameEn()).isEqualTo(DEFAULT_CONDITION_NAME_EN);
        assertThat(testMedicalCondition.getConditionNameAr()).isEqualTo(DEFAULT_CONDITION_NAME_AR);
    }

    @Test
    @Transactional
    public void createMedicalConditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalConditionRepository.findAll().size();

        // Create the MedicalCondition with an existing ID
        medicalCondition.setId(1L);
        MedicalConditionDTO medicalConditionDTO = medicalConditionMapper.toDto(medicalCondition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalConditionMockMvc.perform(post("/api/medical-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCondition in the database
        List<MedicalCondition> medicalConditionList = medicalConditionRepository.findAll();
        assertThat(medicalConditionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalConditions() throws Exception {
        // Initialize the database
        medicalConditionRepository.saveAndFlush(medicalCondition);

        // Get all the medicalConditionList
        restMedicalConditionMockMvc.perform(get("/api/medical-conditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].conditionNameEn").value(hasItem(DEFAULT_CONDITION_NAME_EN)))
            .andExpect(jsonPath("$.[*].conditionNameAr").value(hasItem(DEFAULT_CONDITION_NAME_AR)));
    }
    
    @Test
    @Transactional
    public void getMedicalCondition() throws Exception {
        // Initialize the database
        medicalConditionRepository.saveAndFlush(medicalCondition);

        // Get the medicalCondition
        restMedicalConditionMockMvc.perform(get("/api/medical-conditions/{id}", medicalCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalCondition.getId().intValue()))
            .andExpect(jsonPath("$.conditionNameEn").value(DEFAULT_CONDITION_NAME_EN))
            .andExpect(jsonPath("$.conditionNameAr").value(DEFAULT_CONDITION_NAME_AR));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalCondition() throws Exception {
        // Get the medicalCondition
        restMedicalConditionMockMvc.perform(get("/api/medical-conditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalCondition() throws Exception {
        // Initialize the database
        medicalConditionRepository.saveAndFlush(medicalCondition);

        int databaseSizeBeforeUpdate = medicalConditionRepository.findAll().size();

        // Update the medicalCondition
        MedicalCondition updatedMedicalCondition = medicalConditionRepository.findById(medicalCondition.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalCondition are not directly saved in db
        em.detach(updatedMedicalCondition);
        updatedMedicalCondition
            .conditionNameEn(UPDATED_CONDITION_NAME_EN)
            .conditionNameAr(UPDATED_CONDITION_NAME_AR);
        MedicalConditionDTO medicalConditionDTO = medicalConditionMapper.toDto(updatedMedicalCondition);

        restMedicalConditionMockMvc.perform(put("/api/medical-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalConditionDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalCondition in the database
        List<MedicalCondition> medicalConditionList = medicalConditionRepository.findAll();
        assertThat(medicalConditionList).hasSize(databaseSizeBeforeUpdate);
        MedicalCondition testMedicalCondition = medicalConditionList.get(medicalConditionList.size() - 1);
        assertThat(testMedicalCondition.getConditionNameEn()).isEqualTo(UPDATED_CONDITION_NAME_EN);
        assertThat(testMedicalCondition.getConditionNameAr()).isEqualTo(UPDATED_CONDITION_NAME_AR);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalCondition() throws Exception {
        int databaseSizeBeforeUpdate = medicalConditionRepository.findAll().size();

        // Create the MedicalCondition
        MedicalConditionDTO medicalConditionDTO = medicalConditionMapper.toDto(medicalCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalConditionMockMvc.perform(put("/api/medical-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCondition in the database
        List<MedicalCondition> medicalConditionList = medicalConditionRepository.findAll();
        assertThat(medicalConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalCondition() throws Exception {
        // Initialize the database
        medicalConditionRepository.saveAndFlush(medicalCondition);

        int databaseSizeBeforeDelete = medicalConditionRepository.findAll().size();

        // Delete the medicalCondition
        restMedicalConditionMockMvc.perform(delete("/api/medical-conditions/{id}", medicalCondition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalCondition> medicalConditionList = medicalConditionRepository.findAll();
        assertThat(medicalConditionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
