package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.InspectionRequirementService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.InspectionRequirementDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.InspectionRequirement}.
 */
@RestController
@RequestMapping("/api")
public class InspectionRequirementResource {

    private final Logger log = LoggerFactory.getLogger(InspectionRequirementResource.class);

    private static final String ENTITY_NAME = "inspectionRequirement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionRequirementService inspectionRequirementService;

    public InspectionRequirementResource(InspectionRequirementService inspectionRequirementService) {
        this.inspectionRequirementService = inspectionRequirementService;
    }

    /**
     * {@code POST  /inspection-requirements} : Create a new inspectionRequirement.
     *
     * @param inspectionRequirementDTO the inspectionRequirementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionRequirementDTO, or with status {@code 400 (Bad Request)} if the inspectionRequirement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-requirements")
    public ResponseEntity<InspectionRequirementDTO> createInspectionRequirement(@RequestBody InspectionRequirementDTO inspectionRequirementDTO) throws URISyntaxException {
        log.debug("REST request to save InspectionRequirement : {}", inspectionRequirementDTO);
        if (inspectionRequirementDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionRequirement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionRequirementDTO result = inspectionRequirementService.save(inspectionRequirementDTO);
        return ResponseEntity.created(new URI("/api/inspection-requirements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-requirements} : Updates an existing inspectionRequirement.
     *
     * @param inspectionRequirementDTO the inspectionRequirementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionRequirementDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionRequirementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionRequirementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-requirements")
    public ResponseEntity<InspectionRequirementDTO> updateInspectionRequirement(@RequestBody InspectionRequirementDTO inspectionRequirementDTO) throws URISyntaxException {
        log.debug("REST request to update InspectionRequirement : {}", inspectionRequirementDTO);
        if (inspectionRequirementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionRequirementDTO result = inspectionRequirementService.save(inspectionRequirementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inspectionRequirementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-requirements} : get all the inspectionRequirements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionRequirements in body.
     */
    @GetMapping("/inspection-requirements")
    public ResponseEntity<List<InspectionRequirementDTO>> getAllInspectionRequirements(Pageable pageable) {
        log.debug("REST request to get a page of InspectionRequirements");
        Page<InspectionRequirementDTO> page = inspectionRequirementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-requirements/:id} : get the "id" inspectionRequirement.
     *
     * @param id the id of the inspectionRequirementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionRequirementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-requirements/{id}")
    public ResponseEntity<InspectionRequirementDTO> getInspectionRequirement(@PathVariable Long id) {
        log.debug("REST request to get InspectionRequirement : {}", id);
        Optional<InspectionRequirementDTO> inspectionRequirementDTO = inspectionRequirementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionRequirementDTO);
    }

    /**
     * {@code DELETE  /inspection-requirements/:id} : delete the "id" inspectionRequirement.
     *
     * @param id the id of the inspectionRequirementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-requirements/{id}")
    public ResponseEntity<Void> deleteInspectionRequirement(@PathVariable Long id) {
        log.debug("REST request to delete InspectionRequirement : {}", id);
        inspectionRequirementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
