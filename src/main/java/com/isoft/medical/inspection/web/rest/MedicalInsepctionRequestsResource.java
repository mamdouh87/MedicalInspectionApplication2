package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.MedicalInsepctionRequestsService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.MedicalInsepctionRequestsDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.MedicalInsepctionRequests}.
 */
@RestController
@RequestMapping("/api")
public class MedicalInsepctionRequestsResource {

    private final Logger log = LoggerFactory.getLogger(MedicalInsepctionRequestsResource.class);

    private static final String ENTITY_NAME = "medicalInsepctionRequests";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalInsepctionRequestsService medicalInsepctionRequestsService;

    public MedicalInsepctionRequestsResource(MedicalInsepctionRequestsService medicalInsepctionRequestsService) {
        this.medicalInsepctionRequestsService = medicalInsepctionRequestsService;
    }

    /**
     * {@code POST  /medical-insepction-requests} : Create a new medicalInsepctionRequests.
     *
     * @param medicalInsepctionRequestsDTO the medicalInsepctionRequestsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalInsepctionRequestsDTO, or with status {@code 400 (Bad Request)} if the medicalInsepctionRequests has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-insepction-requests")
    public ResponseEntity<MedicalInsepctionRequestsDTO> createMedicalInsepctionRequests(@RequestBody MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO) throws URISyntaxException {
        log.debug("REST request to save MedicalInsepctionRequests : {}", medicalInsepctionRequestsDTO);
        if (medicalInsepctionRequestsDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicalInsepctionRequests cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalInsepctionRequestsDTO result = medicalInsepctionRequestsService.save(medicalInsepctionRequestsDTO);
        return ResponseEntity.created(new URI("/api/medical-insepction-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-insepction-requests} : Updates an existing medicalInsepctionRequests.
     *
     * @param medicalInsepctionRequestsDTO the medicalInsepctionRequestsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalInsepctionRequestsDTO,
     * or with status {@code 400 (Bad Request)} if the medicalInsepctionRequestsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalInsepctionRequestsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-insepction-requests")
    public ResponseEntity<MedicalInsepctionRequestsDTO> updateMedicalInsepctionRequests(@RequestBody MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO) throws URISyntaxException {
        log.debug("REST request to update MedicalInsepctionRequests : {}", medicalInsepctionRequestsDTO);
        if (medicalInsepctionRequestsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalInsepctionRequestsDTO result = medicalInsepctionRequestsService.save(medicalInsepctionRequestsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicalInsepctionRequestsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-insepction-requests} : get all the medicalInsepctionRequests.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalInsepctionRequests in body.
     */
    @GetMapping("/medical-insepction-requests")
    public ResponseEntity<List<MedicalInsepctionRequestsDTO>> getAllMedicalInsepctionRequests(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of MedicalInsepctionRequests");
        Page<MedicalInsepctionRequestsDTO> page;
        if (eagerload) {
            page = medicalInsepctionRequestsService.findAllWithEagerRelationships(pageable);
        } else {
            page = medicalInsepctionRequestsService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-insepction-requests/:id} : get the "id" medicalInsepctionRequests.
     *
     * @param id the id of the medicalInsepctionRequestsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalInsepctionRequestsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-insepction-requests/{id}")
    public ResponseEntity<MedicalInsepctionRequestsDTO> getMedicalInsepctionRequests(@PathVariable Long id) {
        log.debug("REST request to get MedicalInsepctionRequests : {}", id);
        Optional<MedicalInsepctionRequestsDTO> medicalInsepctionRequestsDTO = medicalInsepctionRequestsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalInsepctionRequestsDTO);
    }

    /**
     * {@code DELETE  /medical-insepction-requests/:id} : delete the "id" medicalInsepctionRequests.
     *
     * @param id the id of the medicalInsepctionRequestsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-insepction-requests/{id}")
    public ResponseEntity<Void> deleteMedicalInsepctionRequests(@PathVariable Long id) {
        log.debug("REST request to delete MedicalInsepctionRequests : {}", id);
        medicalInsepctionRequestsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
