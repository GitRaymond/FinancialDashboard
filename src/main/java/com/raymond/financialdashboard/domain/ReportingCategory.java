package com.raymond.financialdashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ReportingCategory.
 */
@Entity
@Table(name = "reporting_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReportingCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "reportingCategory")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TransactionIng> transactionIngs = new HashSet<>();

    @OneToMany(mappedBy = "reportingCategory")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Budget> budgets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ReportingCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TransactionIng> getTransactionIngs() {
        return transactionIngs;
    }

    public ReportingCategory transactionIngs(Set<TransactionIng> transactionIngs) {
        this.transactionIngs = transactionIngs;
        return this;
    }

    public ReportingCategory addTransactionIng(TransactionIng transactionIng) {
        this.transactionIngs.add(transactionIng);
        transactionIng.setReportingCategory(this);
        return this;
    }

    public ReportingCategory removeTransactionIng(TransactionIng transactionIng) {
        this.transactionIngs.remove(transactionIng);
        transactionIng.setReportingCategory(null);
        return this;
    }

    public void setTransactionIngs(Set<TransactionIng> transactionIngs) {
        this.transactionIngs = transactionIngs;
    }

    public Set<Budget> getBudgets() {
        return budgets;
    }

    public ReportingCategory budgets(Set<Budget> budgets) {
        this.budgets = budgets;
        return this;
    }

    public ReportingCategory addBudget(Budget budget) {
        this.budgets.add(budget);
        budget.setReportingCategory(this);
        return this;
    }

    public ReportingCategory removeBudget(Budget budget) {
        this.budgets.remove(budget);
        budget.setReportingCategory(null);
        return this;
    }

    public void setBudgets(Set<Budget> budgets) {
        this.budgets = budgets;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportingCategory reportingCategory = (ReportingCategory) o;
        if (reportingCategory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reportingCategory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReportingCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
