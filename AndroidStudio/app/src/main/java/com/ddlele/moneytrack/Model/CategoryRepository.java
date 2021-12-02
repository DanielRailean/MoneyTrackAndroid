package com.ddlele.moneytrack.Model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ddlele.moneytrack.Networking.CategoryAPI;
import com.ddlele.moneytrack.Networking.ServiceGenerator;
import com.ddlele.moneytrack.Wrappers.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private static CategoryRepository instance;
    private CategoryAPI categoryAPI;

    private MutableLiveData<List<Category>> allCategories;

    private CategoryRepository(){
        categoryAPI = ServiceGenerator.getCategoryAPI();
        allCategories = new MutableLiveData<>();
        getAll();
    }

    public MutableLiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public static CategoryRepository getInstance(){
        if(instance==null){
            instance = new CategoryRepository();
        }
        return instance;
    }

    public void getAll(){
        Call<List<Category>> call = categoryAPI.getAll();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.code()==200){
                    allCategories.setValue(response.body());
                }else{
                    Log.e("categoryAPI","call not 200");
                    Log.e("categoryApi",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("categoryAPI","call failed");
                Log.e("categoryAPI", t.getMessage());
                t.printStackTrace();

            }
        });

    }

    public Category get(long id){
        final Category[] returned = new Category[1];
        Call<Category> call = categoryAPI.get(id);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
        return returned[0];
    }

    public Category delete(long id){
        final Category[] returned = new Category[1];
        Call<Category> call = categoryAPI.delete(id);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
        return returned[0];
    }
    public Category create(Category item){
        final Category[] returned = new Category[1];
        Call<Category> call = categoryAPI.create(item);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
        return returned[0];
    }
    public Category update(Category item){
        final Category[] returned = new Category[1];
        Call<Category> call = categoryAPI.update(item);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.code()==200){
                    returned[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
        return returned[0];
    }
}
