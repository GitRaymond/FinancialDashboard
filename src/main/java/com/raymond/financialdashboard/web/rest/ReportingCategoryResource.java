package com.raymond.financialdashboard.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raymond.financialdashboard.service.ReportingCategoryService;
import com.raymond.financialdashboard.web.rest.errors.BadRequestAlertException;
import com.raymond.financialdashboard.web.rest.util.HeaderUtil;
import com.raymond.financialdashboard.service.dto.ReportingCategoryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ReportingCategory.
 */
@RestController
@RequestMapping("/api")
public class ReportingCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ReportingCategoryResource.class);

    private static final String ENTITY_NAME = "reportingCategory";

    private final ReportingCategoryService reportingCategoryService;

    public ReportingCategoryResource(ReportingCategoryService reportingCategoryService) {
        this.reportingCategoryService = reportingCategoryService;
    }

    /**
     * POST  /reporting-categories : Create a new reportingCategory.
     *
     * @param reportingCategoryDTO the reportingCategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reportingCategoryDTO, or with status 400 (Bad Request) if the reportingCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reporting-categories")
    @Timed
    public ResponseEntity<ReportingCategoryDTO> createReportingCategory(@RequestBody ReportingCategoryDTO reportingCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save ReportingCategory : {}", reportingCategoryDTO);
        if (reportingCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new reportingCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportingCategoryDTO result = reportingCategoryService.save(reportingCategoryDTO);
        return ResponseEntity.created(new URI("/api/reporting-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reporting-categories : Updates an existing reportingCategory.
     *
     * @param reportingCategoryDTO the reportingCategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reportingCategoryDTO,
     * or with status 400 (Bad Request) if the reportingCategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the reportingCategoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reporting-categories")
    @Timed
    public ResponseEntity<ReportingCategoryDTO> updateReportingCategory(@RequestBody ReportingCategoryDTO reportingCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update ReportingCategory : {}", reportingCategoryDTO);
        if (reportingCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportingCategoryDTO result = reportingCategoryService.save(reportingCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reportingCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reporting-categories : get all the reportingCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reportingCategories in body
     */
    @GetMapping("/reporting-categories")
    @Timed
    public List<ReportingCategoryDTO> getAllReportingCategories() {
        log.debug("REST request to get all ReportingCategories");
        return reportingCategoryService.findAll();
    }

    /**
     * GET  /reporting-categories/:id : get the "id" reportingCategory.
     *
     * @param id the id of the reportingCategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reportingCategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reporting-categories/{id}")
    @Timed
    public ResponseEntity<ReportingCategoryDTO> getReportingCategory(@PathVariable Long id) {
        log.debug("REST request to get ReportingCategory : {}", id);
        Optional<ReportingCategoryDTO> reportingCategoryDTO = reportingCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportingCategoryDTO);
    }

    /**
     * DELETE  /reporting-categories/:id : delete the "id" reportingCategory.
     *
     * @param id the id of the reportingCategoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reporting-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteReportingCategory(@PathVariable Long id) {
        log.debug("REST request to delete ReportingCategory : {}", id);
        reportingCategoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
