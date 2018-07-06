package com.raymond.financialdashboard.service.impl;

import com.raymond.financialdashboard.service.TransactionIngService;
import com.raymond.financialdashboard.domain.TransactionIng;
import com.raymond.financialdashboard.repository.TransactionIngRepository;
import com.raymond.financialdashboard.service.dto.TransactionIngDTO;
import com.raymond.financialdashboard.service.mapper.TransactionIngMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing TransactionIng.
 */
@Service
@Transactional
public class TransactionIngServiceImpl implements TransactionIngService {

    private final Logger log = LoggerFactory.getLogger(TransactionIngServiceImpl.class);

    private final TransactionIngRepository transactionIngRepository;

    private final TransactionIngMapper transactionIngMapper;

    public TransactionIngServiceImpl(TransactionIngRepository transactionIngRepository, TransactionIngMapper transactionIngMapper) {
        this.transactionIngRepository = transactionIngRepository;
        this.transactionIngMapper = transactionIngMapper;
    }

    /**
     * Save a transactionIng.
     *
     * @param transactionIngDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TransactionIngDTO save(TransactionIngDTO transactionIngDTO) {
        log.debug("Request to save TransactionIng : {}", transactionIngDTO);
        TransactionIng transactionIng = transactionIngMapper.toEntity(transactionIngDTO);
        transactionIng = transactionIngRepository.save(transactionIng);
        return transactionIngMapper.toDto(transactionIng);
    }

    /**
     * Get all the transactionIngs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransactionIngDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TransactionIngs");
        return transactionIngRepository.findAll(pageable)
            .map(transactionIngMapper::toDto);
    }

    /**
     * Get all the TransactionIng with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<TransactionIngDTO> findAllWithEagerRelationships(Pageable pageable) {
        return transactionIngRepository.findAllWithEagerRelationships(pageable).map(transactionIngMapper::toDto);
    }
    

    /**
     * Get one transactionIng by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionIngDTO> findOne(Long id) {
        log.debug("Request to get TransactionIng : {}", id);
        return transactionIngRepository.findOneWithEagerRelationships(id)
            .map(transactionIngMapper::toDto);
    }

    /**
     * Delete the transactionIng by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransactionIng : {}", id);
        transactionIngRepository.deleteById(id);
    }
}
