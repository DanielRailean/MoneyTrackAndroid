package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.TransferRepository;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Transfer;

import java.util.List;

public class TransferViewModel extends ViewModel {
    private TransferRepository repository;

    private Transfer edited;

    public Transfer getEdited() {
        return edited;
    }

    public void setEdited(Transfer edited) {
        this.edited = edited;
    }

    public TransferViewModel(){
        repository = TransferRepository.getInstance();
    }

    public LiveData<List<Transfer>> getAll(){
        return repository.getAllTransfers();
    }


    public Transfer create(Transfer item){
        return repository.create(item);
    }

    public Transfer update(Transfer item){
        return repository.update(item);
    }

    public Transfer delete(int id){
        return repository.delete(id);
    }
    public Transfer get(int id){
        return repository.get(id);
    }
}
