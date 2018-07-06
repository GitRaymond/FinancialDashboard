package com.raymond.financialdashboard.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.raymond.financialdashboard.domain.enumeration.Sign;

/**
 * A DTO for the TransactionIng entity.
 */
public class TransactionIngDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer date;

    @NotNull
    private String name;

    @NotNull
    private String myBankAccount;

    private String contraBankAccount;

    @NotNull
    private String code;

    @NotNull
    private Sign sign;

    @NotNull
    private Double amount;

    @NotNull
    private String mutation;

    @Lob
    private String description;

    private Long vendorId;

    private Long bankAccountId;

    private Long reportingCategoryId;

    private Set<TagDTO> tags = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyBankAccount() {
        return myBankAccount;
    }

    public void setMyBankAccount(String myBankAccount) {
        this.myBankAccount = myBankAccount;
    }

    public String getContraBankAccount() {
        return contraBankAccount;
    }

    public void setContraBankAccount(String contraBankAccount) {
        this.contraBankAccount = contraBankAccount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getReportingCategoryId() {
        return reportingCategoryId;
    }

    public void setReportingCategoryId(Long reportingCategoryId) {
        this.reportingCategoryId = reportingCategoryId;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionIngDTO transactionIngDTO = (TransactionIngDTO) o;
        if (transactionIngDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionIngDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionIngDTO{" +
            "id=" + getId() +
            ", date=" + getDate() +
            ", name='" + getName() + "'" +
            ", myBankAccount='" + getMyBankAccount() + "'" +
            ", contraBankAccount='" + getContraBankAccount() + "'" +
            ", code='" + getCode() + "'" +
            ", sign='" + getSign() + "'" +
            ", amount=" + getAmount() +
            ", mutation='" + getMutation() + "'" +
            ", description='" + getDescription() + "'" +
            ", vendor=" + getVendorId() +
            ", bankAccount=" + getBankAccountId() +
            ", reportingCategory=" + getReportingCategoryId() +
            "}";
    }
}
