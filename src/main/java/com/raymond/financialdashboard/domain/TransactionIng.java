package com.raymond.financialdashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.raymond.financialdashboard.domain.enumeration.Sign;

/**
 * A TransactionIng.
 */
@Entity
@Table(name = "transaction_ing")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TransactionIng implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private Integer date;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "my_bank_account", nullable = false)
    private String myBankAccount;

    @Column(name = "contra_bank_account")
    private String contraBankAccount;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sign", nullable = false)
    private Sign sign;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull
    @Column(name = "mutation", nullable = false)
    private String mutation;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("transactionIngs")
    private Vendor vendor;

    @ManyToOne
    @JsonIgnoreProperties("transactionIngs")
    private BankAccount bankAccount;

    @ManyToOne
    @JsonIgnoreProperties("transactionIngs")
    private ReportingCategory reportingCategory;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "transaction_ing_tag",
               joinColumns = @JoinColumn(name = "transaction_ings_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "transactionIng")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SplitTransaction> splitTransactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public TransactionIng date(Integer date) {
        this.date = date;
        return this;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public TransactionIng name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyBankAccount() {
        return myBankAccount;
    }

    public TransactionIng myBankAccount(String myBankAccount) {
        this.myBankAccount = myBankAccount;
        return this;
    }

    public void setMyBankAccount(String myBankAccount) {
        this.myBankAccount = myBankAccount;
    }

    public String getContraBankAccount() {
        return contraBankAccount;
    }

    public TransactionIng contraBankAccount(String contraBankAccount) {
        this.contraBankAccount = contraBankAccount;
        return this;
    }

    public void setContraBankAccount(String contraBankAccount) {
        this.contraBankAccount = contraBankAccount;
    }

    public String getCode() {
        return code;
    }

    public TransactionIng code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Sign getSign() {
        return sign;
    }

    public TransactionIng sign(Sign sign) {
        this.sign = sign;
        return this;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionIng amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMutation() {
        return mutation;
    }

    public TransactionIng mutation(String mutation) {
        this.mutation = mutation;
        return this;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getDescription() {
        return description;
    }

    public TransactionIng description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public TransactionIng vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public TransactionIng bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public ReportingCategory getReportingCategory() {
        return reportingCategory;
    }

    public TransactionIng reportingCategory(ReportingCategory reportingCategory) {
        this.reportingCategory = reportingCategory;
        return this;
    }

    public void setReportingCategory(ReportingCategory reportingCategory) {
        this.reportingCategory = reportingCategory;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public TransactionIng tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public TransactionIng addTag(Tag tag) {
        this.tags.add(tag);
        tag.getTransactionIngs().add(this);
        return this;
    }

    public TransactionIng removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getTransactionIngs().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<SplitTransaction> getSplitTransactions() {
        return splitTransactions;
    }

    public TransactionIng splitTransactions(Set<SplitTransaction> splitTransactions) {
        this.splitTransactions = splitTransactions;
        return this;
    }

    public TransactionIng addSplitTransaction(SplitTransaction splitTransaction) {
        this.splitTransactions.add(splitTransaction);
        splitTransaction.setTransactionIng(this);
        return this;
    }

    public TransactionIng removeSplitTransaction(SplitTransaction splitTransaction) {
        this.splitTransactions.remove(splitTransaction);
        splitTransaction.setTransactionIng(null);
        return this;
    }

    public void setSplitTransactions(Set<SplitTransaction> splitTransactions) {
        this.splitTransactions = splitTransactions;
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
        TransactionIng transactionIng = (TransactionIng) o;
        if (transactionIng.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionIng.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionIng{" +
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
            "}";
    }
}
