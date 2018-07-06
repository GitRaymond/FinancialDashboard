package com.raymond.financialdashboard.service.impl;

import com.raymond.financialdashboard.service.SplitTransactionService;
import com.raymond.financialdashboard.domain.SplitTransaction;
import com.raymond.financialdashboard.repository.SplitTransactionRepository;
import com.raymond.financialdashboard.service.dto.SplitTransactionDTO;
import com.raymond.financialdashboard.service.mapper.SplitTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing SplitTransaction.
 */
@Service
@Transactional
public class SplitTransactionServiceImpl implements SplitTransactionService {

    private final Logger log = LoggerFactory.getLogger(SplitTransactionServiceImpl.class);

    private final SplitTransactionRepository splitTransactionRepository;

    private final SplitTransactionMapper splitTransactionMapper;

    public SplitTransactionServiceImpl(SplitTransactionRepository splitTransactionRepository, SplitTransactionMapper splitTransactionMapper) {
        this.splitTransactionRepository = splitTransactionRepository;
        this.splitTransactionMapper = splitTransactionMapper;
    }

    /**
     * Save a splitTransaction.
     *
     * @param splitTransactionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SplitTransactionDTO save(SplitTransactionDTO splitTransactionDTO) {
        log.debug("Request to save SplitTransaction : {}", splitTransactionDTO);
        SplitTransaction splitTransaction = splitTransactionMapper.toEntity(splitTransactionDTO);
        splitTransaction = splitTransactionRepository.save(splitTransaction);
        return splitTransactionMapper.toDto(splitTransaction);
    }

    /**
     * Get all the splitTransactions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SplitTransactionDTO> findAll() {
        log.debug("Request to get all SplitTransactions");
        return splitTransactionRepository.findAll().stream()
            .map(splitTransactionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one splitTransaction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SplitTransactionDTO> findOne(Long id) {
        log.debug("Request to get SplitTransaction : {}", id);
        return splitTransactionRepository.findById(id)
            .map(splitTransactionMapper::toDto);
    }

    /**
     * Delete the splitTransaction by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SplitTransaction : {}", id);
        splitTransactionRepository.deleteById(id);
    }
}
