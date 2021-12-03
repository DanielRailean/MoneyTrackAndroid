package com.ddlele.moneytrack.Wrappers;

import java.util.Date;

public class Expense {
    public int id;
    public String name;
    public int accountId;
    public Account account;
    public int amount;
    public int currencyId;
    public Currency currency;
    public int authorId;
    public int categoryId;
    public Category category;
    public String dateCreated;
    public int userPriority;

    public Expense(String name, int accountId, int amount, int currencyId, int categoryId) {
        this.name = name;
        this.accountId = accountId;
        this.amount = amount;
        this.currencyId = currencyId;
        this.categoryId = categoryId;
    }

    public Expense(int id, String name, int accountId, Account account, int amount, int currencyId, Currency currency, int authorId, int categoryId, Category category, String dateCreated, int userPriority) {
        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.account = account;
        this.amount = amount;
        this.currencyId = currencyId;
        this.currency = currency;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.category = category;
        this.dateCreated = dateCreated;
        this.userPriority = userPriority;
    }

    public Expense(String bika, int i, String dkk) {
        this.name = bika;
        this.amount = i;
        this.currency = new Currency(dkk,1);
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountId=" + accountId +
                ", account=" + account +
                ", amount=" + amount +
                ", currencyId=" + currencyId +
                ", currency=" + currency +
                ", authorId=" + authorId +
                ", categoryId=" + categoryId +
                ", category=" + category +
                ", dateCreated='" + dateCreated + '\'' +
                ", userPriority=" + userPriority +
                '}';
    }
}
