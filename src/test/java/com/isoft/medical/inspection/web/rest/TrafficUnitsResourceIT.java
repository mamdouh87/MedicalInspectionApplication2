package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.TrafficUnits;
import com.isoft.medical.inspection.repository.TrafficUnitsRepository;
import com.isoft.medical.inspection.service.TrafficUnitsService;
import com.isoft.medical.inspection.service.dto.TrafficUnitsDTO;
import com.isoft.medical.inspection.service.mapper.TrafficUnitsMapper;

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
 * Integration tests for the {@link TrafficUnitsResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TrafficUnitsResourceIT {

    private static final String DEFAULT_TRAFFIC_UNIT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_TRAFFIC_UNIT_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_TRAFFIC_UNIT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_TRAFFIC_UNIT_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_TRAFFIC_UNIT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRAFFIC_UNIT_CODE = "BBBBBBBBBB";

    @Autowired
    private TrafficUnitsRepository trafficUnitsRepository;

    @Autowired
    private TrafficUnitsMapper trafficUnitsMapper;

    @Autowired
    private TrafficUnitsService trafficUnitsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTrafficUnitsMockMvc;

    private TrafficUnits trafficUnits;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrafficUnits createEntity(EntityManager em) {
        TrafficUnits trafficUnits = new TrafficUnits()
            .trafficUnitNameEn(DEFAULT_TRAFFIC_UNIT_NAME_EN)
            .trafficUnitNameAr(DEFAULT_TRAFFIC_UNIT_NAME_AR)
            .trafficUnitCode(DEFAULT_TRAFFIC_UNIT_CODE);
        return trafficUnits;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrafficUnits createUpdatedEntity(EntityManager em) {
        TrafficUnits trafficUnits = new TrafficUnits()
            .trafficUnitNameEn(UPDATED_TRAFFIC_UNIT_NAME_EN)
            .trafficUnitNameAr(UPDATED_TRAFFIC_UNIT_NAME_AR)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);
        return trafficUnits;
    }

    @BeforeEach
    public void initTest() {
        trafficUnits = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrafficUnits() throws Exception {
        int databaseSizeBeforeCreate = trafficUnitsRepository.findAll().size();
        // Create the TrafficUnits
        TrafficUnitsDTO trafficUnitsDTO = trafficUnitsMapper.toDto(trafficUnits);
        restTrafficUnitsMockMvc.perform(post("/api/traffic-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(trafficUnitsDTO)))
            .andExpect(status().isCreated());

        // Validate the TrafficUnits in the database
        List<TrafficUnits> trafficUnitsList = trafficUnitsRepository.findAll();
        assertThat(trafficUnitsList).hasSize(databaseSizeBeforeCreate + 1);
        TrafficUnits testTrafficUnits = trafficUnitsList.get(trafficUnitsList.size() - 1);
        assertThat(testTrafficUnits.getTrafficUnitNameEn()).isEqualTo(DEFAULT_TRAFFIC_UNIT_NAME_EN);
        assertThat(testTrafficUnits.getTrafficUnitNameAr()).isEqualTo(DEFAULT_TRAFFIC_UNIT_NAME_AR);
        assertThat(testTrafficUnits.getTrafficUnitCode()).isEqualTo(DEFAULT_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    public void createTrafficUnitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trafficUnitsRepository.findAll().size();

        // Create the TrafficUnits with an existing ID
        trafficUnits.setId(1L);
        TrafficUnitsDTO trafficUnitsDTO = trafficUnitsMapper.toDto(trafficUnits);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrafficUnitsMockMvc.perform(post("/api/traffic-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(trafficUnitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrafficUnits in the database
        List<TrafficUnits> trafficUnitsList = trafficUnitsRepository.findAll();
        assertThat(trafficUnitsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTrafficUnits() throws Exception {
        // Initialize the database
        trafficUnitsRepository.saveAndFlush(trafficUnits);

        // Get all the trafficUnitsList
        restTrafficUnitsMockMvc.perform(get("/api/traffic-units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trafficUnits.getId().intValue())))
            .andExpect(jsonPath("$.[*].trafficUnitNameEn").value(hasItem(DEFAULT_TRAFFIC_UNIT_NAME_EN)))
            .andExpect(jsonPath("$.[*].trafficUnitNameAr").value(hasItem(DEFAULT_TRAFFIC_UNIT_NAME_AR)))
            .andExpect(jsonPath("$.[*].trafficUnitCode").value(hasItem(DEFAULT_TRAFFIC_UNIT_CODE)));
    }
    
    @Test
    @Transactional
    public void getTrafficUnits() throws Exception {
        // Initialize the database
        trafficUnitsRepository.saveAndFlush(trafficUnits);

        // Get the trafficUnits
        restTrafficUnitsMockMvc.perform(get("/api/traffic-units/{id}", trafficUnits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(trafficUnits.getId().intValue()))
            .andExpect(jsonPath("$.trafficUnitNameEn").value(DEFAULT_TRAFFIC_UNIT_NAME_EN))
            .andExpect(jsonPath("$.trafficUnitNameAr").value(DEFAULT_TRAFFIC_UNIT_NAME_AR))
            .andExpect(jsonPath("$.trafficUnitCode").value(DEFAULT_TRAFFIC_UNIT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingTrafficUnits() throws Exception {
        // Get the trafficUnits
        restTrafficUnitsMockMvc.perform(get("/api/traffic-units/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrafficUnits() throws Exception {
        // Initialize the database
        trafficUnitsRepository.saveAndFlush(trafficUnits);

        int databaseSizeBeforeUpdate = trafficUnitsRepository.findAll().size();

        // Update the trafficUnits
        TrafficUnits updatedTrafficUnits = trafficUnitsRepository.findById(trafficUnits.getId()).get();
        // Disconnect from session so that the updates on updatedTrafficUnits are not directly saved in db
        em.detach(updatedTrafficUnits);
        updatedTrafficUnits
            .trafficUnitNameEn(UPDATED_TRAFFIC_UNIT_NAME_EN)
            .trafficUnitNameAr(UPDATED_TRAFFIC_UNIT_NAME_AR)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);
        TrafficUnitsDTO trafficUnitsDTO = trafficUnitsMapper.toDto(updatedTrafficUnits);

        restTrafficUnitsMockMvc.perform(put("/api/traffic-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(trafficUnitsDTO)))
            .andExpect(status().isOk());

        // Validate the TrafficUnits in the database
        List<TrafficUnits> trafficUnitsList = trafficUnitsRepository.findAll();
        assertThat(trafficUnitsList).hasSize(databaseSizeBeforeUpdate);
        TrafficUnits testTrafficUnits = trafficUnitsList.get(trafficUnitsList.size() - 1);
        assertThat(testTrafficUnits.getTrafficUnitNameEn()).isEqualTo(UPDATED_TRAFFIC_UNIT_NAME_EN);
        assertThat(testTrafficUnits.getTrafficUnitNameAr()).isEqualTo(UPDATED_TRAFFIC_UNIT_NAME_AR);
        assertThat(testTrafficUnits.getTrafficUnitCode()).isEqualTo(UPDATED_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingTrafficUnits() throws Exception {
        int databaseSizeBeforeUpdate = trafficUnitsRepository.findAll().size();

        // Create the TrafficUnits
        TrafficUnitsDTO trafficUnitsDTO = trafficUnitsMapper.toDto(trafficUnits);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrafficUnitsMockMvc.perform(put("/api/traffic-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(trafficUnitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrafficUnits in the database
        List<TrafficUnits> trafficUnitsList = trafficUnitsRepository.findAll();
        assertThat(trafficUnitsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrafficUnits() throws Exception {
        // Initialize the database
        trafficUnitsRepository.saveAndFlush(trafficUnits);

        int databaseSizeBeforeDelete = trafficUnitsRepository.findAll().size();

        // Delete the trafficUnits
        restTrafficUnitsMockMvc.perform(delete("/api/traffic-units/{id}", trafficUnits.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TrafficUnits> trafficUnitsList = trafficUnitsRepository.findAll();
        assertThat(trafficUnitsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
