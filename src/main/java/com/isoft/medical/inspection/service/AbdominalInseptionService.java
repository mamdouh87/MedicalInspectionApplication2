package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.AbdominalInseption;
import com.isoft.medical.inspection.repository.AbdominalInseptionRepository;
import com.isoft.medical.inspection.service.dto.AbdominalInseptionDTO;
import com.isoft.medical.inspection.service.mapper.AbdominalInseptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AbdominalInseption}.
 */
@Service
@Transactional
public class AbdominalInseptionService {

    private final Logger log = LoggerFactory.getLogger(AbdominalInseptionService.class);

    private final AbdominalInseptionRepository abdominalInseptionRepository;

    private final AbdominalInseptionMapper abdominalInseptionMapper;

    public AbdominalInseptionService(AbdominalInseptionRepository abdominalInseptionRepository, AbdominalInseptionMapper abdominalInseptionMapper) {
        this.abdominalInseptionRepository = abdominalInseptionRepository;
        this.abdominalInseptionMapper = abdominalInseptionMapper;
    }

    /**
     * Save a abdominalInseption.
     *
     * @param abdominalInseptionDTO the entity to save.
     * @return the persisted entity.
     */
    public AbdominalInseptionDTO save(AbdominalInseptionDTO abdominalInseptionDTO) {
        log.debug("Request to save AbdominalInseption : {}", abdominalInseptionDTO);
        AbdominalInseption abdominalInseption = abdominalInseptionMapper.toEntity(abdominalInseptionDTO);
        abdominalInseption = abdominalInseptionRepository.save(abdominalInseption);
        return abdominalInseptionMapper.toDto(abdominalInseption);
    }

    /**
     * Get all the abdominalInseptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AbdominalInseptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AbdominalInseptions");
        return abdominalInseptionRepository.findAll(pageable)
            .map(abdominalInseptionMapper::toDto);
    }


    /**
     * Get one abdominalInseption by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AbdominalInseptionDTO> findOne(Long id) {
        log.debug("Request to get AbdominalInseption : {}", id);
        return abdominalInseptionRepository.findById(id)
            .map(abdominalInseptionMapper::toDto);
    }

    /**
     * Delete the abdominalInseption by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AbdominalInseption : {}", id);
        abdominalInseptionRepository.deleteById(id);
    }
}
