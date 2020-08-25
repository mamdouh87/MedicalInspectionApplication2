package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.InspectionType;
import com.isoft.medical.inspection.repository.InspectionTypeRepository;
import com.isoft.medical.inspection.service.InspectionTypeService;
import com.isoft.medical.inspection.service.dto.InspectionTypeDTO;
import com.isoft.medical.inspection.service.mapper.InspectionTypeMapper;

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
 * Integration tests for the {@link InspectionTypeResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionTypeResourceIT {

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private InspectionTypeRepository inspectionTypeRepository;

    @Autowired
    private InspectionTypeMapper inspectionTypeMapper;

    @Autowired
    private InspectionTypeService inspectionTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionTypeMockMvc;

    private InspectionType inspectionType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionType createEntity(EntityManager em) {
        InspectionType inspectionType = new InspectionType()
            .nameEn(DEFAULT_NAME_EN)
            .nameAr(DEFAULT_NAME_AR)
            .code(DEFAULT_CODE);
        return inspectionType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionType createUpdatedEntity(EntityManager em) {
        InspectionType inspectionType = new InspectionType()
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        return inspectionType;
    }

    @BeforeEach
    public void initTest() {
        inspectionType = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionType() throws Exception {
        int databaseSizeBeforeCreate = inspectionTypeRepository.findAll().size();
        // Create the InspectionType
        InspectionTypeDTO inspectionTypeDTO = inspectionTypeMapper.toDto(inspectionType);
        restInspectionTypeMockMvc.perform(post("/api/inspection-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the InspectionType in the database
        List<InspectionType> inspectionTypeList = inspectionTypeRepository.findAll();
        assertThat(inspectionTypeList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionType testInspectionType = inspectionTypeList.get(inspectionTypeList.size() - 1);
        assertThat(testInspectionType.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testInspectionType.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testInspectionType.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createInspectionTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionTypeRepository.findAll().size();

        // Create the InspectionType with an existing ID
        inspectionType.setId(1L);
        InspectionTypeDTO inspectionTypeDTO = inspectionTypeMapper.toDto(inspectionType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionTypeMockMvc.perform(post("/api/inspection-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionType in the database
        List<InspectionType> inspectionTypeList = inspectionTypeRepository.findAll();
        assertThat(inspectionTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInspectionTypes() throws Exception {
        // Initialize the database
        inspectionTypeRepository.saveAndFlush(inspectionType);

        // Get all the inspectionTypeList
        restInspectionTypeMockMvc.perform(get("/api/inspection-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionType.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getInspectionType() throws Exception {
        // Initialize the database
        inspectionTypeRepository.saveAndFlush(inspectionType);

        // Get the inspectionType
        restInspectionTypeMockMvc.perform(get("/api/inspection-types/{id}", inspectionType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionType.getId().intValue()))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingInspectionType() throws Exception {
        // Get the inspectionType
        restInspectionTypeMockMvc.perform(get("/api/inspection-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionType() throws Exception {
        // Initialize the database
        inspectionTypeRepository.saveAndFlush(inspectionType);

        int databaseSizeBeforeUpdate = inspectionTypeRepository.findAll().size();

        // Update the inspectionType
        InspectionType updatedInspectionType = inspectionTypeRepository.findById(inspectionType.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionType are not directly saved in db
        em.detach(updatedInspectionType);
        updatedInspectionType
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        InspectionTypeDTO inspectionTypeDTO = inspectionTypeMapper.toDto(updatedInspectionType);

        restInspectionTypeMockMvc.perform(put("/api/inspection-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypeDTO)))
            .andExpect(status().isOk());

        // Validate the InspectionType in the database
        List<InspectionType> inspectionTypeList = inspectionTypeRepository.findAll();
        assertThat(inspectionTypeList).hasSize(databaseSizeBeforeUpdate);
        InspectionType testInspectionType = inspectionTypeList.get(inspectionTypeList.size() - 1);
        assertThat(testInspectionType.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testInspectionType.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testInspectionType.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionType() throws Exception {
        int databaseSizeBeforeUpdate = inspectionTypeRepository.findAll().size();

        // Create the InspectionType
        InspectionTypeDTO inspectionTypeDTO = inspectionTypeMapper.toDto(inspectionType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionTypeMockMvc.perform(put("/api/inspection-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionType in the database
        List<InspectionType> inspectionTypeList = inspectionTypeRepository.findAll();
        assertThat(inspectionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionType() throws Exception {
        // Initialize the database
        inspectionTypeRepository.saveAndFlush(inspectionType);

        int databaseSizeBeforeDelete = inspectionTypeRepository.findAll().size();

        // Delete the inspectionType
        restInspectionTypeMockMvc.perform(delete("/api/inspection-types/{id}", inspectionType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionType> inspectionTypeList = inspectionTypeRepository.findAll();
        assertThat(inspectionTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
