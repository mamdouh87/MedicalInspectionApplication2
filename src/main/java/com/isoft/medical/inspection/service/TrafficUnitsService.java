package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.TrafficUnits;
import com.isoft.medical.inspection.repository.TrafficUnitsRepository;
import com.isoft.medical.inspection.service.dto.TrafficUnitsDTO;
import com.isoft.medical.inspection.service.mapper.TrafficUnitsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TrafficUnits}.
 */
@Service
@Transactional
public class TrafficUnitsService {

    private final Logger log = LoggerFactory.getLogger(TrafficUnitsService.class);

    private final TrafficUnitsRepository trafficUnitsRepository;

    private final TrafficUnitsMapper trafficUnitsMapper;

    public TrafficUnitsService(TrafficUnitsRepository trafficUnitsRepository, TrafficUnitsMapper trafficUnitsMapper) {
        this.trafficUnitsRepository = trafficUnitsRepository;
        this.trafficUnitsMapper = trafficUnitsMapper;
    }

    /**
     * Save a trafficUnits.
     *
     * @param trafficUnitsDTO the entity to save.
     * @return the persisted entity.
     */
    public TrafficUnitsDTO save(TrafficUnitsDTO trafficUnitsDTO) {
        log.debug("Request to save TrafficUnits : {}", trafficUnitsDTO);
        TrafficUnits trafficUnits = trafficUnitsMapper.toEntity(trafficUnitsDTO);
        trafficUnits = trafficUnitsRepository.save(trafficUnits);
        return trafficUnitsMapper.toDto(trafficUnits);
    }

    /**
     * Get all the trafficUnits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrafficUnitsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrafficUnits");
        return trafficUnitsRepository.findAll(pageable)
            .map(trafficUnitsMapper::toDto);
    }


    /**
     * Get one trafficUnits by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrafficUnitsDTO> findOne(Long id) {
        log.debug("Request to get TrafficUnits : {}", id);
        return trafficUnitsRepository.findById(id)
            .map(trafficUnitsMapper::toDto);
    }

    /**
     * Delete the trafficUnits by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TrafficUnits : {}", id);
        trafficUnitsRepository.deleteById(id);
    }
}
