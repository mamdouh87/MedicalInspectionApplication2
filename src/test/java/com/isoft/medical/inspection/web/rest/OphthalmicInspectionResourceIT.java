package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.OphthalmicInspection;
import com.isoft.medical.inspection.repository.OphthalmicInspectionRepository;
import com.isoft.medical.inspection.service.OphthalmicInspectionService;
import com.isoft.medical.inspection.service.dto.OphthalmicInspectionDTO;
import com.isoft.medical.inspection.service.mapper.OphthalmicInspectionMapper;

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
 * Integration tests for the {@link OphthalmicInspectionResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OphthalmicInspectionResourceIT {

    private static final String DEFAULT_RIGTH_EYE = "AAAAAAAAAA";
    private static final String UPDATED_RIGTH_EYE = "BBBBBBBBBB";

    private static final String DEFAULT_LEFT_EYE = "AAAAAAAAAA";
    private static final String UPDATED_LEFT_EYE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private OphthalmicInspectionRepository ophthalmicInspectionRepository;

    @Autowired
    private OphthalmicInspectionMapper ophthalmicInspectionMapper;

    @Autowired
    private OphthalmicInspectionService ophthalmicInspectionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOphthalmicInspectionMockMvc;

    private OphthalmicInspection ophthalmicInspection;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OphthalmicInspection createEntity(EntityManager em) {
        OphthalmicInspection ophthalmicInspection = new OphthalmicInspection()
            .rigthEye(DEFAULT_RIGTH_EYE)
            .leftEye(DEFAULT_LEFT_EYE)
            .doctorComments(DEFAULT_DOCTOR_COMMENTS);
        return ophthalmicInspection;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OphthalmicInspection createUpdatedEntity(EntityManager em) {
        OphthalmicInspection ophthalmicInspection = new OphthalmicInspection()
            .rigthEye(UPDATED_RIGTH_EYE)
            .leftEye(UPDATED_LEFT_EYE)
            .doctorComments(UPDATED_DOCTOR_COMMENTS);
        return ophthalmicInspection;
    }

    @BeforeEach
    public void initTest() {
        ophthalmicInspection = createEntity(em);
    }

    @Test
    @Transactional
    public void createOphthalmicInspection() throws Exception {
        int databaseSizeBeforeCreate = ophthalmicInspectionRepository.findAll().size();
        // Create the OphthalmicInspection
        OphthalmicInspectionDTO ophthalmicInspectionDTO = ophthalmicInspectionMapper.toDto(ophthalmicInspection);
        restOphthalmicInspectionMockMvc.perform(post("/api/ophthalmic-inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ophthalmicInspectionDTO)))
            .andExpect(status().isCreated());

        // Validate the OphthalmicInspection in the database
        List<OphthalmicInspection> ophthalmicInspectionList = ophthalmicInspectionRepository.findAll();
        assertThat(ophthalmicInspectionList).hasSize(databaseSizeBeforeCreate + 1);
        OphthalmicInspection testOphthalmicInspection = ophthalmicInspectionList.get(ophthalmicInspectionList.size() - 1);
        assertThat(testOphthalmicInspection.getRigthEye()).isEqualTo(DEFAULT_RIGTH_EYE);
        assertThat(testOphthalmicInspection.getLeftEye()).isEqualTo(DEFAULT_LEFT_EYE);
        assertThat(testOphthalmicInspection.getDoctorComments()).isEqualTo(DEFAULT_DOCTOR_COMMENTS);
    }

    @Test
    @Transactional
    public void createOphthalmicInspectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ophthalmicInspectionRepository.findAll().size();

        // Create the OphthalmicInspection with an existing ID
        ophthalmicInspection.setId(1L);
        OphthalmicInspectionDTO ophthalmicInspectionDTO = ophthalmicInspectionMapper.toDto(ophthalmicInspection);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOphthalmicInspectionMockMvc.perform(post("/api/ophthalmic-inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ophthalmicInspectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OphthalmicInspection in the database
        List<OphthalmicInspection> ophthalmicInspectionList = ophthalmicInspectionRepository.findAll();
        assertThat(ophthalmicInspectionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOphthalmicInspections() throws Exception {
        // Initialize the database
        ophthalmicInspectionRepository.saveAndFlush(ophthalmicInspection);

        // Get all the ophthalmicInspectionList
        restOphthalmicInspectionMockMvc.perform(get("/api/ophthalmic-inspections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ophthalmicInspection.getId().intValue())))
            .andExpect(jsonPath("$.[*].rigthEye").value(hasItem(DEFAULT_RIGTH_EYE)))
            .andExpect(jsonPath("$.[*].leftEye").value(hasItem(DEFAULT_LEFT_EYE)))
            .andExpect(jsonPath("$.[*].doctorComments").value(hasItem(DEFAULT_DOCTOR_COMMENTS)));
    }
    
    @Test
    @Transactional
    public void getOphthalmicInspection() throws Exception {
        // Initialize the database
        ophthalmicInspectionRepository.saveAndFlush(ophthalmicInspection);

        // Get the ophthalmicInspection
        restOphthalmicInspectionMockMvc.perform(get("/api/ophthalmic-inspections/{id}", ophthalmicInspection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ophthalmicInspection.getId().intValue()))
            .andExpect(jsonPath("$.rigthEye").value(DEFAULT_RIGTH_EYE))
            .andExpect(jsonPath("$.leftEye").value(DEFAULT_LEFT_EYE))
            .andExpect(jsonPath("$.doctorComments").value(DEFAULT_DOCTOR_COMMENTS));
    }
    @Test
    @Transactional
    public void getNonExistingOphthalmicInspection() throws Exception {
        // Get the ophthalmicInspection
        restOphthalmicInspectionMockMvc.perform(get("/api/ophthalmic-inspections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOphthalmicInspection() throws Exception {
        // Initialize the database
        ophthalmicInspectionRepository.saveAndFlush(ophthalmicInspection);

        int databaseSizeBeforeUpdate = ophthalmicInspectionRepository.findAll().size();

        // Update the ophthalmicInspection
        OphthalmicInspection updatedOphthalmicInspection = ophthalmicInspectionRepository.findById(ophthalmicInspection.getId()).get();
        // Disconnect from session so that the updates on updatedOphthalmicInspection are not directly saved in db
        em.detach(updatedOphthalmicInspection);
        updatedOphthalmicInspection
            .rigthEye(UPDATED_RIGTH_EYE)
            .leftEye(UPDATED_LEFT_EYE)
            .doctorComments(UPDATED_DOCTOR_COMMENTS);
        OphthalmicInspectionDTO ophthalmicInspectionDTO = ophthalmicInspectionMapper.toDto(updatedOphthalmicInspection);

        restOphthalmicInspectionMockMvc.perform(put("/api/ophthalmic-inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ophthalmicInspectionDTO)))
            .andExpect(status().isOk());

        // Validate the OphthalmicInspection in the database
        List<OphthalmicInspection> ophthalmicInspectionList = ophthalmicInspectionRepository.findAll();
        assertThat(ophthalmicInspectionList).hasSize(databaseSizeBeforeUpdate);
        OphthalmicInspection testOphthalmicInspection = ophthalmicInspectionList.get(ophthalmicInspectionList.size() - 1);
        assertThat(testOphthalmicInspection.getRigthEye()).isEqualTo(UPDATED_RIGTH_EYE);
        assertThat(testOphthalmicInspection.getLeftEye()).isEqualTo(UPDATED_LEFT_EYE);
        assertThat(testOphthalmicInspection.getDoctorComments()).isEqualTo(UPDATED_DOCTOR_COMMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingOphthalmicInspection() throws Exception {
        int databaseSizeBeforeUpdate = ophthalmicInspectionRepository.findAll().size();

        // Create the OphthalmicInspection
        OphthalmicInspectionDTO ophthalmicInspectionDTO = ophthalmicInspectionMapper.toDto(ophthalmicInspection);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOphthalmicInspectionMockMvc.perform(put("/api/ophthalmic-inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ophthalmicInspectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OphthalmicInspection in the database
        List<OphthalmicInspection> ophthalmicInspectionList = ophthalmicInspectionRepository.findAll();
        assertThat(ophthalmicInspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOphthalmicInspection() throws Exception {
        // Initialize the database
        ophthalmicInspectionRepository.saveAndFlush(ophthalmicInspection);

        int databaseSizeBeforeDelete = ophthalmicInspectionRepository.findAll().size();

        // Delete the ophthalmicInspection
        restOphthalmicInspectionMockMvc.perform(delete("/api/ophthalmic-inspections/{id}", ophthalmicInspection.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OphthalmicInspection> ophthalmicInspectionList = ophthalmicInspectionRepository.findAll();
        assertThat(ophthalmicInspectionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
