package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.MedicalInsepctionRequests;
import com.isoft.medical.inspection.repository.MedicalInsepctionRequestsRepository;
import com.isoft.medical.inspection.service.dto.MedicalInsepctionRequestsDTO;
import com.isoft.medical.inspection.service.mapper.MedicalInsepctionRequestsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalInsepctionRequests}.
 */
@Service
@Transactional
public class MedicalInsepctionRequestsService {

    private final Logger log = LoggerFactory.getLogger(MedicalInsepctionRequestsService.class);

    private final MedicalInsepctionRequestsRepository medicalInsepctionRequestsRepository;

    private final MedicalInsepctionRequestsMapper medicalInsepctionRequestsMapper;

    public MedicalInsepctionRequestsService(MedicalInsepctionRequestsRepository medicalInsepctionRequestsRepository, MedicalInsepctionRequestsMapper medicalInsepctionRequestsMapper) {
        this.medicalInsepctionRequestsRepository = medicalInsepctionRequestsRepository;
        this.medicalInsepctionRequestsMapper = medicalInsepctionRequestsMapper;
    }

    /**
     * Save a medicalInsepctionRequests.
     *
     * @param medicalInsepctionRequestsDTO the entity to save.
     * @return the persisted entity.
     */
    public MedicalInsepctionRequestsDTO save(MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO) {
        log.debug("Request to save MedicalInsepctionRequests : {}", medicalInsepctionRequestsDTO);
        MedicalInsepctionRequests medicalInsepctionRequests = medicalInsepctionRequestsMapper.toEntity(medicalInsepctionRequestsDTO);
        medicalInsepctionRequests = medicalInsepctionRequestsRepository.save(medicalInsepctionRequests);
        return medicalInsepctionRequestsMapper.toDto(medicalInsepctionRequests);
    }

    /**
     * Get all the medicalInsepctionRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MedicalInsepctionRequestsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalInsepctionRequests");
        return medicalInsepctionRequestsRepository.findAll(pageable)
            .map(medicalInsepctionRequestsMapper::toDto);
    }


    /**
     * Get all the medicalInsepctionRequests with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MedicalInsepctionRequestsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return medicalInsepctionRequestsRepository.findAllWithEagerRelationships(pageable).map(medicalInsepctionRequestsMapper::toDto);
    }

    /**
     * Get one medicalInsepctionRequests by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedicalInsepctionRequestsDTO> findOne(Long id) {
        log.debug("Request to get MedicalInsepctionRequests : {}", id);
        return medicalInsepctionRequestsRepository.findOneWithEagerRelationships(id)
            .map(medicalInsepctionRequestsMapper::toDto);
    }

    /**
     * Delete the medicalInsepctionRequests by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicalInsepctionRequests : {}", id);
        medicalInsepctionRequestsRepository.deleteById(id);
    }
}
