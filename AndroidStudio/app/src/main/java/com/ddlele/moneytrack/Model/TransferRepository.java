package com.ddlele.moneytrack.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ddlele.moneytrack.Networking.TransferAPI;
import com.ddlele.moneytrack.Networking.ServiceGenerator;
import com.ddlele.moneytrack.Networking.UserAPI;
import com.ddlele.moneytrack.Util.LocalStorage;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Transfer;
import com.ddlele.moneytrack.Wrappers.User;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferRepository {
    private static TransferRepository instance;
    private TransferAPI transferAPI;

    private MutableLiveData<List<Transfer>> allTransfers;

    private TransferRepository(){
        transferAPI = ServiceGenerator.getTransferAPI();
        allTransfers = new MutableLiveData<>();
        getAll();
    }

    public MutableLiveData<List<Transfer>> getAllTransfers() {
        return allTransfers;
    }

    public static TransferRepository getInstance(){
        if(instance==null){
            instance = new TransferRepository();
        }
        return instance;
    }

    public void getAll(){
        Call<List<Transfer>> call = transferAPI.getAll();
        call.enqueue(new Callback<List<Transfer>>() {
            @Override
            public void onResponse(Call<List<Transfer>> call, Response<List<Transfer>> response) {
                if(response.code()==200){
                    allTransfers.setValue(response.body());
                }else{
                    Log.e("transferAPI","call not 200");
                }
            }

            @Override
            public void onFailure(Call<List<Transfer>> call, Throwable t) {
                Log.e("transferAPI","call failed");
                Log.e("transferAPI", t.getMessage());
                t.printStackTrace();

            }
        });

    }

    public Transfer get(long id){
        final Transfer[] returned = new Transfer[1];
        Call<Transfer> call = transferAPI.get(id);
        call.enqueue(new Callback<Transfer>() {
            @Override
            public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                if(response.code()==200){
                    returned[0] = response.body();

                }
            }

            @Override
            public void onFailure(Call<Transfer> call, Throwable t) {

            }
        });
        return returned[0];
    }

    public Transfer delete(long id){
        final Transfer[] returned = new Transfer[1];
        Call<Transfer> call = transferAPI.delete(id);
        call.enqueue(new Callback<Transfer>() {
            @Override
            public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                    AccountRepository.getInstance().getAll();

                }
            }

            @Override
            public void onFailure(Call<Transfer> call, Throwable t) {

            }

        });
        return returned[0];
    }
    public Transfer create(Transfer item){
        final Transfer[] returned = new Transfer[1];
        Call<Transfer> call = transferAPI.create(item);
        call.enqueue(new Callback<Transfer>() {
            @Override
            public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                    AccountRepository.getInstance().getAll();

                }else{
                    Log.e("transferAPI","call not 200");
                }
            }

            @Override
            public void onFailure(Call<Transfer> call, Throwable t) {
                Log.e("transferAPI","call failed");
                Log.e("transferAPI", t.getMessage());
                t.printStackTrace();
            }
        });
        return returned[0];
    }
    public Transfer update(Transfer item){
        final Transfer[] returned = new Transfer[1];
        Call<Transfer> call = transferAPI.update(item);
        call.enqueue(new Callback<Transfer>() {
            @Override
            public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                    getAll();
                    AccountRepository.getInstance().getAll();

                }else{
                    Log.e("transferAPI","call not 200");
                    Log.e("transferAPI",response.code()+"");
                    Log.e("transferAPI",response.message());
                }
            }

            @Override
            public void onFailure(Call<Transfer> call, Throwable t) {
                Log.e("transferAPI","call failed");
                Log.e("transferAPI", t.getMessage());
                t.printStackTrace();
            }
        });
        getAll();
        return returned[0];
    }
}
