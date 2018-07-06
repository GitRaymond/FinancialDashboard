package com.raymond.financialdashboard.service.mapper;

import com.raymond.financialdashboard.domain.*;
import com.raymond.financialdashboard.service.dto.TransactionIngDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TransactionIng and its DTO TransactionIngDTO.
 */
@Mapper(componentModel = "spring", uses = {VendorMapper.class, BankAccountMapper.class, ReportingCategoryMapper.class, TagMapper.class})
public interface TransactionIngMapper extends EntityMapper<TransactionIngDTO, TransactionIng> {

    @Mapping(source = "vendor.id", target = "vendorId")
    @Mapping(source = "bankAccount.id", target = "bankAccountId")
    @Mapping(source = "reportingCategory.id", target = "reportingCategoryId")
    TransactionIngDTO toDto(TransactionIng transactionIng);

    @Mapping(source = "vendorId", target = "vendor")
    @Mapping(source = "bankAccountId", target = "bankAccount")
    @Mapping(source = "reportingCategoryId", target = "reportingCategory")
    @Mapping(target = "splitTransactions", ignore = true)
    TransactionIng toEntity(TransactionIngDTO transactionIngDTO);

    default TransactionIng fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransactionIng transactionIng = new TransactionIng();
        transactionIng.setId(id);
        return transactionIng;
    }
}
