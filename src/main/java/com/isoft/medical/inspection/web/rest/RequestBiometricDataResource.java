package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.RequestBiometricDataService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.RequestBiometricDataDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.RequestBiometricData}.
 */
@RestController
@RequestMapping("/api")
public class RequestBiometricDataResource {

    private final Logger log = LoggerFactory.getLogger(RequestBiometricDataResource.class);

    private static final String ENTITY_NAME = "requestBiometricData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestBiometricDataService requestBiometricDataService;

    public RequestBiometricDataResource(RequestBiometricDataService requestBiometricDataService) {
        this.requestBiometricDataService = requestBiometricDataService;
    }

    /**
     * {@code POST  /request-biometric-data} : Create a new requestBiometricData.
     *
     * @param requestBiometricDataDTO the requestBiometricDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestBiometricDataDTO, or with status {@code 400 (Bad Request)} if the requestBiometricData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-biometric-data")
    public ResponseEntity<RequestBiometricDataDTO> createRequestBiometricData(@RequestBody RequestBiometricDataDTO requestBiometricDataDTO) throws URISyntaxException {
        log.debug("REST request to save RequestBiometricData : {}", requestBiometricDataDTO);
        if (requestBiometricDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestBiometricData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestBiometricDataDTO result = requestBiometricDataService.save(requestBiometricDataDTO);
        return ResponseEntity.created(new URI("/api/request-biometric-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-biometric-data} : Updates an existing requestBiometricData.
     *
     * @param requestBiometricDataDTO the requestBiometricDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestBiometricDataDTO,
     * or with status {@code 400 (Bad Request)} if the requestBiometricDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestBiometricDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-biometric-data")
    public ResponseEntity<RequestBiometricDataDTO> updateRequestBiometricData(@RequestBody RequestBiometricDataDTO requestBiometricDataDTO) throws URISyntaxException {
        log.debug("REST request to update RequestBiometricData : {}", requestBiometricDataDTO);
        if (requestBiometricDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequestBiometricDataDTO result = requestBiometricDataService.save(requestBiometricDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestBiometricDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /request-biometric-data} : get all the requestBiometricData.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestBiometricData in body.
     */
    @GetMapping("/request-biometric-data")
    public ResponseEntity<List<RequestBiometricDataDTO>> getAllRequestBiometricData(Pageable pageable) {
        log.debug("REST request to get a page of RequestBiometricData");
        Page<RequestBiometricDataDTO> page = requestBiometricDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-biometric-data/:id} : get the "id" requestBiometricData.
     *
     * @param id the id of the requestBiometricDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestBiometricDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-biometric-data/{id}")
    public ResponseEntity<RequestBiometricDataDTO> getRequestBiometricData(@PathVariable Long id) {
        log.debug("REST request to get RequestBiometricData : {}", id);
        Optional<RequestBiometricDataDTO> requestBiometricDataDTO = requestBiometricDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestBiometricDataDTO);
    }

    /**
     * {@code DELETE  /request-biometric-data/:id} : delete the "id" requestBiometricData.
     *
     * @param id the id of the requestBiometricDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-biometric-data/{id}")
    public ResponseEntity<Void> deleteRequestBiometricData(@PathVariable Long id) {
        log.debug("REST request to delete RequestBiometricData : {}", id);
        requestBiometricDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
