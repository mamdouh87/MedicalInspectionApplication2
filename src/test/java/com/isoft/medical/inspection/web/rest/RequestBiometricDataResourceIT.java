package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.RequestBiometricData;
import com.isoft.medical.inspection.repository.RequestBiometricDataRepository;
import com.isoft.medical.inspection.service.RequestBiometricDataService;
import com.isoft.medical.inspection.service.dto.RequestBiometricDataDTO;
import com.isoft.medical.inspection.service.mapper.RequestBiometricDataMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RequestBiometricDataResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequestBiometricDataResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private RequestBiometricDataRepository requestBiometricDataRepository;

    @Autowired
    private RequestBiometricDataMapper requestBiometricDataMapper;

    @Autowired
    private RequestBiometricDataService requestBiometricDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestBiometricDataMockMvc;

    private RequestBiometricData requestBiometricData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestBiometricData createEntity(EntityManager em) {
        RequestBiometricData requestBiometricData = new RequestBiometricData()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return requestBiometricData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestBiometricData createUpdatedEntity(EntityManager em) {
        RequestBiometricData requestBiometricData = new RequestBiometricData()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return requestBiometricData;
    }

    @BeforeEach
    public void initTest() {
        requestBiometricData = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequestBiometricData() throws Exception {
        int databaseSizeBeforeCreate = requestBiometricDataRepository.findAll().size();
        // Create the RequestBiometricData
        RequestBiometricDataDTO requestBiometricDataDTO = requestBiometricDataMapper.toDto(requestBiometricData);
        restRequestBiometricDataMockMvc.perform(post("/api/request-biometric-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestBiometricDataDTO)))
            .andExpect(status().isCreated());

        // Validate the RequestBiometricData in the database
        List<RequestBiometricData> requestBiometricDataList = requestBiometricDataRepository.findAll();
        assertThat(requestBiometricDataList).hasSize(databaseSizeBeforeCreate + 1);
        RequestBiometricData testRequestBiometricData = requestBiometricDataList.get(requestBiometricDataList.size() - 1);
        assertThat(testRequestBiometricData.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testRequestBiometricData.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createRequestBiometricDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestBiometricDataRepository.findAll().size();

        // Create the RequestBiometricData with an existing ID
        requestBiometricData.setId(1L);
        RequestBiometricDataDTO requestBiometricDataDTO = requestBiometricDataMapper.toDto(requestBiometricData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestBiometricDataMockMvc.perform(post("/api/request-biometric-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestBiometricDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestBiometricData in the database
        List<RequestBiometricData> requestBiometricDataList = requestBiometricDataRepository.findAll();
        assertThat(requestBiometricDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRequestBiometricData() throws Exception {
        // Initialize the database
        requestBiometricDataRepository.saveAndFlush(requestBiometricData);

        // Get all the requestBiometricDataList
        restRequestBiometricDataMockMvc.perform(get("/api/request-biometric-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestBiometricData.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getRequestBiometricData() throws Exception {
        // Initialize the database
        requestBiometricDataRepository.saveAndFlush(requestBiometricData);

        // Get the requestBiometricData
        restRequestBiometricDataMockMvc.perform(get("/api/request-biometric-data/{id}", requestBiometricData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestBiometricData.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingRequestBiometricData() throws Exception {
        // Get the requestBiometricData
        restRequestBiometricDataMockMvc.perform(get("/api/request-biometric-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestBiometricData() throws Exception {
        // Initialize the database
        requestBiometricDataRepository.saveAndFlush(requestBiometricData);

        int databaseSizeBeforeUpdate = requestBiometricDataRepository.findAll().size();

        // Update the requestBiometricData
        RequestBiometricData updatedRequestBiometricData = requestBiometricDataRepository.findById(requestBiometricData.getId()).get();
        // Disconnect from session so that the updates on updatedRequestBiometricData are not directly saved in db
        em.detach(updatedRequestBiometricData);
        updatedRequestBiometricData
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        RequestBiometricDataDTO requestBiometricDataDTO = requestBiometricDataMapper.toDto(updatedRequestBiometricData);

        restRequestBiometricDataMockMvc.perform(put("/api/request-biometric-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestBiometricDataDTO)))
            .andExpect(status().isOk());

        // Validate the RequestBiometricData in the database
        List<RequestBiometricData> requestBiometricDataList = requestBiometricDataRepository.findAll();
        assertThat(requestBiometricDataList).hasSize(databaseSizeBeforeUpdate);
        RequestBiometricData testRequestBiometricData = requestBiometricDataList.get(requestBiometricDataList.size() - 1);
        assertThat(testRequestBiometricData.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testRequestBiometricData.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingRequestBiometricData() throws Exception {
        int databaseSizeBeforeUpdate = requestBiometricDataRepository.findAll().size();

        // Create the RequestBiometricData
        RequestBiometricDataDTO requestBiometricDataDTO = requestBiometricDataMapper.toDto(requestBiometricData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestBiometricDataMockMvc.perform(put("/api/request-biometric-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestBiometricDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestBiometricData in the database
        List<RequestBiometricData> requestBiometricDataList = requestBiometricDataRepository.findAll();
        assertThat(requestBiometricDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequestBiometricData() throws Exception {
        // Initialize the database
        requestBiometricDataRepository.saveAndFlush(requestBiometricData);

        int databaseSizeBeforeDelete = requestBiometricDataRepository.findAll().size();

        // Delete the requestBiometricData
        restRequestBiometricDataMockMvc.perform(delete("/api/request-biometric-data/{id}", requestBiometricData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestBiometricData> requestBiometricDataList = requestBiometricDataRepository.findAll();
        assertThat(requestBiometricDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
