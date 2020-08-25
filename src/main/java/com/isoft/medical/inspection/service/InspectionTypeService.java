package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.InspectionType;
import com.isoft.medical.inspection.repository.InspectionTypeRepository;
import com.isoft.medical.inspection.service.dto.InspectionTypeDTO;
import com.isoft.medical.inspection.service.mapper.InspectionTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InspectionType}.
 */
@Service
@Transactional
public class InspectionTypeService {

    private final Logger log = LoggerFactory.getLogger(InspectionTypeService.class);

    private final InspectionTypeRepository inspectionTypeRepository;

    private final InspectionTypeMapper inspectionTypeMapper;

    public InspectionTypeService(InspectionTypeRepository inspectionTypeRepository, InspectionTypeMapper inspectionTypeMapper) {
        this.inspectionTypeRepository = inspectionTypeRepository;
        this.inspectionTypeMapper = inspectionTypeMapper;
    }

    /**
     * Save a inspectionType.
     *
     * @param inspectionTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionTypeDTO save(InspectionTypeDTO inspectionTypeDTO) {
        log.debug("Request to save InspectionType : {}", inspectionTypeDTO);
        InspectionType inspectionType = inspectionTypeMapper.toEntity(inspectionTypeDTO);
        inspectionType = inspectionTypeRepository.save(inspectionType);
        return inspectionTypeMapper.toDto(inspectionType);
    }

    /**
     * Get all the inspectionTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionTypes");
        return inspectionTypeRepository.findAll(pageable)
            .map(inspectionTypeMapper::toDto);
    }


    /**
     * Get one inspectionType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionTypeDTO> findOne(Long id) {
        log.debug("Request to get InspectionType : {}", id);
        return inspectionTypeRepository.findById(id)
            .map(inspectionTypeMapper::toDto);
    }

    /**
     * Delete the inspectionType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionType : {}", id);
        inspectionTypeRepository.deleteById(id);
    }
}
