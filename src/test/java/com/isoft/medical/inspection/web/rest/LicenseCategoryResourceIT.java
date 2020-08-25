package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.LicenseCategory;
import com.isoft.medical.inspection.repository.LicenseCategoryRepository;
import com.isoft.medical.inspection.service.LicenseCategoryService;
import com.isoft.medical.inspection.service.dto.LicenseCategoryDTO;
import com.isoft.medical.inspection.service.mapper.LicenseCategoryMapper;

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
 * Integration tests for the {@link LicenseCategoryResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LicenseCategoryResourceIT {

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private LicenseCategoryRepository licenseCategoryRepository;

    @Autowired
    private LicenseCategoryMapper licenseCategoryMapper;

    @Autowired
    private LicenseCategoryService licenseCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLicenseCategoryMockMvc;

    private LicenseCategory licenseCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LicenseCategory createEntity(EntityManager em) {
        LicenseCategory licenseCategory = new LicenseCategory()
            .nameEn(DEFAULT_NAME_EN)
            .nameAr(DEFAULT_NAME_AR)
            .code(DEFAULT_CODE);
        return licenseCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LicenseCategory createUpdatedEntity(EntityManager em) {
        LicenseCategory licenseCategory = new LicenseCategory()
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        return licenseCategory;
    }

    @BeforeEach
    public void initTest() {
        licenseCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createLicenseCategory() throws Exception {
        int databaseSizeBeforeCreate = licenseCategoryRepository.findAll().size();
        // Create the LicenseCategory
        LicenseCategoryDTO licenseCategoryDTO = licenseCategoryMapper.toDto(licenseCategory);
        restLicenseCategoryMockMvc.perform(post("/api/license-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenseCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the LicenseCategory in the database
        List<LicenseCategory> licenseCategoryList = licenseCategoryRepository.findAll();
        assertThat(licenseCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        LicenseCategory testLicenseCategory = licenseCategoryList.get(licenseCategoryList.size() - 1);
        assertThat(testLicenseCategory.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testLicenseCategory.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testLicenseCategory.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createLicenseCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = licenseCategoryRepository.findAll().size();

        // Create the LicenseCategory with an existing ID
        licenseCategory.setId(1L);
        LicenseCategoryDTO licenseCategoryDTO = licenseCategoryMapper.toDto(licenseCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLicenseCategoryMockMvc.perform(post("/api/license-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenseCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LicenseCategory in the database
        List<LicenseCategory> licenseCategoryList = licenseCategoryRepository.findAll();
        assertThat(licenseCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLicenseCategories() throws Exception {
        // Initialize the database
        licenseCategoryRepository.saveAndFlush(licenseCategory);

        // Get all the licenseCategoryList
        restLicenseCategoryMockMvc.perform(get("/api/license-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(licenseCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getLicenseCategory() throws Exception {
        // Initialize the database
        licenseCategoryRepository.saveAndFlush(licenseCategory);

        // Get the licenseCategory
        restLicenseCategoryMockMvc.perform(get("/api/license-categories/{id}", licenseCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(licenseCategory.getId().intValue()))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingLicenseCategory() throws Exception {
        // Get the licenseCategory
        restLicenseCategoryMockMvc.perform(get("/api/license-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLicenseCategory() throws Exception {
        // Initialize the database
        licenseCategoryRepository.saveAndFlush(licenseCategory);

        int databaseSizeBeforeUpdate = licenseCategoryRepository.findAll().size();

        // Update the licenseCategory
        LicenseCategory updatedLicenseCategory = licenseCategoryRepository.findById(licenseCategory.getId()).get();
        // Disconnect from session so that the updates on updatedLicenseCategory are not directly saved in db
        em.detach(updatedLicenseCategory);
        updatedLicenseCategory
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        LicenseCategoryDTO licenseCategoryDTO = licenseCategoryMapper.toDto(updatedLicenseCategory);

        restLicenseCategoryMockMvc.perform(put("/api/license-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenseCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the LicenseCategory in the database
        List<LicenseCategory> licenseCategoryList = licenseCategoryRepository.findAll();
        assertThat(licenseCategoryList).hasSize(databaseSizeBeforeUpdate);
        LicenseCategory testLicenseCategory = licenseCategoryList.get(licenseCategoryList.size() - 1);
        assertThat(testLicenseCategory.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testLicenseCategory.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testLicenseCategory.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingLicenseCategory() throws Exception {
        int databaseSizeBeforeUpdate = licenseCategoryRepository.findAll().size();

        // Create the LicenseCategory
        LicenseCategoryDTO licenseCategoryDTO = licenseCategoryMapper.toDto(licenseCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLicenseCategoryMockMvc.perform(put("/api/license-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenseCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LicenseCategory in the database
        List<LicenseCategory> licenseCategoryList = licenseCategoryRepository.findAll();
        assertThat(licenseCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLicenseCategory() throws Exception {
        // Initialize the database
        licenseCategoryRepository.saveAndFlush(licenseCategory);

        int databaseSizeBeforeDelete = licenseCategoryRepository.findAll().size();

        // Delete the licenseCategory
        restLicenseCategoryMockMvc.perform(delete("/api/license-categories/{id}", licenseCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LicenseCategory> licenseCategoryList = licenseCategoryRepository.findAll();
        assertThat(licenseCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
