package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.LicenseCategoryService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.LicenseCategoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.isoft.medical.inspection.domain.LicenseCategory}.
 */
@RestController
@RequestMapping("/api")
public class LicenseCategoryResource {

    private final Logger log = LoggerFactory.getLogger(LicenseCategoryResource.class);

    private static final String ENTITY_NAME = "licenseCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LicenseCategoryService licenseCategoryService;

    public LicenseCategoryResource(LicenseCategoryService licenseCategoryService) {
        this.licenseCategoryService = licenseCategoryService;
    }

    /**
     * {@code POST  /license-categories} : Create a new licenseCategory.
     *
     * @param licenseCategoryDTO the licenseCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new licenseCategoryDTO, or with status {@code 400 (Bad Request)} if the licenseCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/license-categories")
    public ResponseEntity<LicenseCategoryDTO> createLicenseCategory(@RequestBody LicenseCategoryDTO licenseCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save LicenseCategory : {}", licenseCategoryDTO);
        if (licenseCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new licenseCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LicenseCategoryDTO result = licenseCategoryService.save(licenseCategoryDTO);
        return ResponseEntity.created(new URI("/api/license-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /license-categories} : Updates an existing licenseCategory.
     *
     * @param licenseCategoryDTO the licenseCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licenseCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the licenseCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the licenseCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/license-categories")
    public ResponseEntity<LicenseCategoryDTO> updateLicenseCategory(@RequestBody LicenseCategoryDTO licenseCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update LicenseCategory : {}", licenseCategoryDTO);
        if (licenseCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LicenseCategoryDTO result = licenseCategoryService.save(licenseCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licenseCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /license-categories} : get all the licenseCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of licenseCategories in body.
     */
    @GetMapping("/license-categories")
    public ResponseEntity<List<LicenseCategoryDTO>> getAllLicenseCategories(Pageable pageable) {
        log.debug("REST request to get a page of LicenseCategories");
        Page<LicenseCategoryDTO> page = licenseCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /license-categories/:id} : get the "id" licenseCategory.
     *
     * @param id the id of the licenseCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the licenseCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/license-categories/{id}")
    public ResponseEntity<LicenseCategoryDTO> getLicenseCategory(@PathVariable Long id) {
        log.debug("REST request to get LicenseCategory : {}", id);
        Optional<LicenseCategoryDTO> licenseCategoryDTO = licenseCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(licenseCategoryDTO);
    }

    /**
     * {@code DELETE  /license-categories/:id} : delete the "id" licenseCategory.
     *
     * @param id the id of the licenseCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/license-categories/{id}")
    public ResponseEntity<Void> deleteLicenseCategory(@PathVariable Long id) {
        log.debug("REST request to delete LicenseCategory : {}", id);
        licenseCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
