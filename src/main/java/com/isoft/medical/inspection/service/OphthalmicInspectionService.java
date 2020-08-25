package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.OphthalmicInspection;
import com.isoft.medical.inspection.repository.OphthalmicInspectionRepository;
import com.isoft.medical.inspection.service.dto.OphthalmicInspectionDTO;
import com.isoft.medical.inspection.service.mapper.OphthalmicInspectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OphthalmicInspection}.
 */
@Service
@Transactional
public class OphthalmicInspectionService {

    private final Logger log = LoggerFactory.getLogger(OphthalmicInspectionService.class);

    private final OphthalmicInspectionRepository ophthalmicInspectionRepository;

    private final OphthalmicInspectionMapper ophthalmicInspectionMapper;

    public OphthalmicInspectionService(OphthalmicInspectionRepository ophthalmicInspectionRepository, OphthalmicInspectionMapper ophthalmicInspectionMapper) {
        this.ophthalmicInspectionRepository = ophthalmicInspectionRepository;
        this.ophthalmicInspectionMapper = ophthalmicInspectionMapper;
    }

    /**
     * Save a ophthalmicInspection.
     *
     * @param ophthalmicInspectionDTO the entity to save.
     * @return the persisted entity.
     */
    public OphthalmicInspectionDTO save(OphthalmicInspectionDTO ophthalmicInspectionDTO) {
        log.debug("Request to save OphthalmicInspection : {}", ophthalmicInspectionDTO);
        OphthalmicInspection ophthalmicInspection = ophthalmicInspectionMapper.toEntity(ophthalmicInspectionDTO);
        ophthalmicInspection = ophthalmicInspectionRepository.save(ophthalmicInspection);
        return ophthalmicInspectionMapper.toDto(ophthalmicInspection);
    }

    /**
     * Get all the ophthalmicInspections.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OphthalmicInspectionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OphthalmicInspections");
        return ophthalmicInspectionRepository.findAll(pageable)
            .map(ophthalmicInspectionMapper::toDto);
    }


    /**
     * Get one ophthalmicInspection by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OphthalmicInspectionDTO> findOne(Long id) {
        log.debug("Request to get OphthalmicInspection : {}", id);
        return ophthalmicInspectionRepository.findById(id)
            .map(ophthalmicInspectionMapper::toDto);
    }

    /**
     * Delete the ophthalmicInspection by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OphthalmicInspection : {}", id);
        ophthalmicInspectionRepository.deleteById(id);
    }
}
