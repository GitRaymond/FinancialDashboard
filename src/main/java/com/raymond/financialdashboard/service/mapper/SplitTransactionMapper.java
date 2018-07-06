package com.raymond.financialdashboard.service.mapper;

import com.raymond.financialdashboard.domain.*;
import com.raymond.financialdashboard.service.dto.SplitTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SplitTransaction and its DTO SplitTransactionDTO.
 */
@Mapper(componentModel = "spring", uses = {TransactionIngMapper.class})
public interface SplitTransactionMapper extends EntityMapper<SplitTransactionDTO, SplitTransaction> {

    @Mapping(source = "transactionIng.id", target = "transactionIngId")
    SplitTransactionDTO toDto(SplitTransaction splitTransaction);

    @Mapping(source = "transactionIngId", target = "transactionIng")
    SplitTransaction toEntity(SplitTransactionDTO splitTransactionDTO);

    default SplitTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        SplitTransaction splitTransaction = new SplitTransaction();
        splitTransaction.setId(id);
        return splitTransaction;
    }
}
