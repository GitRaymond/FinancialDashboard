package com.raymond.financialdashboard.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raymond.financialdashboard.service.SplitTransactionService;
import com.raymond.financialdashboard.web.rest.errors.BadRequestAlertException;
import com.raymond.financialdashboard.web.rest.util.HeaderUtil;
import com.raymond.financialdashboard.service.dto.SplitTransactionDTO;
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
 * REST controller for managing SplitTransaction.
 */
@RestController
@RequestMapping("/api")
public class SplitTransactionResource {

    private final Logger log = LoggerFactory.getLogger(SplitTransactionResource.class);

    private static final String ENTITY_NAME = "splitTransaction";

    private final SplitTransactionService splitTransactionService;

    public SplitTransactionResource(SplitTransactionService splitTransactionService) {
        this.splitTransactionService = splitTransactionService;
    }

    /**
     * POST  /split-transactions : Create a new splitTransaction.
     *
     * @param splitTransactionDTO the splitTransactionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new splitTransactionDTO, or with status 400 (Bad Request) if the splitTransaction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/split-transactions")
    @Timed
    public ResponseEntity<SplitTransactionDTO> createSplitTransaction(@RequestBody SplitTransactionDTO splitTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save SplitTransaction : {}", splitTransactionDTO);
        if (splitTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new splitTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SplitTransactionDTO result = splitTransactionService.save(splitTransactionDTO);
        return ResponseEntity.created(new URI("/api/split-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /split-transactions : Updates an existing splitTransaction.
     *
     * @param splitTransactionDTO the splitTransactionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated splitTransactionDTO,
     * or with status 400 (Bad Request) if the splitTransactionDTO is not valid,
     * or with status 500 (Internal Server Error) if the splitTransactionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/split-transactions")
    @Timed
    public ResponseEntity<SplitTransactionDTO> updateSplitTransaction(@RequestBody SplitTransactionDTO splitTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update SplitTransaction : {}", splitTransactionDTO);
        if (splitTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SplitTransactionDTO result = splitTransactionService.save(splitTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, splitTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /split-transactions : get all the splitTransactions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of splitTransactions in body
     */
    @GetMapping("/split-transactions")
    @Timed
    public List<SplitTransactionDTO> getAllSplitTransactions() {
        log.debug("REST request to get all SplitTransactions");
        return splitTransactionService.findAll();
    }

    /**
     * GET  /split-transactions/:id : get the "id" splitTransaction.
     *
     * @param id the id of the splitTransactionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the splitTransactionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/split-transactions/{id}")
    @Timed
    public ResponseEntity<SplitTransactionDTO> getSplitTransaction(@PathVariable Long id) {
        log.debug("REST request to get SplitTransaction : {}", id);
        Optional<SplitTransactionDTO> splitTransactionDTO = splitTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(splitTransactionDTO);
    }

    /**
     * DELETE  /split-transactions/:id : delete the "id" splitTransaction.
     *
     * @param id the id of the splitTransactionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/split-transactions/{id}")
    @Timed
    public ResponseEntity<Void> deleteSplitTransaction(@PathVariable Long id) {
        log.debug("REST request to delete SplitTransaction : {}", id);
        splitTransactionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
