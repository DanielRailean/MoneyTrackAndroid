package com.ddlele.moneytrack.Wrappers;

import java.util.Date;

public class Category{
    public int id;
    public String name;
    public int budget;
    public double currentSpent;
    public Currency currency;
    public int currencyId;
    public int userId;
    public Date time;
    public int userPriority;

    public Category(int id, String name, int budget, double currentSpent, Currency currency, int currencyId, int userId, Date time, int userPriority) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.currentSpent = currentSpent;
        this.currency = currency;
        this.currencyId = currencyId;
        this.userId = userId;
        this.time = time;
        this.userPriority = userPriority;
    }

    public Category(String name, int budget, double currentSpent, int currencyId) {
        this.name = name;
        this.budget = budget;
        this.currentSpent = currentSpent;
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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public double getCurrentSpent() {
        return currentSpent;
    }

    public void setCurrentSpent(double currentSpent) {
        this.currentSpent = currentSpent;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }
}