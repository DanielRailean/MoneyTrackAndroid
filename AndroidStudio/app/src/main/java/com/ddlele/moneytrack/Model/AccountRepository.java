package com.ddlele.moneytrack.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ddlele.moneytrack.Networking.AccountAPI;
import com.ddlele.moneytrack.Networking.ServiceGenerator;
import com.ddlele.moneytrack.Networking.UserAPI;
import com.ddlele.moneytrack.Util.LocalStorage;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.User;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private static AccountRepository instance;
    private AccountAPI accountAPI;

    private MutableLiveData<List<Account>> allAccounts;

    private AccountRepository(){
        accountAPI = ServiceGenerator.getAccountAPI();
        allAccounts = new MutableLiveData<>();
        getAll();
    }

    public MutableLiveData<List<Account>> getAllAccounts() {
        return allAccounts;
    }

    public static AccountRepository getInstance(){
        if(instance==null){
            instance = new AccountRepository();
        }
        return instance;
    }

    public void getAll(){
        Call<List<Account>> call = accountAPI.getAll();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if(response.code()==200){
                    allAccounts.setValue(response.body());
                }else{
                    Log.e("accountAPI","call not 200");
                    Log.e("accountAPI",response.message());

                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e("accountAPI","call failed");
                Log.e("accountAPI", t.getMessage());
                t.printStackTrace();

            }
        });

    }

    public Account get(long id){
        final Account[] returned = new Account[1];
        Call<Account> call = accountAPI.get(id);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
        return returned[0];
    }

    public Account delete(long id){
        final Account[] returned = new Account[1];
        Call<Account> call = accountAPI.delete(id);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
        return returned[0];
    }
    public Account create(Account item){
        final Account[] returned = new Account[1];
        Call<Account> call = accountAPI.create(item);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
        return returned[0];
    }
    public Account update(Account item){
        final Account[] returned = new Account[1];
        Call<Account> call = accountAPI.update(item);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
        return returned[0];
    }
}
