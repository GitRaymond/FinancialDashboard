package com.raymond.financialdashboard.repository;

import com.raymond.financialdashboard.domain.SplitTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SplitTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SplitTransactionRepository extends JpaRepository<SplitTransaction, Long> {

}
