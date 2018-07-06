package com.raymond.financialdashboard.service;

import com.raymond.financialdashboard.service.dto.TransactionIngDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TransactionIng.
 */
public interface TransactionIngService {

    /**
     * Save a transactionIng.
     *
     * @param transactionIngDTO the entity to save
     * @return the persisted entity
     */
    TransactionIngDTO save(TransactionIngDTO transactionIngDTO);

    /**
     * Get all the transactionIngs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TransactionIngDTO> findAll(Pageable pageable);

    /**
     * Get all the TransactionIng with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<TransactionIngDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" transactionIng.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TransactionIngDTO> findOne(Long id);

    /**
     * Delete the "id" transactionIng.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
