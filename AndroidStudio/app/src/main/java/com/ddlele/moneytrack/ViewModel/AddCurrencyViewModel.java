package com.ddlele.moneytrack.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.CurrencyRepository;
import com.ddlele.moneytrack.Model.CategoryRepository;
import com.ddlele.moneytrack.Model.CurrencyRepository;
import com.ddlele.moneytrack.Model.CurrencyRepository;
import com.ddlele.moneytrack.Model.ExpenseRepository;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Currency;

import java.util.List;

public class AddCurrencyViewModel extends ViewModel {
    private CurrencyRepository currencyRepository;




    public AddCurrencyViewModel(){
        currencyRepository = CurrencyRepository.getInstance();
    }

    public LiveData<List<Currency>> getAllCurrencies(){
        return currencyRepository.getAllCurrencies();
    }

    public Currency create(Currency item){
        return currencyRepository.create(item);
    }

}
