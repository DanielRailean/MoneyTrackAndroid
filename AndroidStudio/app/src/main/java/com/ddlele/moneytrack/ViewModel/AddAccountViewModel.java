package com.ddlele.moneytrack.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.AccountRepository;
import com.ddlele.moneytrack.Model.CategoryRepository;
import com.ddlele.moneytrack.Model.CurrencyRepository;
import com.ddlele.moneytrack.Model.AccountRepository;
import com.ddlele.moneytrack.Model.ExpenseRepository;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Account;

import java.util.List;

public class AddAccountViewModel extends ViewModel {
    private AccountRepository accountRepository;
    private CategoryRepository categoryRepository;
    private CurrencyRepository currencyRepository;




    public AddAccountViewModel(){
        accountRepository = AccountRepository.getInstance();
        categoryRepository = CategoryRepository.getInstance();
        currencyRepository = CurrencyRepository.getInstance();
    }

    public LiveData<List<Account>> getAllAccounts(){
        return accountRepository.getAllAccounts();
    }
    public LiveData<List<Category>> getAllCategories(){
        return categoryRepository.getAllCategories();
    }
    public LiveData<List<Currency>> getAllCurrencies(){
        return currencyRepository.getAllCurrencies();
    }


    public Account create(Account item){
        return accountRepository.create(item);
    }

}
