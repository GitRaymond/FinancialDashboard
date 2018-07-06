package com.raymond.financialdashboard.service;

import com.raymond.financialdashboard.service.dto.ReportingCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ReportingCategory.
 */
public interface ReportingCategoryService {

    /**
     * Save a reportingCategory.
     *
     * @param reportingCategoryDTO the entity to save
     * @return the persisted entity
     */
    ReportingCategoryDTO save(ReportingCategoryDTO reportingCategoryDTO);

    /**
     * Get all the reportingCategories.
     *
     * @return the list of entities
     */
    List<ReportingCategoryDTO> findAll();


    /**
     * Get the "id" reportingCategory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReportingCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" reportingCategory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
