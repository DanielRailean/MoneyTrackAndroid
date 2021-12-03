package com.ddlele.moneytrack.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ddlele.moneytrack.Networking.IncomeAPI;
import com.ddlele.moneytrack.Networking.ServiceGenerator;
import com.ddlele.moneytrack.Networking.UserAPI;
import com.ddlele.moneytrack.Util.LocalStorage;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Income;
import com.ddlele.moneytrack.Wrappers.User;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeRepository {
    private static IncomeRepository instance;
    private IncomeAPI incomeAPI;

    private MutableLiveData<List<Income>> allIncomes;

    private IncomeRepository(){
        incomeAPI = ServiceGenerator.getIncomeAPI();
        allIncomes = new MutableLiveData<>();
        getAll();
    }

    public MutableLiveData<List<Income>> getAllIncomes() {
        return allIncomes;
    }

    public static IncomeRepository getInstance(){
        if(instance==null){
            instance = new IncomeRepository();
        }
        return instance;
    }

    public void getAll(){
        Call<List<Income>> call = incomeAPI.getAll();
        call.enqueue(new Callback<List<Income>>() {
            @Override
            public void onResponse(Call<List<Income>> call, Response<List<Income>> response) {
                if(response.code()==200){
                    allIncomes.setValue(response.body());
                }else{
                    Log.e("incomeAPI","call not 200");
                }
            }

            @Override
            public void onFailure(Call<List<Income>> call, Throwable t) {
                Log.e("incomeAPI","call failed");
                Log.e("incomeAPI", t.getMessage());
                t.printStackTrace();

            }
        });

    }

    public Income get(long id){
        final Income[] returned = new Income[1];
        Call<Income> call = incomeAPI.get(id);
        call.enqueue(new Callback<Income>() {
            @Override
            public void onResponse(Call<Income> call, Response<Income> response) {
                if(response.code()==200){
                    returned[0] = response.body();

                }
            }

            @Override
            public void onFailure(Call<Income> call, Throwable t) {

            }
        });
        return returned[0];
    }

    public Income delete(long id){
        final Income[] returned = new Income[1];
        Call<Income> call = incomeAPI.delete(id);
        call.enqueue(new Callback<Income>() {
            @Override
            public void onResponse(Call<Income> call, Response<Income> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                    AccountRepository.getInstance().getAll();
                }
            }

            @Override
            public void onFailure(Call<Income> call, Throwable t) {

            }

        });
        return returned[0];
    }
    public Income create(Income item){
        final Income[] returned = new Income[1];
        Call<Income> call = incomeAPI.create(item);
        call.enqueue(new Callback<Income>() {
            @Override
            public void onResponse(Call<Income> call, Response<Income> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                    AccountRepository.getInstance().getAll();
                }else{
                    Log.e("incomeAPI","call not 200");
                }
            }

            @Override
            public void onFailure(Call<Income> call, Throwable t) {
                Log.e("incomeAPI","call failed");
                Log.e("incomeAPI", t.getMessage());
                t.printStackTrace();
            }
        });
        return returned[0];
    }
    public Income update(Income item){
        final Income[] returned = new Income[1];
        Call<Income> call = incomeAPI.update(item);
        call.enqueue(new Callback<Income>() {
            @Override
            public void onResponse(Call<Income> call, Response<Income> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                    AccountRepository.getInstance().getAll();

                }else{
                    Log.e("incomeAPI","call not 200");
                    Log.e("incomeAPI",response.code()+"");
                    Log.e("incomeAPI",response.message());
                }
            }

            @Override
            public void onFailure(Call<Income> call, Throwable t) {
                Log.e("incomeAPI","call failed");
                Log.e("incomeAPI", t.getMessage());
                t.printStackTrace();
            }
        });
        getAll();
        return returned[0];
    }
}
