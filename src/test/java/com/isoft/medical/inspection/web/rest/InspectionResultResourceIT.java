package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.InspectionResult;
import com.isoft.medical.inspection.repository.InspectionResultRepository;
import com.isoft.medical.inspection.service.InspectionResultService;
import com.isoft.medical.inspection.service.dto.InspectionResultDTO;
import com.isoft.medical.inspection.service.mapper.InspectionResultMapper;

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
 * Integration tests for the {@link InspectionResultResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionResultResourceIT {

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private InspectionResultRepository inspectionResultRepository;

    @Autowired
    private InspectionResultMapper inspectionResultMapper;

    @Autowired
    private InspectionResultService inspectionResultService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionResultMockMvc;

    private InspectionResult inspectionResult;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionResult createEntity(EntityManager em) {
        InspectionResult inspectionResult = new InspectionResult()
            .nameEn(DEFAULT_NAME_EN)
            .nameAr(DEFAULT_NAME_AR)
            .code(DEFAULT_CODE);
        return inspectionResult;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionResult createUpdatedEntity(EntityManager em) {
        InspectionResult inspectionResult = new InspectionResult()
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        return inspectionResult;
    }

    @BeforeEach
    public void initTest() {
        inspectionResult = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionResult() throws Exception {
        int databaseSizeBeforeCreate = inspectionResultRepository.findAll().size();
        // Create the InspectionResult
        InspectionResultDTO inspectionResultDTO = inspectionResultMapper.toDto(inspectionResult);
        restInspectionResultMockMvc.perform(post("/api/inspection-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionResultDTO)))
            .andExpect(status().isCreated());

        // Validate the InspectionResult in the database
        List<InspectionResult> inspectionResultList = inspectionResultRepository.findAll();
        assertThat(inspectionResultList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionResult testInspectionResult = inspectionResultList.get(inspectionResultList.size() - 1);
        assertThat(testInspectionResult.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testInspectionResult.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testInspectionResult.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createInspectionResultWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionResultRepository.findAll().size();

        // Create the InspectionResult with an existing ID
        inspectionResult.setId(1L);
        InspectionResultDTO inspectionResultDTO = inspectionResultMapper.toDto(inspectionResult);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionResultMockMvc.perform(post("/api/inspection-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionResult in the database
        List<InspectionResult> inspectionResultList = inspectionResultRepository.findAll();
        assertThat(inspectionResultList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInspectionResults() throws Exception {
        // Initialize the database
        inspectionResultRepository.saveAndFlush(inspectionResult);

        // Get all the inspectionResultList
        restInspectionResultMockMvc.perform(get("/api/inspection-results?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionResult.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getInspectionResult() throws Exception {
        // Initialize the database
        inspectionResultRepository.saveAndFlush(inspectionResult);

        // Get the inspectionResult
        restInspectionResultMockMvc.perform(get("/api/inspection-results/{id}", inspectionResult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionResult.getId().intValue()))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingInspectionResult() throws Exception {
        // Get the inspectionResult
        restInspectionResultMockMvc.perform(get("/api/inspection-results/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionResult() throws Exception {
        // Initialize the database
        inspectionResultRepository.saveAndFlush(inspectionResult);

        int databaseSizeBeforeUpdate = inspectionResultRepository.findAll().size();

        // Update the inspectionResult
        InspectionResult updatedInspectionResult = inspectionResultRepository.findById(inspectionResult.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionResult are not directly saved in db
        em.detach(updatedInspectionResult);
        updatedInspectionResult
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        InspectionResultDTO inspectionResultDTO = inspectionResultMapper.toDto(updatedInspectionResult);

        restInspectionResultMockMvc.perform(put("/api/inspection-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionResultDTO)))
            .andExpect(status().isOk());

        // Validate the InspectionResult in the database
        List<InspectionResult> inspectionResultList = inspectionResultRepository.findAll();
        assertThat(inspectionResultList).hasSize(databaseSizeBeforeUpdate);
        InspectionResult testInspectionResult = inspectionResultList.get(inspectionResultList.size() - 1);
        assertThat(testInspectionResult.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testInspectionResult.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testInspectionResult.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionResult() throws Exception {
        int databaseSizeBeforeUpdate = inspectionResultRepository.findAll().size();

        // Create the InspectionResult
        InspectionResultDTO inspectionResultDTO = inspectionResultMapper.toDto(inspectionResult);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionResultMockMvc.perform(put("/api/inspection-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionResult in the database
        List<InspectionResult> inspectionResultList = inspectionResultRepository.findAll();
        assertThat(inspectionResultList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionResult() throws Exception {
        // Initialize the database
        inspectionResultRepository.saveAndFlush(inspectionResult);

        int databaseSizeBeforeDelete = inspectionResultRepository.findAll().size();

        // Delete the inspectionResult
        restInspectionResultMockMvc.perform(delete("/api/inspection-results/{id}", inspectionResult.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionResult> inspectionResultList = inspectionResultRepository.findAll();
        assertThat(inspectionResultList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
