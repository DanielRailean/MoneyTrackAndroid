package com.ddlele.moneytrack.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.CategoryRepository;
import com.ddlele.moneytrack.Model.CategoryRepository;
import com.ddlele.moneytrack.Model.CurrencyRepository;
import com.ddlele.moneytrack.Model.CategoryRepository;
import com.ddlele.moneytrack.Model.ExpenseRepository;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Category;

import java.util.List;

public class AddCategoryViewModel extends ViewModel {
    private CategoryRepository categoryRepository;
    private CurrencyRepository currencyRepository;




    public AddCategoryViewModel(){
        categoryRepository = CategoryRepository.getInstance();
        currencyRepository = CurrencyRepository.getInstance();
    }

    public LiveData<List<Category>> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

    public LiveData<List<Currency>> getAllCurrencies(){
        return currencyRepository.getAllCurrencies();
    }


    public Category create(Category item){
        return categoryRepository.create(item);
    }

}
