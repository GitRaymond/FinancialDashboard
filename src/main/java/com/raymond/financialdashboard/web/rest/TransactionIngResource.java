package com.raymond.financialdashboard.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raymond.financialdashboard.service.TransactionIngService;
import com.raymond.financialdashboard.web.rest.errors.BadRequestAlertException;
import com.raymond.financialdashboard.web.rest.util.HeaderUtil;
import com.raymond.financialdashboard.web.rest.util.PaginationUtil;
import com.raymond.financialdashboard.service.dto.TransactionIngDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TransactionIng.
 */
@RestController
@RequestMapping("/api")
public class TransactionIngResource {

    private final Logger log = LoggerFactory.getLogger(TransactionIngResource.class);

    private static final String ENTITY_NAME = "transactionIng";

    private final TransactionIngService transactionIngService;

    public TransactionIngResource(TransactionIngService transactionIngService) {
        this.transactionIngService = transactionIngService;
    }

    /**
     * POST  /transaction-ings : Create a new transactionIng.
     *
     * @param transactionIngDTO the transactionIngDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transactionIngDTO, or with status 400 (Bad Request) if the transactionIng has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transaction-ings")
    @Timed
    public ResponseEntity<TransactionIngDTO> createTransactionIng(@Valid @RequestBody TransactionIngDTO transactionIngDTO) throws URISyntaxException {
        log.debug("REST request to save TransactionIng : {}", transactionIngDTO);
        if (transactionIngDTO.getId() != null) {
            throw new BadRequestAlertException("A new transactionIng cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionIngDTO result = transactionIngService.save(transactionIngDTO);
        return ResponseEntity.created(new URI("/api/transaction-ings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transaction-ings : Updates an existing transactionIng.
     *
     * @param transactionIngDTO the transactionIngDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transactionIngDTO,
     * or with status 400 (Bad Request) if the transactionIngDTO is not valid,
     * or with status 500 (Internal Server Error) if the transactionIngDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transaction-ings")
    @Timed
    public ResponseEntity<TransactionIngDTO> updateTransactionIng(@Valid @RequestBody TransactionIngDTO transactionIngDTO) throws URISyntaxException {
        log.debug("REST request to update TransactionIng : {}", transactionIngDTO);
        if (transactionIngDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransactionIngDTO result = transactionIngService.save(transactionIngDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transactionIngDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transaction-ings : get all the transactionIngs.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of transactionIngs in body
     */
    @GetMapping("/transaction-ings")
    @Timed
    public ResponseEntity<List<TransactionIngDTO>> getAllTransactionIngs(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of TransactionIngs");
        Page<TransactionIngDTO> page;
        if (eagerload) {
            page = transactionIngService.findAllWithEagerRelationships(pageable);
        } else {
            page = transactionIngService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/transaction-ings?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /transaction-ings/:id : get the "id" transactionIng.
     *
     * @param id the id of the transactionIngDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transactionIngDTO, or with status 404 (Not Found)
     */
    @GetMapping("/transaction-ings/{id}")
    @Timed
    public ResponseEntity<TransactionIngDTO> getTransactionIng(@PathVariable Long id) {
        log.debug("REST request to get TransactionIng : {}", id);
        Optional<TransactionIngDTO> transactionIngDTO = transactionIngService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionIngDTO);
    }

    /**
     * DELETE  /transaction-ings/:id : delete the "id" transactionIng.
     *
     * @param id the id of the transactionIngDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transaction-ings/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransactionIng(@PathVariable Long id) {
        log.debug("REST request to delete TransactionIng : {}", id);
        transactionIngService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
