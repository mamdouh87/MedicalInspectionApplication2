package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.RequestBiometricData;
import com.isoft.medical.inspection.repository.RequestBiometricDataRepository;
import com.isoft.medical.inspection.service.dto.RequestBiometricDataDTO;
import com.isoft.medical.inspection.service.mapper.RequestBiometricDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RequestBiometricData}.
 */
@Service
@Transactional
public class RequestBiometricDataService {

    private final Logger log = LoggerFactory.getLogger(RequestBiometricDataService.class);

    private final RequestBiometricDataRepository requestBiometricDataRepository;

    private final RequestBiometricDataMapper requestBiometricDataMapper;

    public RequestBiometricDataService(RequestBiometricDataRepository requestBiometricDataRepository, RequestBiometricDataMapper requestBiometricDataMapper) {
        this.requestBiometricDataRepository = requestBiometricDataRepository;
        this.requestBiometricDataMapper = requestBiometricDataMapper;
    }

    /**
     * Save a requestBiometricData.
     *
     * @param requestBiometricDataDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestBiometricDataDTO save(RequestBiometricDataDTO requestBiometricDataDTO) {
        log.debug("Request to save RequestBiometricData : {}", requestBiometricDataDTO);
        RequestBiometricData requestBiometricData = requestBiometricDataMapper.toEntity(requestBiometricDataDTO);
        requestBiometricData = requestBiometricDataRepository.save(requestBiometricData);
        return requestBiometricDataMapper.toDto(requestBiometricData);
    }

    /**
     * Get all the requestBiometricData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestBiometricDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestBiometricData");
        return requestBiometricDataRepository.findAll(pageable)
            .map(requestBiometricDataMapper::toDto);
    }


    /**
     * Get one requestBiometricData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequestBiometricDataDTO> findOne(Long id) {
        log.debug("Request to get RequestBiometricData : {}", id);
        return requestBiometricDataRepository.findById(id)
            .map(requestBiometricDataMapper::toDto);
    }

    /**
     * Delete the requestBiometricData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RequestBiometricData : {}", id);
        requestBiometricDataRepository.deleteById(id);
    }
}
