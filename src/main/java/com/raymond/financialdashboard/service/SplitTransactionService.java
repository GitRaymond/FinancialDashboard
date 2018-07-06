package com.raymond.financialdashboard.service;

import com.raymond.financialdashboard.service.dto.SplitTransactionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SplitTransaction.
 */
public interface SplitTransactionService {

    /**
     * Save a splitTransaction.
     *
     * @param splitTransactionDTO the entity to save
     * @return the persisted entity
     */
    SplitTransactionDTO save(SplitTransactionDTO splitTransactionDTO);

    /**
     * Get all the splitTransactions.
     *
     * @return the list of entities
     */
    List<SplitTransactionDTO> findAll();


    /**
     * Get the "id" splitTransaction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SplitTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" splitTransaction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
