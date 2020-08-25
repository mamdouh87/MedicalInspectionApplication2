package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.MedicalInsepctionRequests;
import com.isoft.medical.inspection.repository.MedicalInsepctionRequestsRepository;
import com.isoft.medical.inspection.service.MedicalInsepctionRequestsService;
import com.isoft.medical.inspection.service.dto.MedicalInsepctionRequestsDTO;
import com.isoft.medical.inspection.service.mapper.MedicalInsepctionRequestsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MedicalInsepctionRequestsResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalInsepctionRequestsResourceIT {

    private static final String DEFAULT_REQUEST_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXPORTED = false;
    private static final Boolean UPDATED_EXPORTED = true;

    @Autowired
    private MedicalInsepctionRequestsRepository medicalInsepctionRequestsRepository;

    @Mock
    private MedicalInsepctionRequestsRepository medicalInsepctionRequestsRepositoryMock;

    @Autowired
    private MedicalInsepctionRequestsMapper medicalInsepctionRequestsMapper;

    @Mock
    private MedicalInsepctionRequestsService medicalInsepctionRequestsServiceMock;

    @Autowired
    private MedicalInsepctionRequestsService medicalInsepctionRequestsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalInsepctionRequestsMockMvc;

    private MedicalInsepctionRequests medicalInsepctionRequests;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalInsepctionRequests createEntity(EntityManager em) {
        MedicalInsepctionRequests medicalInsepctionRequests = new MedicalInsepctionRequests()
            .requestNumber(DEFAULT_REQUEST_NUMBER)
            .exported(DEFAULT_EXPORTED);
        return medicalInsepctionRequests;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalInsepctionRequests createUpdatedEntity(EntityManager em) {
        MedicalInsepctionRequests medicalInsepctionRequests = new MedicalInsepctionRequests()
            .requestNumber(UPDATED_REQUEST_NUMBER)
            .exported(UPDATED_EXPORTED);
        return medicalInsepctionRequests;
    }

    @BeforeEach
    public void initTest() {
        medicalInsepctionRequests = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalInsepctionRequests() throws Exception {
        int databaseSizeBeforeCreate = medicalInsepctionRequestsRepository.findAll().size();
        // Create the MedicalInsepctionRequests
        MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO = medicalInsepctionRequestsMapper.toDto(medicalInsepctionRequests);
        restMedicalInsepctionRequestsMockMvc.perform(post("/api/medical-insepction-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInsepctionRequestsDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalInsepctionRequests in the database
        List<MedicalInsepctionRequests> medicalInsepctionRequestsList = medicalInsepctionRequestsRepository.findAll();
        assertThat(medicalInsepctionRequestsList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalInsepctionRequests testMedicalInsepctionRequests = medicalInsepctionRequestsList.get(medicalInsepctionRequestsList.size() - 1);
        assertThat(testMedicalInsepctionRequests.getRequestNumber()).isEqualTo(DEFAULT_REQUEST_NUMBER);
        assertThat(testMedicalInsepctionRequests.isExported()).isEqualTo(DEFAULT_EXPORTED);
    }

    @Test
    @Transactional
    public void createMedicalInsepctionRequestsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalInsepctionRequestsRepository.findAll().size();

        // Create the MedicalInsepctionRequests with an existing ID
        medicalInsepctionRequests.setId(1L);
        MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO = medicalInsepctionRequestsMapper.toDto(medicalInsepctionRequests);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalInsepctionRequestsMockMvc.perform(post("/api/medical-insepction-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInsepctionRequestsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalInsepctionRequests in the database
        List<MedicalInsepctionRequests> medicalInsepctionRequestsList = medicalInsepctionRequestsRepository.findAll();
        assertThat(medicalInsepctionRequestsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalInsepctionRequests() throws Exception {
        // Initialize the database
        medicalInsepctionRequestsRepository.saveAndFlush(medicalInsepctionRequests);

        // Get all the medicalInsepctionRequestsList
        restMedicalInsepctionRequestsMockMvc.perform(get("/api/medical-insepction-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalInsepctionRequests.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestNumber").value(hasItem(DEFAULT_REQUEST_NUMBER)))
            .andExpect(jsonPath("$.[*].exported").value(hasItem(DEFAULT_EXPORTED.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllMedicalInsepctionRequestsWithEagerRelationshipsIsEnabled() throws Exception {
        when(medicalInsepctionRequestsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMedicalInsepctionRequestsMockMvc.perform(get("/api/medical-insepction-requests?eagerload=true"))
            .andExpect(status().isOk());

        verify(medicalInsepctionRequestsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllMedicalInsepctionRequestsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(medicalInsepctionRequestsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMedicalInsepctionRequestsMockMvc.perform(get("/api/medical-insepction-requests?eagerload=true"))
            .andExpect(status().isOk());

        verify(medicalInsepctionRequestsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getMedicalInsepctionRequests() throws Exception {
        // Initialize the database
        medicalInsepctionRequestsRepository.saveAndFlush(medicalInsepctionRequests);

        // Get the medicalInsepctionRequests
        restMedicalInsepctionRequestsMockMvc.perform(get("/api/medical-insepction-requests/{id}", medicalInsepctionRequests.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalInsepctionRequests.getId().intValue()))
            .andExpect(jsonPath("$.requestNumber").value(DEFAULT_REQUEST_NUMBER))
            .andExpect(jsonPath("$.exported").value(DEFAULT_EXPORTED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalInsepctionRequests() throws Exception {
        // Get the medicalInsepctionRequests
        restMedicalInsepctionRequestsMockMvc.perform(get("/api/medical-insepction-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalInsepctionRequests() throws Exception {
        // Initialize the database
        medicalInsepctionRequestsRepository.saveAndFlush(medicalInsepctionRequests);

        int databaseSizeBeforeUpdate = medicalInsepctionRequestsRepository.findAll().size();

        // Update the medicalInsepctionRequests
        MedicalInsepctionRequests updatedMedicalInsepctionRequests = medicalInsepctionRequestsRepository.findById(medicalInsepctionRequests.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalInsepctionRequests are not directly saved in db
        em.detach(updatedMedicalInsepctionRequests);
        updatedMedicalInsepctionRequests
            .requestNumber(UPDATED_REQUEST_NUMBER)
            .exported(UPDATED_EXPORTED);
        MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO = medicalInsepctionRequestsMapper.toDto(updatedMedicalInsepctionRequests);

        restMedicalInsepctionRequestsMockMvc.perform(put("/api/medical-insepction-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInsepctionRequestsDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalInsepctionRequests in the database
        List<MedicalInsepctionRequests> medicalInsepctionRequestsList = medicalInsepctionRequestsRepository.findAll();
        assertThat(medicalInsepctionRequestsList).hasSize(databaseSizeBeforeUpdate);
        MedicalInsepctionRequests testMedicalInsepctionRequests = medicalInsepctionRequestsList.get(medicalInsepctionRequestsList.size() - 1);
        assertThat(testMedicalInsepctionRequests.getRequestNumber()).isEqualTo(UPDATED_REQUEST_NUMBER);
        assertThat(testMedicalInsepctionRequests.isExported()).isEqualTo(UPDATED_EXPORTED);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalInsepctionRequests() throws Exception {
        int databaseSizeBeforeUpdate = medicalInsepctionRequestsRepository.findAll().size();

        // Create the MedicalInsepctionRequests
        MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO = medicalInsepctionRequestsMapper.toDto(medicalInsepctionRequests);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalInsepctionRequestsMockMvc.perform(put("/api/medical-insepction-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInsepctionRequestsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalInsepctionRequests in the database
        List<MedicalInsepctionRequests> medicalInsepctionRequestsList = medicalInsepctionRequestsRepository.findAll();
        assertThat(medicalInsepctionRequestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalInsepctionRequests() throws Exception {
        // Initialize the database
        medicalInsepctionRequestsRepository.saveAndFlush(medicalInsepctionRequests);

        int databaseSizeBeforeDelete = medicalInsepctionRequestsRepository.findAll().size();

        // Delete the medicalInsepctionRequests
        restMedicalInsepctionRequestsMockMvc.perform(delete("/api/medical-insepction-requests/{id}", medicalInsepctionRequests.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalInsepctionRequests> medicalInsepctionRequestsList = medicalInsepctionRequestsRepository.findAll();
        assertThat(medicalInsepctionRequestsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
