package com.raymond.financialdashboard.service.impl;

import com.raymond.financialdashboard.service.ReportingCategoryService;
import com.raymond.financialdashboard.domain.ReportingCategory;
import com.raymond.financialdashboard.repository.ReportingCategoryRepository;
import com.raymond.financialdashboard.service.dto.ReportingCategoryDTO;
import com.raymond.financialdashboard.service.mapper.ReportingCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ReportingCategory.
 */
@Service
@Transactional
public class ReportingCategoryServiceImpl implements ReportingCategoryService {

    private final Logger log = LoggerFactory.getLogger(ReportingCategoryServiceImpl.class);

    private final ReportingCategoryRepository reportingCategoryRepository;

    private final ReportingCategoryMapper reportingCategoryMapper;

    public ReportingCategoryServiceImpl(ReportingCategoryRepository reportingCategoryRepository, ReportingCategoryMapper reportingCategoryMapper) {
        this.reportingCategoryRepository = reportingCategoryRepository;
        this.reportingCategoryMapper = reportingCategoryMapper;
    }

    /**
     * Save a reportingCategory.
     *
     * @param reportingCategoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReportingCategoryDTO save(ReportingCategoryDTO reportingCategoryDTO) {
        log.debug("Request to save ReportingCategory : {}", reportingCategoryDTO);
        ReportingCategory reportingCategory = reportingCategoryMapper.toEntity(reportingCategoryDTO);
        reportingCategory = reportingCategoryRepository.save(reportingCategory);
        return reportingCategoryMapper.toDto(reportingCategory);
    }

    /**
     * Get all the reportingCategories.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReportingCategoryDTO> findAll() {
        log.debug("Request to get all ReportingCategories");
        return reportingCategoryRepository.findAll().stream()
            .map(reportingCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one reportingCategory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReportingCategoryDTO> findOne(Long id) {
        log.debug("Request to get ReportingCategory : {}", id);
        return reportingCategoryRepository.findById(id)
            .map(reportingCategoryMapper::toDto);
    }

    /**
     * Delete the reportingCategory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportingCategory : {}", id);
        reportingCategoryRepository.deleteById(id);
    }
}
