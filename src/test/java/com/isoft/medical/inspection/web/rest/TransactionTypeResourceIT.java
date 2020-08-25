package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.TransactionType;
import com.isoft.medical.inspection.repository.TransactionTypeRepository;
import com.isoft.medical.inspection.service.TransactionTypeService;
import com.isoft.medical.inspection.service.dto.TransactionTypeDTO;
import com.isoft.medical.inspection.service.mapper.TransactionTypeMapper;

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
 * Integration tests for the {@link TransactionTypeResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TransactionTypeResourceIT {

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionTypeMapper transactionTypeMapper;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionTypeMockMvc;

    private TransactionType transactionType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionType createEntity(EntityManager em) {
        TransactionType transactionType = new TransactionType()
            .nameEn(DEFAULT_NAME_EN)
            .nameAr(DEFAULT_NAME_AR)
            .code(DEFAULT_CODE);
        return transactionType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionType createUpdatedEntity(EntityManager em) {
        TransactionType transactionType = new TransactionType()
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        return transactionType;
    }

    @BeforeEach
    public void initTest() {
        transactionType = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionType() throws Exception {
        int databaseSizeBeforeCreate = transactionTypeRepository.findAll().size();
        // Create the TransactionType
        TransactionTypeDTO transactionTypeDTO = transactionTypeMapper.toDto(transactionType);
        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionType testTransactionType = transactionTypeList.get(transactionTypeList.size() - 1);
        assertThat(testTransactionType.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testTransactionType.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testTransactionType.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createTransactionTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionTypeRepository.findAll().size();

        // Create the TransactionType with an existing ID
        transactionType.setId(1L);
        TransactionTypeDTO transactionTypeDTO = transactionTypeMapper.toDto(transactionType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTransactionTypes() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList
        restTransactionTypeMockMvc.perform(get("/api/transaction-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionType.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getTransactionType() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get the transactionType
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/{id}", transactionType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactionType.getId().intValue()))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingTransactionType() throws Exception {
        // Get the transactionType
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionType() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        int databaseSizeBeforeUpdate = transactionTypeRepository.findAll().size();

        // Update the transactionType
        TransactionType updatedTransactionType = transactionTypeRepository.findById(transactionType.getId()).get();
        // Disconnect from session so that the updates on updatedTransactionType are not directly saved in db
        em.detach(updatedTransactionType);
        updatedTransactionType
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        TransactionTypeDTO transactionTypeDTO = transactionTypeMapper.toDto(updatedTransactionType);

        restTransactionTypeMockMvc.perform(put("/api/transaction-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionTypeDTO)))
            .andExpect(status().isOk());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeUpdate);
        TransactionType testTransactionType = transactionTypeList.get(transactionTypeList.size() - 1);
        assertThat(testTransactionType.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testTransactionType.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testTransactionType.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionType() throws Exception {
        int databaseSizeBeforeUpdate = transactionTypeRepository.findAll().size();

        // Create the TransactionType
        TransactionTypeDTO transactionTypeDTO = transactionTypeMapper.toDto(transactionType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionTypeMockMvc.perform(put("/api/transaction-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactionType() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        int databaseSizeBeforeDelete = transactionTypeRepository.findAll().size();

        // Delete the transactionType
        restTransactionTypeMockMvc.perform(delete("/api/transaction-types/{id}", transactionType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
