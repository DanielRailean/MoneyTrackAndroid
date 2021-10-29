package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private List<String> expenses;

    public MainActivityViewModel() {
        expenses = new ArrayList<>();
        expenses.add("First Expense");
        expenses.add("Bilka Expense");
    }

    public List<String> getAllExpenses() {
        return expenses;
    }

    public void addExpense(String expense){
        expenses.add(expense);
    }

    public void deleteExpense(String expense){
        expenses.remove(expense);
    }
}
