package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.CurrencyRepository;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Currency;

import java.util.List;

public class CurrencyViewModel extends ViewModel {
    private CurrencyRepository repository;

    private Currency edited;

    public Currency getEdited() {
        return edited;
    }

    public void setEdited(Currency edited) {
        this.edited = edited;
    }

    public CurrencyViewModel(){
        repository = CurrencyRepository.getInstance();
    }

    public LiveData<List<Currency>> getAll(){
        return repository.getAllCurrencies();
    }


    public Currency create(Currency item){
        return repository.create(item);
    }

    public Currency update(Currency item){
        return repository.update(item);
    }

    public Currency delete(int id){
        return repository.delete(id);
    }
    public Currency get(int id){
        return repository.get(id);
    }
}
