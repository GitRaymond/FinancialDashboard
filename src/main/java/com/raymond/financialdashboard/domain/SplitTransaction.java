package com.raymond.financialdashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.raymond.financialdashboard.domain.enumeration.Sign;

/**
 * A SplitTransaction.
 */
@Entity
@Table(name = "split_transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SplitTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "split_name")
    private String splitName;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign")
    private Sign sign;

    @ManyToOne
    @JsonIgnoreProperties("splitTransactions")
    private TransactionIng transactionIng;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSplitName() {
        return splitName;
    }

    public SplitTransaction splitName(String splitName) {
        this.splitName = splitName;
        return this;
    }

    public void setSplitName(String splitName) {
        this.splitName = splitName;
    }

    public Double getAmount() {
        return amount;
    }

    public SplitTransaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Sign getSign() {
        return sign;
    }

    public SplitTransaction sign(Sign sign) {
        this.sign = sign;
        return this;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public TransactionIng getTransactionIng() {
        return transactionIng;
    }

    public SplitTransaction transactionIng(TransactionIng transactionIng) {
        this.transactionIng = transactionIng;
        return this;
    }

    public void setTransactionIng(TransactionIng transactionIng) {
        this.transactionIng = transactionIng;
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
        SplitTransaction splitTransaction = (SplitTransaction) o;
        if (splitTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), splitTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SplitTransaction{" +
            "id=" + getId() +
            ", splitName='" + getSplitName() + "'" +
            ", amount=" + getAmount() +
            ", sign='" + getSign() + "'" +
            "}";
    }
}
