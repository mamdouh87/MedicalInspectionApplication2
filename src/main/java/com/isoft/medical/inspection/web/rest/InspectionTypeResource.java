package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.InspectionTypeService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.InspectionTypeDTO;

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
 * REST controller for managing {@link com.isoft.medical.inspection.domain.InspectionType}.
 */
@RestController
@RequestMapping("/api")
public class InspectionTypeResource {

    private final Logger log = LoggerFactory.getLogger(InspectionTypeResource.class);

    private static final String ENTITY_NAME = "inspectionType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionTypeService inspectionTypeService;

    public InspectionTypeResource(InspectionTypeService inspectionTypeService) {
        this.inspectionTypeService = inspectionTypeService;
    }

    /**
     * {@code POST  /inspection-types} : Create a new inspectionType.
     *
     * @param inspectionTypeDTO the inspectionTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionTypeDTO, or with status {@code 400 (Bad Request)} if the inspectionType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-types")
    public ResponseEntity<InspectionTypeDTO> createInspectionType(@RequestBody InspectionTypeDTO inspectionTypeDTO) throws URISyntaxException {
        log.debug("REST request to save InspectionType : {}", inspectionTypeDTO);
        if (inspectionTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionTypeDTO result = inspectionTypeService.save(inspectionTypeDTO);
        return ResponseEntity.created(new URI("/api/inspection-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-types} : Updates an existing inspectionType.
     *
     * @param inspectionTypeDTO the inspectionTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionTypeDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-types")
    public ResponseEntity<InspectionTypeDTO> updateInspectionType(@RequestBody InspectionTypeDTO inspectionTypeDTO) throws URISyntaxException {
        log.debug("REST request to update InspectionType : {}", inspectionTypeDTO);
        if (inspectionTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionTypeDTO result = inspectionTypeService.save(inspectionTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inspectionTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-types} : get all the inspectionTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionTypes in body.
     */
    @GetMapping("/inspection-types")
    public ResponseEntity<List<InspectionTypeDTO>> getAllInspectionTypes(Pageable pageable) {
        log.debug("REST request to get a page of InspectionTypes");
        Page<InspectionTypeDTO> page = inspectionTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-types/:id} : get the "id" inspectionType.
     *
     * @param id the id of the inspectionTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-types/{id}")
    public ResponseEntity<InspectionTypeDTO> getInspectionType(@PathVariable Long id) {
        log.debug("REST request to get InspectionType : {}", id);
        Optional<InspectionTypeDTO> inspectionTypeDTO = inspectionTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionTypeDTO);
    }

    /**
     * {@code DELETE  /inspection-types/:id} : delete the "id" inspectionType.
     *
     * @param id the id of the inspectionTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-types/{id}")
    public ResponseEntity<Void> deleteInspectionType(@PathVariable Long id) {
        log.debug("REST request to delete InspectionType : {}", id);
        inspectionTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
