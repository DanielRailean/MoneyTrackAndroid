package com.ddlele.moneytrack.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ddlele.moneytrack.Networking.ExpenseAPI;
import com.ddlele.moneytrack.Networking.ServiceGenerator;
import com.ddlele.moneytrack.Networking.UserAPI;
import com.ddlele.moneytrack.Util.LocalStorage;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Expense;
import com.ddlele.moneytrack.Wrappers.User;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseRepository {
    private static ExpenseRepository instance;
    private ExpenseAPI expenseAPI;

    private MutableLiveData<List<Expense>> allExpenses;

    private ExpenseRepository(){
        expenseAPI = ServiceGenerator.getExpenseAPI();
        allExpenses = new MutableLiveData<>();
        getAll();
    }

    public MutableLiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public static ExpenseRepository getInstance(){
        if(instance==null){
            instance = new ExpenseRepository();
        }
        return instance;
    }

    public void getAll(){
        Call<List<Expense>> call = expenseAPI.getAll();
        call.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
                if(response.code()==200){
                    allExpenses.setValue(response.body());
                }else{
                    Log.e("expenseAPI","call not 200");
                }
            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {
                Log.e("expenseAPI","call failed");
                Log.e("expenseAPI", t.getMessage());
                t.printStackTrace();

            }
        });

    }

    public Expense get(long id){
        final Expense[] returned = new Expense[1];
        Call<Expense> call = expenseAPI.get(id);
        call.enqueue(new Callback<Expense>() {
            @Override
            public void onResponse(Call<Expense> call, Response<Expense> response) {
                if(response.code()==200){
                   returned[0] = response.body();

                }
            }

            @Override
            public void onFailure(Call<Expense> call, Throwable t) {

            }
        });
        return returned[0];
    }

    public Expense delete(long id){
        final Expense[] returned = new Expense[1];
        Call<Expense> call = expenseAPI.delete(id);
        call.enqueue(new Callback<Expense>() {
            @Override
            public void onResponse(Call<Expense> call, Response<Expense> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                }
            }

            @Override
            public void onFailure(Call<Expense> call, Throwable t) {

            }

        });
        return returned[0];
    }
    public Expense create(Expense item){
        final Expense[] returned = new Expense[1];
        Call<Expense> call = expenseAPI.create(item);
        call.enqueue(new Callback<Expense>() {
            @Override
            public void onResponse(Call<Expense> call, Response<Expense> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();

                }else{
                    Log.e("expenseAPI","call not 200");
                }
            }

            @Override
            public void onFailure(Call<Expense> call, Throwable t) {
                Log.e("expenseAPI","call failed");
                Log.e("expenseAPI", t.getMessage());
                t.printStackTrace();
            }
        });
        return returned[0];
    }
    public Expense update(Expense item){
        final Expense[] returned = new Expense[1];
        Call<Expense> call = expenseAPI.update(item);
        call.enqueue(new Callback<Expense>() {
            @Override
            public void onResponse(Call<Expense> call, Response<Expense> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                }else{
                    Log.e("expenseAPI","call not 200");
                    Log.e("expenseAPI",response.code()+"");
                    Log.e("expenseAPI",response.message());
                }
            }

            @Override
            public void onFailure(Call<Expense> call, Throwable t) {
                Log.e("expenseAPI","call failed");
                Log.e("expenseAPI", t.getMessage());
                t.printStackTrace();
            }
        });
        getAll();
        return returned[0];
    }
}
