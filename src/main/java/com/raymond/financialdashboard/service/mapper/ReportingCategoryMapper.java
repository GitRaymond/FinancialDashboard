package com.raymond.financialdashboard.service.mapper;

import com.raymond.financialdashboard.domain.*;
import com.raymond.financialdashboard.service.dto.ReportingCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ReportingCategory and its DTO ReportingCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReportingCategoryMapper extends EntityMapper<ReportingCategoryDTO, ReportingCategory> {


    @Mapping(target = "transactionIngs", ignore = true)
    @Mapping(target = "budgets", ignore = true)
    ReportingCategory toEntity(ReportingCategoryDTO reportingCategoryDTO);

    default ReportingCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReportingCategory reportingCategory = new ReportingCategory();
        reportingCategory.setId(id);
        return reportingCategory;
    }
}
