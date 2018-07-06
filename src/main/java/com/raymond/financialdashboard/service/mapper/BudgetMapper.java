package com.raymond.financialdashboard.service.mapper;

import com.raymond.financialdashboard.domain.*;
import com.raymond.financialdashboard.service.dto.BudgetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Budget and its DTO BudgetDTO.
 */
@Mapper(componentModel = "spring", uses = {ReportingCategoryMapper.class})
public interface BudgetMapper extends EntityMapper<BudgetDTO, Budget> {

    @Mapping(source = "reportingCategory.id", target = "reportingCategoryId")
    BudgetDTO toDto(Budget budget);

    @Mapping(source = "reportingCategoryId", target = "reportingCategory")
    Budget toEntity(BudgetDTO budgetDTO);

    default Budget fromId(Long id) {
        if (id == null) {
            return null;
        }
        Budget budget = new Budget();
        budget.setId(id);
        return budget;
    }
}
