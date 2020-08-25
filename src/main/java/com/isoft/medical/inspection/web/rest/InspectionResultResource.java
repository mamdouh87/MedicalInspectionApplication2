package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.InspectionResultService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.InspectionResultDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.InspectionResult}.
 */
@RestController
@RequestMapping("/api")
public class InspectionResultResource {

    private final Logger log = LoggerFactory.getLogger(InspectionResultResource.class);

    private static final String ENTITY_NAME = "inspectionResult";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionResultService inspectionResultService;

    public InspectionResultResource(InspectionResultService inspectionResultService) {
        this.inspectionResultService = inspectionResultService;
    }

    /**
     * {@code POST  /inspection-results} : Create a new inspectionResult.
     *
     * @param inspectionResultDTO the inspectionResultDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionResultDTO, or with status {@code 400 (Bad Request)} if the inspectionResult has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-results")
    public ResponseEntity<InspectionResultDTO> createInspectionResult(@RequestBody InspectionResultDTO inspectionResultDTO) throws URISyntaxException {
        log.debug("REST request to save InspectionResult : {}", inspectionResultDTO);
        if (inspectionResultDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionResult cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionResultDTO result = inspectionResultService.save(inspectionResultDTO);
        return ResponseEntity.created(new URI("/api/inspection-results/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-results} : Updates an existing inspectionResult.
     *
     * @param inspectionResultDTO the inspectionResultDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionResultDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionResultDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionResultDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-results")
    public ResponseEntity<InspectionResultDTO> updateInspectionResult(@RequestBody InspectionResultDTO inspectionResultDTO) throws URISyntaxException {
        log.debug("REST request to update InspectionResult : {}", inspectionResultDTO);
        if (inspectionResultDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionResultDTO result = inspectionResultService.save(inspectionResultDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inspectionResultDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-results} : get all the inspectionResults.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionResults in body.
     */
    @GetMapping("/inspection-results")
    public ResponseEntity<List<InspectionResultDTO>> getAllInspectionResults(Pageable pageable) {
        log.debug("REST request to get a page of InspectionResults");
        Page<InspectionResultDTO> page = inspectionResultService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-results/:id} : get the "id" inspectionResult.
     *
     * @param id the id of the inspectionResultDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionResultDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-results/{id}")
    public ResponseEntity<InspectionResultDTO> getInspectionResult(@PathVariable Long id) {
        log.debug("REST request to get InspectionResult : {}", id);
        Optional<InspectionResultDTO> inspectionResultDTO = inspectionResultService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionResultDTO);
    }

    /**
     * {@code DELETE  /inspection-results/:id} : delete the "id" inspectionResult.
     *
     * @param id the id of the inspectionResultDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-results/{id}")
    public ResponseEntity<Void> deleteInspectionResult(@PathVariable Long id) {
        log.debug("REST request to delete InspectionResult : {}", id);
        inspectionResultService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
