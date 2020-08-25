package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.OphthalmicInspectionService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.OphthalmicInspectionDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.OphthalmicInspection}.
 */
@RestController
@RequestMapping("/api")
public class OphthalmicInspectionResource {

    private final Logger log = LoggerFactory.getLogger(OphthalmicInspectionResource.class);

    private static final String ENTITY_NAME = "ophthalmicInspection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OphthalmicInspectionService ophthalmicInspectionService;

    public OphthalmicInspectionResource(OphthalmicInspectionService ophthalmicInspectionService) {
        this.ophthalmicInspectionService = ophthalmicInspectionService;
    }

    /**
     * {@code POST  /ophthalmic-inspections} : Create a new ophthalmicInspection.
     *
     * @param ophthalmicInspectionDTO the ophthalmicInspectionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ophthalmicInspectionDTO, or with status {@code 400 (Bad Request)} if the ophthalmicInspection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ophthalmic-inspections")
    public ResponseEntity<OphthalmicInspectionDTO> createOphthalmicInspection(@RequestBody OphthalmicInspectionDTO ophthalmicInspectionDTO) throws URISyntaxException {
        log.debug("REST request to save OphthalmicInspection : {}", ophthalmicInspectionDTO);
        if (ophthalmicInspectionDTO.getId() != null) {
            throw new BadRequestAlertException("A new ophthalmicInspection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OphthalmicInspectionDTO result = ophthalmicInspectionService.save(ophthalmicInspectionDTO);
        return ResponseEntity.created(new URI("/api/ophthalmic-inspections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ophthalmic-inspections} : Updates an existing ophthalmicInspection.
     *
     * @param ophthalmicInspectionDTO the ophthalmicInspectionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ophthalmicInspectionDTO,
     * or with status {@code 400 (Bad Request)} if the ophthalmicInspectionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ophthalmicInspectionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ophthalmic-inspections")
    public ResponseEntity<OphthalmicInspectionDTO> updateOphthalmicInspection(@RequestBody OphthalmicInspectionDTO ophthalmicInspectionDTO) throws URISyntaxException {
        log.debug("REST request to update OphthalmicInspection : {}", ophthalmicInspectionDTO);
        if (ophthalmicInspectionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OphthalmicInspectionDTO result = ophthalmicInspectionService.save(ophthalmicInspectionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ophthalmicInspectionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ophthalmic-inspections} : get all the ophthalmicInspections.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ophthalmicInspections in body.
     */
    @GetMapping("/ophthalmic-inspections")
    public ResponseEntity<List<OphthalmicInspectionDTO>> getAllOphthalmicInspections(Pageable pageable) {
        log.debug("REST request to get a page of OphthalmicInspections");
        Page<OphthalmicInspectionDTO> page = ophthalmicInspectionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ophthalmic-inspections/:id} : get the "id" ophthalmicInspection.
     *
     * @param id the id of the ophthalmicInspectionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ophthalmicInspectionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ophthalmic-inspections/{id}")
    public ResponseEntity<OphthalmicInspectionDTO> getOphthalmicInspection(@PathVariable Long id) {
        log.debug("REST request to get OphthalmicInspection : {}", id);
        Optional<OphthalmicInspectionDTO> ophthalmicInspectionDTO = ophthalmicInspectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ophthalmicInspectionDTO);
    }

    /**
     * {@code DELETE  /ophthalmic-inspections/:id} : delete the "id" ophthalmicInspection.
     *
     * @param id the id of the ophthalmicInspectionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ophthalmic-inspections/{id}")
    public ResponseEntity<Void> deleteOphthalmicInspection(@PathVariable Long id) {
        log.debug("REST request to delete OphthalmicInspection : {}", id);
        ophthalmicInspectionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
