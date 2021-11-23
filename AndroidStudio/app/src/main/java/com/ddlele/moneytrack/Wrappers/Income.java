package com.ddlele.moneytrack.Wrappers;

import java.util.Date;

public class Income {
    public int id;
    public String name;
    public int accountId;
    public Account account;
    public int amount;
    public int currencyId;
    public Currency currency;
    public int authorId;
    public Date dateCreated;
    public int userPriority;

    public Income(int id, String name, int accountId, Account account, int amount, int currencyId, Currency currency, int authorId, Date dateCreated, int userPriority) {
        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.account = account;
        this.amount = amount;
        this.currencyId = currencyId;
        this.currency = currency;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
        this.userPriority = userPriority;
    }

    public Income(String name, int accountId, int amount, int currencyId) {
        this.name = name;
        this.accountId = accountId;
        this.amount = amount;
        this.currencyId = currencyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
