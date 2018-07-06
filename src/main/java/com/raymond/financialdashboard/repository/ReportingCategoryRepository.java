package com.raymond.financialdashboard.repository;

import com.raymond.financialdashboard.domain.ReportingCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReportingCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportingCategoryRepository extends JpaRepository<ReportingCategory, Long> {

}
