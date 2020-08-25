package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.InspectionRequirement;
import com.isoft.medical.inspection.repository.InspectionRequirementRepository;
import com.isoft.medical.inspection.service.dto.InspectionRequirementDTO;
import com.isoft.medical.inspection.service.mapper.InspectionRequirementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InspectionRequirement}.
 */
@Service
@Transactional
public class InspectionRequirementService {

    private final Logger log = LoggerFactory.getLogger(InspectionRequirementService.class);

    private final InspectionRequirementRepository inspectionRequirementRepository;

    private final InspectionRequirementMapper inspectionRequirementMapper;

    public InspectionRequirementService(InspectionRequirementRepository inspectionRequirementRepository, InspectionRequirementMapper inspectionRequirementMapper) {
        this.inspectionRequirementRepository = inspectionRequirementRepository;
        this.inspectionRequirementMapper = inspectionRequirementMapper;
    }

    /**
     * Save a inspectionRequirement.
     *
     * @param inspectionRequirementDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionRequirementDTO save(InspectionRequirementDTO inspectionRequirementDTO) {
        log.debug("Request to save InspectionRequirement : {}", inspectionRequirementDTO);
        InspectionRequirement inspectionRequirement = inspectionRequirementMapper.toEntity(inspectionRequirementDTO);
        inspectionRequirement = inspectionRequirementRepository.save(inspectionRequirement);
        return inspectionRequirementMapper.toDto(inspectionRequirement);
    }

    /**
     * Get all the inspectionRequirements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionRequirementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionRequirements");
        return inspectionRequirementRepository.findAll(pageable)
            .map(inspectionRequirementMapper::toDto);
    }


    /**
     * Get one inspectionRequirement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionRequirementDTO> findOne(Long id) {
        log.debug("Request to get InspectionRequirement : {}", id);
        return inspectionRequirementRepository.findById(id)
            .map(inspectionRequirementMapper::toDto);
    }

    /**
     * Delete the inspectionRequirement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionRequirement : {}", id);
        inspectionRequirementRepository.deleteById(id);
    }
}
