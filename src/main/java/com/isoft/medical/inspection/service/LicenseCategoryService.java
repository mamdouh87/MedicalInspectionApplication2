package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.LicenseCategory;
import com.isoft.medical.inspection.repository.LicenseCategoryRepository;
import com.isoft.medical.inspection.service.dto.LicenseCategoryDTO;
import com.isoft.medical.inspection.service.mapper.LicenseCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LicenseCategory}.
 */
@Service
@Transactional
public class LicenseCategoryService {

    private final Logger log = LoggerFactory.getLogger(LicenseCategoryService.class);

    private final LicenseCategoryRepository licenseCategoryRepository;

    private final LicenseCategoryMapper licenseCategoryMapper;

    public LicenseCategoryService(LicenseCategoryRepository licenseCategoryRepository, LicenseCategoryMapper licenseCategoryMapper) {
        this.licenseCategoryRepository = licenseCategoryRepository;
        this.licenseCategoryMapper = licenseCategoryMapper;
    }

    /**
     * Save a licenseCategory.
     *
     * @param licenseCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public LicenseCategoryDTO save(LicenseCategoryDTO licenseCategoryDTO) {
        log.debug("Request to save LicenseCategory : {}", licenseCategoryDTO);
        LicenseCategory licenseCategory = licenseCategoryMapper.toEntity(licenseCategoryDTO);
        licenseCategory = licenseCategoryRepository.save(licenseCategory);
        return licenseCategoryMapper.toDto(licenseCategory);
    }

    /**
     * Get all the licenseCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LicenseCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LicenseCategories");
        return licenseCategoryRepository.findAll(pageable)
            .map(licenseCategoryMapper::toDto);
    }


    /**
     * Get one licenseCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LicenseCategoryDTO> findOne(Long id) {
        log.debug("Request to get LicenseCategory : {}", id);
        return licenseCategoryRepository.findById(id)
            .map(licenseCategoryMapper::toDto);
    }

    /**
     * Delete the licenseCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LicenseCategory : {}", id);
        licenseCategoryRepository.deleteById(id);
    }
}
