package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.RequestStatus;
import com.isoft.medical.inspection.repository.RequestStatusRepository;
import com.isoft.medical.inspection.service.RequestStatusService;
import com.isoft.medical.inspection.service.dto.RequestStatusDTO;
import com.isoft.medical.inspection.service.mapper.RequestStatusMapper;

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
 * Integration tests for the {@link RequestStatusResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequestStatusResourceIT {

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private RequestStatusRepository requestStatusRepository;

    @Autowired
    private RequestStatusMapper requestStatusMapper;

    @Autowired
    private RequestStatusService requestStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestStatusMockMvc;

    private RequestStatus requestStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestStatus createEntity(EntityManager em) {
        RequestStatus requestStatus = new RequestStatus()
            .nameEn(DEFAULT_NAME_EN)
            .nameAr(DEFAULT_NAME_AR)
            .code(DEFAULT_CODE);
        return requestStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestStatus createUpdatedEntity(EntityManager em) {
        RequestStatus requestStatus = new RequestStatus()
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        return requestStatus;
    }

    @BeforeEach
    public void initTest() {
        requestStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequestStatus() throws Exception {
        int databaseSizeBeforeCreate = requestStatusRepository.findAll().size();
        // Create the RequestStatus
        RequestStatusDTO requestStatusDTO = requestStatusMapper.toDto(requestStatus);
        restRequestStatusMockMvc.perform(post("/api/request-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the RequestStatus in the database
        List<RequestStatus> requestStatusList = requestStatusRepository.findAll();
        assertThat(requestStatusList).hasSize(databaseSizeBeforeCreate + 1);
        RequestStatus testRequestStatus = requestStatusList.get(requestStatusList.size() - 1);
        assertThat(testRequestStatus.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testRequestStatus.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testRequestStatus.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createRequestStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestStatusRepository.findAll().size();

        // Create the RequestStatus with an existing ID
        requestStatus.setId(1L);
        RequestStatusDTO requestStatusDTO = requestStatusMapper.toDto(requestStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestStatusMockMvc.perform(post("/api/request-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestStatus in the database
        List<RequestStatus> requestStatusList = requestStatusRepository.findAll();
        assertThat(requestStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRequestStatuses() throws Exception {
        // Initialize the database
        requestStatusRepository.saveAndFlush(requestStatus);

        // Get all the requestStatusList
        restRequestStatusMockMvc.perform(get("/api/request-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getRequestStatus() throws Exception {
        // Initialize the database
        requestStatusRepository.saveAndFlush(requestStatus);

        // Get the requestStatus
        restRequestStatusMockMvc.perform(get("/api/request-statuses/{id}", requestStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestStatus.getId().intValue()))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingRequestStatus() throws Exception {
        // Get the requestStatus
        restRequestStatusMockMvc.perform(get("/api/request-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestStatus() throws Exception {
        // Initialize the database
        requestStatusRepository.saveAndFlush(requestStatus);

        int databaseSizeBeforeUpdate = requestStatusRepository.findAll().size();

        // Update the requestStatus
        RequestStatus updatedRequestStatus = requestStatusRepository.findById(requestStatus.getId()).get();
        // Disconnect from session so that the updates on updatedRequestStatus are not directly saved in db
        em.detach(updatedRequestStatus);
        updatedRequestStatus
            .nameEn(UPDATED_NAME_EN)
            .nameAr(UPDATED_NAME_AR)
            .code(UPDATED_CODE);
        RequestStatusDTO requestStatusDTO = requestStatusMapper.toDto(updatedRequestStatus);

        restRequestStatusMockMvc.perform(put("/api/request-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestStatusDTO)))
            .andExpect(status().isOk());

        // Validate the RequestStatus in the database
        List<RequestStatus> requestStatusList = requestStatusRepository.findAll();
        assertThat(requestStatusList).hasSize(databaseSizeBeforeUpdate);
        RequestStatus testRequestStatus = requestStatusList.get(requestStatusList.size() - 1);
        assertThat(testRequestStatus.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testRequestStatus.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testRequestStatus.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingRequestStatus() throws Exception {
        int databaseSizeBeforeUpdate = requestStatusRepository.findAll().size();

        // Create the RequestStatus
        RequestStatusDTO requestStatusDTO = requestStatusMapper.toDto(requestStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestStatusMockMvc.perform(put("/api/request-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestStatus in the database
        List<RequestStatus> requestStatusList = requestStatusRepository.findAll();
        assertThat(requestStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequestStatus() throws Exception {
        // Initialize the database
        requestStatusRepository.saveAndFlush(requestStatus);

        int databaseSizeBeforeDelete = requestStatusRepository.findAll().size();

        // Delete the requestStatus
        restRequestStatusMockMvc.perform(delete("/api/request-statuses/{id}", requestStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestStatus> requestStatusList = requestStatusRepository.findAll();
        assertThat(requestStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
