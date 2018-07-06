package com.raymond.financialdashboard.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.raymond.financialdashboard.domain.enumeration.Sign;

/**
 * A DTO for the SplitTransaction entity.
 */
public class SplitTransactionDTO implements Serializable {

    private Long id;

    private String splitName;

    private Double amount;

    private Sign sign;

    private Long transactionIngId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSplitName() {
        return splitName;
    }

    public void setSplitName(String splitName) {
        this.splitName = splitName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Long getTransactionIngId() {
        return transactionIngId;
    }

    public void setTransactionIngId(Long transactionIngId) {
        this.transactionIngId = transactionIngId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SplitTransactionDTO splitTransactionDTO = (SplitTransactionDTO) o;
        if (splitTransactionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), splitTransactionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SplitTransactionDTO{" +
            "id=" + getId() +
            ", splitName='" + getSplitName() + "'" +
            ", amount=" + getAmount() +
            ", sign='" + getSign() + "'" +
            ", transactionIng=" + getTransactionIngId() +
            "}";
    }
}
