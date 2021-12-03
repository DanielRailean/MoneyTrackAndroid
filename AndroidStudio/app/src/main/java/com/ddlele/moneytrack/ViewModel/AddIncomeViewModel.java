package com.ddlele.moneytrack.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.AccountRepository;
import com.ddlele.moneytrack.Model.CategoryRepository;
import com.ddlele.moneytrack.Model.CurrencyRepository;
import com.ddlele.moneytrack.Model.IncomeRepository;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Income;

import java.util.List;

public class AddIncomeViewModel extends ViewModel {
    private IncomeRepository incomeRepository;
    private AccountRepository accountRepository;
    private CategoryRepository categoryRepository;
    private CurrencyRepository currencyRepository;




    public AddIncomeViewModel(){
        incomeRepository = IncomeRepository.getInstance();
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


    public Income create(Income item){
        return incomeRepository.create(item);
    }

}
