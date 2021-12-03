package com.ddlele.moneytrack.Wrappers;

import java.util.Date;

public class Transfer {
    public int id;
    public String description;
    public Account sourceAccount;
    public int sourceId;
    public Account destinationAccount;
    public int destinationId;
    public int amount;
    public Currency currency;
    public int currencyId;
    public int authorId;
    public Date dateCreated;
    public int userPriority;

    public Transfer(int id, String description, Account sourceAccount, int sourceId, Account destinationAccount, int destinationId, int amount, Currency currency, int currencyId, int authorId, Date dateCreated, int userPriority) {
        this.id = id;
        this.description = description;
        this.sourceAccount = sourceAccount;
        this.sourceId = sourceId;
        this.destinationAccount = destinationAccount;
        this.destinationId = destinationId;
        this.amount = amount;
        this.currency = currency;
        this.currencyId = currencyId;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
        this.userPriority = userPriority;
    }

    public Transfer(String description, int sourceId, int destinationId, int amount, int currencyId) {
        this.description = description;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.amount = amount;
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", sourceAccount=" + sourceAccount +
                ", sourceId=" + sourceId +
                ", destinationAccount=" + destinationAccount +
                ", destinationId=" + destinationId +
                ", amount=" + amount +
                ", currency=" + currency +
                ", currencyId=" + currencyId +
                ", authorId=" + authorId +
                ", dateCreated=" + dateCreated +
                ", userPriority=" + userPriority +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }
}
