package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.InspectionResult;
import com.isoft.medical.inspection.repository.InspectionResultRepository;
import com.isoft.medical.inspection.service.dto.InspectionResultDTO;
import com.isoft.medical.inspection.service.mapper.InspectionResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InspectionResult}.
 */
@Service
@Transactional
public class InspectionResultService {

    private final Logger log = LoggerFactory.getLogger(InspectionResultService.class);

    private final InspectionResultRepository inspectionResultRepository;

    private final InspectionResultMapper inspectionResultMapper;

    public InspectionResultService(InspectionResultRepository inspectionResultRepository, InspectionResultMapper inspectionResultMapper) {
        this.inspectionResultRepository = inspectionResultRepository;
        this.inspectionResultMapper = inspectionResultMapper;
    }

    /**
     * Save a inspectionResult.
     *
     * @param inspectionResultDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionResultDTO save(InspectionResultDTO inspectionResultDTO) {
        log.debug("Request to save InspectionResult : {}", inspectionResultDTO);
        InspectionResult inspectionResult = inspectionResultMapper.toEntity(inspectionResultDTO);
        inspectionResult = inspectionResultRepository.save(inspectionResult);
        return inspectionResultMapper.toDto(inspectionResult);
    }

    /**
     * Get all the inspectionResults.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionResultDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionResults");
        return inspectionResultRepository.findAll(pageable)
            .map(inspectionResultMapper::toDto);
    }


    /**
     * Get one inspectionResult by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionResultDTO> findOne(Long id) {
        log.debug("Request to get InspectionResult : {}", id);
        return inspectionResultRepository.findById(id)
            .map(inspectionResultMapper::toDto);
    }

    /**
     * Delete the inspectionResult by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionResult : {}", id);
        inspectionResultRepository.deleteById(id);
    }
}
