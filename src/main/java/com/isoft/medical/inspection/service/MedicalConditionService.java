package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.MedicalCondition;
import com.isoft.medical.inspection.repository.MedicalConditionRepository;
import com.isoft.medical.inspection.service.dto.MedicalConditionDTO;
import com.isoft.medical.inspection.service.mapper.MedicalConditionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalCondition}.
 */
@Service
@Transactional
public class MedicalConditionService {

    private final Logger log = LoggerFactory.getLogger(MedicalConditionService.class);

    private final MedicalConditionRepository medicalConditionRepository;

    private final MedicalConditionMapper medicalConditionMapper;

    public MedicalConditionService(MedicalConditionRepository medicalConditionRepository, MedicalConditionMapper medicalConditionMapper) {
        this.medicalConditionRepository = medicalConditionRepository;
        this.medicalConditionMapper = medicalConditionMapper;
    }

    /**
     * Save a medicalCondition.
     *
     * @param medicalConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public MedicalConditionDTO save(MedicalConditionDTO medicalConditionDTO) {
        log.debug("Request to save MedicalCondition : {}", medicalConditionDTO);
        MedicalCondition medicalCondition = medicalConditionMapper.toEntity(medicalConditionDTO);
        medicalCondition = medicalConditionRepository.save(medicalCondition);
        return medicalConditionMapper.toDto(medicalCondition);
    }

    /**
     * Get all the medicalConditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MedicalConditionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalConditions");
        return medicalConditionRepository.findAll(pageable)
            .map(medicalConditionMapper::toDto);
    }


    /**
     * Get one medicalCondition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedicalConditionDTO> findOne(Long id) {
        log.debug("Request to get MedicalCondition : {}", id);
        return medicalConditionRepository.findById(id)
            .map(medicalConditionMapper::toDto);
    }

    /**
     * Delete the medicalCondition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicalCondition : {}", id);
        medicalConditionRepository.deleteById(id);
    }
}
