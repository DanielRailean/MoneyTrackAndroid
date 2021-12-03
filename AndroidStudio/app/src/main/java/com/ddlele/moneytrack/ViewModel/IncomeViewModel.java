package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.IncomeRepository;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Income;

import java.util.List;

public class IncomeViewModel extends ViewModel {
    private IncomeRepository repository;

    private Income edited;

    public Income getEdited() {
        return edited;
    }

    public void setEdited(Income edited) {
        this.edited = edited;
    }

    public IncomeViewModel(){
        repository = IncomeRepository.getInstance();
    }

    public LiveData<List<Income>> getAll(){
        return repository.getAllIncomes();
    }


    public Income create(Income item){
        return repository.create(item);
    }

    public Income update(Income item){
        return repository.update(item);
    }

    public Income delete(int id){
        return repository.delete(id);
    }
    public Income get(int id){
        return repository.get(id);
    }
}
