package com.testone.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import com.testone.util.BankAccountNumberEncryptor;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bank_information")
public class BankInformation {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    @Convert(converter = BankAccountNumberEncryptor.class) // Use the encryption converter
    private String bankAccountNumber;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountType;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    // toString method
    @Override
    public String toString() {
        return "BankInformation{" +
                "id=" + id +
                ", userId=" + userId +
                ", bankAccountNumber='***REDACTED***'" + // Mask sensitive data in logs
                ", bankName='" + bankName + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankInformation that = (BankInformation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(bankAccountNumber, that.bankAccountNumber) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(accountType, that.accountType);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bankAccountNumber, bankName, accountType);
    }
}
