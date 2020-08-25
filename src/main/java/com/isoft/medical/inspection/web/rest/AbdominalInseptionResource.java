package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.AbdominalInseptionService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.AbdominalInseptionDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.AbdominalInseption}.
 */
@RestController
@RequestMapping("/api")
public class AbdominalInseptionResource {

    private final Logger log = LoggerFactory.getLogger(AbdominalInseptionResource.class);

    private static final String ENTITY_NAME = "abdominalInseption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AbdominalInseptionService abdominalInseptionService;

    public AbdominalInseptionResource(AbdominalInseptionService abdominalInseptionService) {
        this.abdominalInseptionService = abdominalInseptionService;
    }

    /**
     * {@code POST  /abdominal-inseptions} : Create a new abdominalInseption.
     *
     * @param abdominalInseptionDTO the abdominalInseptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new abdominalInseptionDTO, or with status {@code 400 (Bad Request)} if the abdominalInseption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/abdominal-inseptions")
    public ResponseEntity<AbdominalInseptionDTO> createAbdominalInseption(@RequestBody AbdominalInseptionDTO abdominalInseptionDTO) throws URISyntaxException {
        log.debug("REST request to save AbdominalInseption : {}", abdominalInseptionDTO);
        if (abdominalInseptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new abdominalInseption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbdominalInseptionDTO result = abdominalInseptionService.save(abdominalInseptionDTO);
        return ResponseEntity.created(new URI("/api/abdominal-inseptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /abdominal-inseptions} : Updates an existing abdominalInseption.
     *
     * @param abdominalInseptionDTO the abdominalInseptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated abdominalInseptionDTO,
     * or with status {@code 400 (Bad Request)} if the abdominalInseptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the abdominalInseptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/abdominal-inseptions")
    public ResponseEntity<AbdominalInseptionDTO> updateAbdominalInseption(@RequestBody AbdominalInseptionDTO abdominalInseptionDTO) throws URISyntaxException {
        log.debug("REST request to update AbdominalInseption : {}", abdominalInseptionDTO);
        if (abdominalInseptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AbdominalInseptionDTO result = abdominalInseptionService.save(abdominalInseptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, abdominalInseptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /abdominal-inseptions} : get all the abdominalInseptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of abdominalInseptions in body.
     */
    @GetMapping("/abdominal-inseptions")
    public ResponseEntity<List<AbdominalInseptionDTO>> getAllAbdominalInseptions(Pageable pageable) {
        log.debug("REST request to get a page of AbdominalInseptions");
        Page<AbdominalInseptionDTO> page = abdominalInseptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /abdominal-inseptions/:id} : get the "id" abdominalInseption.
     *
     * @param id the id of the abdominalInseptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the abdominalInseptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/abdominal-inseptions/{id}")
    public ResponseEntity<AbdominalInseptionDTO> getAbdominalInseption(@PathVariable Long id) {
        log.debug("REST request to get AbdominalInseption : {}", id);
        Optional<AbdominalInseptionDTO> abdominalInseptionDTO = abdominalInseptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(abdominalInseptionDTO);
    }

    /**
     * {@code DELETE  /abdominal-inseptions/:id} : delete the "id" abdominalInseption.
     *
     * @param id the id of the abdominalInseptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/abdominal-inseptions/{id}")
    public ResponseEntity<Void> deleteAbdominalInseption(@PathVariable Long id) {
        log.debug("REST request to delete AbdominalInseption : {}", id);
        abdominalInseptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
