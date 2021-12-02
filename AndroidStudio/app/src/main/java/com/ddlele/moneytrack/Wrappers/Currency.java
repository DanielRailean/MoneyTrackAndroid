package com.ddlele.moneytrack.Wrappers;

public class Currency{
    public int id;
    public String name;
    public double priceInEur;
    public int userId;
    public int isShared;
    public int userPriority;

    @Override
    public String toString() {
        return name;
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

    public double getPriceInEur() {
        return priceInEur;
    }

    public void setPriceInEur(double priceInEur) {
        this.priceInEur = priceInEur;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsShared() {
        return isShared;
    }

    public void setIsShared(int isShared) {
        this.isShared = isShared;
    }

    public int getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }

    public Currency(int id, String name, double priceInEur, int userId, int isShared, int userPriority) {
        this.id = id;
        this.name = name;
        this.priceInEur = priceInEur;
        this.userId = userId;
        this.isShared = isShared;
        this.userPriority = userPriority;
    }

    public Currency(String name, double priceInEur) {
        this.name = name;
        this.priceInEur = priceInEur;
    }
}