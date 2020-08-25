package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.InspectionRequirement;
import com.isoft.medical.inspection.repository.InspectionRequirementRepository;
import com.isoft.medical.inspection.service.InspectionRequirementService;
import com.isoft.medical.inspection.service.dto.InspectionRequirementDTO;
import com.isoft.medical.inspection.service.mapper.InspectionRequirementMapper;

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
 * Integration tests for the {@link InspectionRequirementResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionRequirementResourceIT {

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDER = 1;
    private static final Integer UPDATED_ORDER = 2;

    @Autowired
    private InspectionRequirementRepository inspectionRequirementRepository;

    @Autowired
    private InspectionRequirementMapper inspectionRequirementMapper;

    @Autowired
    private InspectionRequirementService inspectionRequirementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionRequirementMockMvc;

    private InspectionRequirement inspectionRequirement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionRequirement createEntity(EntityManager em) {
        InspectionRequirement inspectionRequirement = new InspectionRequirement()
            .nameEn(DEFAULT_NAME_EN)
            .nameAr(DEFAULT_NAME_AR)
            .code(DEFAULT_CODE)
            .order(DEFAULT_ORDER);
        return inspectionRequirement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionRequirement createUpdatedEntity(EntityManager em) {
        InspectionRequirement inspectionRequirement = new InspectionRequirement()
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE)
            .order(UPDATED_ORDER);
        return inspectionRequirement;
    }

    @BeforeEach
    public void initTest() {
        inspectionRequirement = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionRequirement() throws Exception {
        int databaseSizeBeforeCreate = inspectionRequirementRepository.findAll().size();
        // Create the InspectionRequirement
        InspectionRequirementDTO inspectionRequirementDTO = inspectionRequirementMapper.toDto(inspectionRequirement);
        restInspectionRequirementMockMvc.perform(post("/api/inspection-requirements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionRequirementDTO)))
            .andExpect(status().isCreated());

        // Validate the InspectionRequirement in the database
        List<InspectionRequirement> inspectionRequirementList = inspectionRequirementRepository.findAll();
        assertThat(inspectionRequirementList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionRequirement testInspectionRequirement = inspectionRequirementList.get(inspectionRequirementList.size() - 1);
        assertThat(testInspectionRequirement.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testInspectionRequirement.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testInspectionRequirement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testInspectionRequirement.getOrder()).isEqualTo(DEFAULT_ORDER);
    }

    @Test
    @Transactional
    public void createInspectionRequirementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionRequirementRepository.findAll().size();

        // Create the InspectionRequirement with an existing ID
        inspectionRequirement.setId(1L);
        InspectionRequirementDTO inspectionRequirementDTO = inspectionRequirementMapper.toDto(inspectionRequirement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionRequirementMockMvc.perform(post("/api/inspection-requirements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionRequirementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionRequirement in the database
        List<InspectionRequirement> inspectionRequirementList = inspectionRequirementRepository.findAll();
        assertThat(inspectionRequirementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInspectionRequirements() throws Exception {
        // Initialize the database
        inspectionRequirementRepository.saveAndFlush(inspectionRequirement);

        // Get all the inspectionRequirementList
        restInspectionRequirementMockMvc.perform(get("/api/inspection-requirements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionRequirement.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)));
    }
    
    @Test
    @Transactional
    public void getInspectionRequirement() throws Exception {
        // Initialize the database
        inspectionRequirementRepository.saveAndFlush(inspectionRequirement);

        // Get the inspectionRequirement
        restInspectionRequirementMockMvc.perform(get("/api/inspection-requirements/{id}", inspectionRequirement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionRequirement.getId().intValue()))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingInspectionRequirement() throws Exception {
        // Get the inspectionRequirement
        restInspectionRequirementMockMvc.perform(get("/api/inspection-requirements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionRequirement() throws Exception {
        // Initialize the database
        inspectionRequirementRepository.saveAndFlush(inspectionRequirement);

        int databaseSizeBeforeUpdate = inspectionRequirementRepository.findAll().size();

        // Update the inspectionRequirement
        InspectionRequirement updatedInspectionRequirement = inspectionRequirementRepository.findById(inspectionRequirement.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionRequirement are not directly saved in db
        em.detach(updatedInspectionRequirement);
        updatedInspectionRequirement
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE)
            .order(UPDATED_ORDER);
        InspectionRequirementDTO inspectionRequirementDTO = inspectionRequirementMapper.toDto(updatedInspectionRequirement);

        restInspectionRequirementMockMvc.perform(put("/api/inspection-requirements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionRequirementDTO)))
            .andExpect(status().isOk());

        // Validate the InspectionRequirement in the database
        List<InspectionRequirement> inspectionRequirementList = inspectionRequirementRepository.findAll();
        assertThat(inspectionRequirementList).hasSize(databaseSizeBeforeUpdate);
        InspectionRequirement testInspectionRequirement = inspectionRequirementList.get(inspectionRequirementList.size() - 1);
        assertThat(testInspectionRequirement.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testInspectionRequirement.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testInspectionRequirement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testInspectionRequirement.getOrder()).isEqualTo(UPDATED_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionRequirement() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRequirementRepository.findAll().size();

        // Create the InspectionRequirement
        InspectionRequirementDTO inspectionRequirementDTO = inspectionRequirementMapper.toDto(inspectionRequirement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionRequirementMockMvc.perform(put("/api/inspection-requirements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionRequirementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionRequirement in the database
        List<InspectionRequirement> inspectionRequirementList = inspectionRequirementRepository.findAll();
        assertThat(inspectionRequirementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionRequirement() throws Exception {
        // Initialize the database
        inspectionRequirementRepository.saveAndFlush(inspectionRequirement);

        int databaseSizeBeforeDelete = inspectionRequirementRepository.findAll().size();

        // Delete the inspectionRequirement
        restInspectionRequirementMockMvc.perform(delete("/api/inspection-requirements/{id}", inspectionRequirement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionRequirement> inspectionRequirementList = inspectionRequirementRepository.findAll();
        assertThat(inspectionRequirementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
