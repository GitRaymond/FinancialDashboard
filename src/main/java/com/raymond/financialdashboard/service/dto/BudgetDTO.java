package com.raymond.financialdashboard.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Budget entity.
 */
public class BudgetDTO implements Serializable {

    private Long id;

    private Integer year;

    private Integer month;

    private Double amount;

    private Long reportingCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getReportingCategoryId() {
        return reportingCategoryId;
    }

    public void setReportingCategoryId(Long reportingCategoryId) {
        this.reportingCategoryId = reportingCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BudgetDTO budgetDTO = (BudgetDTO) o;
        if (budgetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), budgetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BudgetDTO{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", amount=" + getAmount() +
            ", reportingCategory=" + getReportingCategoryId() +
            "}";
    }
}
