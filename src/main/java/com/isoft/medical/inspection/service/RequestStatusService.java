package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.RequestStatus;
import com.isoft.medical.inspection.repository.RequestStatusRepository;
import com.isoft.medical.inspection.service.dto.RequestStatusDTO;
import com.isoft.medical.inspection.service.mapper.RequestStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RequestStatus}.
 */
@Service
@Transactional
public class RequestStatusService {

    private final Logger log = LoggerFactory.getLogger(RequestStatusService.class);

    private final RequestStatusRepository requestStatusRepository;

    private final RequestStatusMapper requestStatusMapper;

    public RequestStatusService(RequestStatusRepository requestStatusRepository, RequestStatusMapper requestStatusMapper) {
        this.requestStatusRepository = requestStatusRepository;
        this.requestStatusMapper = requestStatusMapper;
    }

    /**
     * Save a requestStatus.
     *
     * @param requestStatusDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestStatusDTO save(RequestStatusDTO requestStatusDTO) {
        log.debug("Request to save RequestStatus : {}", requestStatusDTO);
        RequestStatus requestStatus = requestStatusMapper.toEntity(requestStatusDTO);
        requestStatus = requestStatusRepository.save(requestStatus);
        return requestStatusMapper.toDto(requestStatus);
    }

    /**
     * Get all the requestStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestStatuses");
        return requestStatusRepository.findAll(pageable)
            .map(requestStatusMapper::toDto);
    }


    /**
     * Get one requestStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequestStatusDTO> findOne(Long id) {
        log.debug("Request to get RequestStatus : {}", id);
        return requestStatusRepository.findById(id)
            .map(requestStatusMapper::toDto);
    }

    /**
     * Delete the requestStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RequestStatus : {}", id);
        requestStatusRepository.deleteById(id);
    }
}
