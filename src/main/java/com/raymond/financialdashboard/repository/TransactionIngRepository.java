package com.raymond.financialdashboard.repository;

import com.raymond.financialdashboard.domain.TransactionIng;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TransactionIng entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionIngRepository extends JpaRepository<TransactionIng, Long> {

    @Query(value = "select distinct transaction_ing from TransactionIng transaction_ing left join fetch transaction_ing.tags",
        countQuery = "select count(distinct transaction_ing) from TransactionIng transaction_ing")
    Page<TransactionIng> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct transaction_ing from TransactionIng transaction_ing left join fetch transaction_ing.tags")
    List<TransactionIng> findAllWithEagerRelationships();

    @Query("select transaction_ing from TransactionIng transaction_ing left join fetch transaction_ing.tags where transaction_ing.id =:id")
    Optional<TransactionIng> findOneWithEagerRelationships(@Param("id") Long id);

}
