package com.ddlele.moneytrack.Model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ddlele.moneytrack.Networking.CurrencyAPI;
import com.ddlele.moneytrack.Networking.ServiceGenerator;
import com.ddlele.moneytrack.Wrappers.Currency;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRepository {
    private static CurrencyRepository instance;
    private CurrencyAPI currencyAPI;

    private MutableLiveData<List<Currency>> allCurrencies;

    private CurrencyRepository(){
        currencyAPI = ServiceGenerator.getCurrencyAPI();
        allCurrencies = new MutableLiveData<>();
        getAll();
    }

    public MutableLiveData<List<Currency>> getAllCurrencies() {
        return allCurrencies;
    }

    public static CurrencyRepository getInstance(){
        if(instance==null){
            instance = new CurrencyRepository();
        }
        return instance;
    }

    public void getAll(){
        Call<List<Currency>> call = currencyAPI.getAll();
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                if(response.code()==200){
                    allCurrencies.setValue(response.body());
                }else{
                    Log.e("currencyAPI","call not 200");
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Log.e("currencyAPI","call failed");
                Log.e("currencyAPI", t.getMessage());
                t.printStackTrace();

            }
        });

    }

    public Currency get(long id){
        final Currency[] returned = new Currency[1];
        Call<Currency> call = currencyAPI.get(id);
        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {

            }
        });
        return returned[0];
    }

    public Currency delete(long id){
        final Currency[] returned = new Currency[1];
        Call<Currency> call = currencyAPI.delete(id);
        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {

            }
        });
        return returned[0];
    }
    public Currency create(Currency item){
        final Currency[] returned = new Currency[1];
        Call<Currency> call = currencyAPI.create(item);
        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {

            }
        });
        return returned[0];
    }
    public Currency update(Currency item){
        final Currency[] returned = new Currency[1];
        Call<Currency> call = currencyAPI.update(item);
        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {

            }
        });
        return returned[0];
    }
}
