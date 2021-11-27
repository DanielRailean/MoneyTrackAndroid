package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.ExpenseRepository;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Expense;

import java.util.List;

public class ExpenseViewModel extends ViewModel {
    private ExpenseRepository repository;

    public ExpenseViewModel(){
        repository = ExpenseRepository.getInstance();
    }

    public LiveData<List<Expense>> getAll(){
        return repository.getAllExpenses();
    }


    public Expense create(Expense item){
        return repository.create(item);
    }

    public Expense update(Expense item){
        return repository.update(item);
    }

    public Expense delete(int id){
        return repository.delete(id);
    }
    public Expense get(int id){
        return repository.get(id);
    }
}
