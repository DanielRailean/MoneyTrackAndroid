package com.ddlele.moneytrack.Wrappers;

import java.util.Date;

public class Account{
    public int id;
    public String name;
    public double balance;
    public Currency currency;
    public int currencyId;
    public int userId;
    public boolean isShared;
    public int sharedWithId;
    public boolean sharingConfirmedOther;
    public Date dateCreated;
    public Date lastConfirmed;
    public int userPriority;

    public Account(int id, String name, double balance, Currency currency, int currencyId, int userId, boolean isShared, int sharedWithId, boolean sharingConfirmedOther, Date dateCreated, Date lastConfirmed, int userPriority) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
        this.currencyId = currencyId;
        this.userId = userId;
        this.isShared = isShared;
        this.sharedWithId = sharedWithId;
        this.sharingConfirmedOther = sharingConfirmedOther;
        this.dateCreated = dateCreated;
        this.lastConfirmed = lastConfirmed;
        this.userPriority = userPriority;
    }

    public Account(String name, double balance, int currencyId) {
        this.name = name;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public int getSharedWithId() {
        return sharedWithId;
    }

    public void setSharedWithId(int sharedWithId) {
        this.sharedWithId = sharedWithId;
    }

    public boolean isSharingConfirmedOther() {
        return sharingConfirmedOther;
    }

    public void setSharingConfirmedOther(boolean sharingConfirmedOther) {
        this.sharingConfirmedOther = sharingConfirmedOther;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastConfirmed() {
        return lastConfirmed;
    }

    public void setLastConfirmed(Date lastConfirmed) {
        this.lastConfirmed = lastConfirmed;
    }

    public int getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }
}

