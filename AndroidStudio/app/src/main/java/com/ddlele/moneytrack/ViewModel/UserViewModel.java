package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.UserRepository;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.User;

public class UserViewModel extends ViewModel {
    private UserRepository repository;

    public UserViewModel(){
        repository = UserRepository.getInstance();
    }

    public LiveData<JWT> getToken(){
        return repository.getToken();
    }

    public void login(User user){
        repository.login(user);
    }

    public void logout(){
        repository.logout();
    }
}
