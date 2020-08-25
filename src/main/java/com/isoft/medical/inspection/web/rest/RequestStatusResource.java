package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.RequestStatusService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.RequestStatusDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.RequestStatus}.
 */
@RestController
@RequestMapping("/api")
public class RequestStatusResource {

    private final Logger log = LoggerFactory.getLogger(RequestStatusResource.class);

    private static final String ENTITY_NAME = "requestStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestStatusService requestStatusService;

    public RequestStatusResource(RequestStatusService requestStatusService) {
        this.requestStatusService = requestStatusService;
    }

    /**
     * {@code POST  /request-statuses} : Create a new requestStatus.
     *
     * @param requestStatusDTO the requestStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestStatusDTO, or with status {@code 400 (Bad Request)} if the requestStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-statuses")
    public ResponseEntity<RequestStatusDTO> createRequestStatus(@RequestBody RequestStatusDTO requestStatusDTO) throws URISyntaxException {
        log.debug("REST request to save RequestStatus : {}", requestStatusDTO);
        if (requestStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestStatusDTO result = requestStatusService.save(requestStatusDTO);
        return ResponseEntity.created(new URI("/api/request-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-statuses} : Updates an existing requestStatus.
     *
     * @param requestStatusDTO the requestStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestStatusDTO,
     * or with status {@code 400 (Bad Request)} if the requestStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-statuses")
    public ResponseEntity<RequestStatusDTO> updateRequestStatus(@RequestBody RequestStatusDTO requestStatusDTO) throws URISyntaxException {
        log.debug("REST request to update RequestStatus : {}", requestStatusDTO);
        if (requestStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequestStatusDTO result = requestStatusService.save(requestStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /request-statuses} : get all the requestStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestStatuses in body.
     */
    @GetMapping("/request-statuses")
    public ResponseEntity<List<RequestStatusDTO>> getAllRequestStatuses(Pageable pageable) {
        log.debug("REST request to get a page of RequestStatuses");
        Page<RequestStatusDTO> page = requestStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-statuses/:id} : get the "id" requestStatus.
     *
     * @param id the id of the requestStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-statuses/{id}")
    public ResponseEntity<RequestStatusDTO> getRequestStatus(@PathVariable Long id) {
        log.debug("REST request to get RequestStatus : {}", id);
        Optional<RequestStatusDTO> requestStatusDTO = requestStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestStatusDTO);
    }

    /**
     * {@code DELETE  /request-statuses/:id} : delete the "id" requestStatus.
     *
     * @param id the id of the requestStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-statuses/{id}")
    public ResponseEntity<Void> deleteRequestStatus(@PathVariable Long id) {
        log.debug("REST request to delete RequestStatus : {}", id);
        requestStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
