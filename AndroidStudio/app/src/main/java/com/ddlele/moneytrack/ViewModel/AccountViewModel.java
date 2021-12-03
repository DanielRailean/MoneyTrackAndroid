package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.AccountRepository;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Account;

import java.util.List;

public class AccountViewModel extends ViewModel {
    private AccountRepository repository;

    private Account edited;

    public Account getEdited() {
        return edited;
    }

    public void setEdited(Account edited) {
        this.edited = edited;
    }

    public AccountViewModel(){
        repository = AccountRepository.getInstance();
    }

    public LiveData<List<Account>> getAll(){
        return repository.getAllAccounts();
    }


    public Account create(Account item){
        return repository.create(item);
    }

    public Account update(Account item){
        return repository.update(item);
    }

    public Account delete(int id){
        return repository.delete(id);
    }
    public Account get(int id){
        return repository.get(id);
    }
}
