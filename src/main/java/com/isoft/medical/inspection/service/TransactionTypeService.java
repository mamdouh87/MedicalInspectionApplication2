package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.TransactionType;
import com.isoft.medical.inspection.repository.TransactionTypeRepository;
import com.isoft.medical.inspection.service.dto.TransactionTypeDTO;
import com.isoft.medical.inspection.service.mapper.TransactionTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TransactionType}.
 */
@Service
@Transactional
public class TransactionTypeService {

    private final Logger log = LoggerFactory.getLogger(TransactionTypeService.class);

    private final TransactionTypeRepository transactionTypeRepository;

    private final TransactionTypeMapper transactionTypeMapper;

    public TransactionTypeService(TransactionTypeRepository transactionTypeRepository, TransactionTypeMapper transactionTypeMapper) {
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionTypeMapper = transactionTypeMapper;
    }

    /**
     * Save a transactionType.
     *
     * @param transactionTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public TransactionTypeDTO save(TransactionTypeDTO transactionTypeDTO) {
        log.debug("Request to save TransactionType : {}", transactionTypeDTO);
        TransactionType transactionType = transactionTypeMapper.toEntity(transactionTypeDTO);
        transactionType = transactionTypeRepository.save(transactionType);
        return transactionTypeMapper.toDto(transactionType);
    }

    /**
     * Get all the transactionTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TransactionTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TransactionTypes");
        return transactionTypeRepository.findAll(pageable)
            .map(transactionTypeMapper::toDto);
    }


    /**
     * Get one transactionType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TransactionTypeDTO> findOne(Long id) {
        log.debug("Request to get TransactionType : {}", id);
        return transactionTypeRepository.findById(id)
            .map(transactionTypeMapper::toDto);
    }

    /**
     * Delete the transactionType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TransactionType : {}", id);
        transactionTypeRepository.deleteById(id);
    }
}
