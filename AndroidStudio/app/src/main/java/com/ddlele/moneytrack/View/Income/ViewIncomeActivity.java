package com.ddlele.moneytrack.View.Income;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddIncomeViewModel;
import com.ddlele.moneytrack.ViewModel.IncomeViewModel;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Income;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.stream.Collectors;

public class ViewIncomeActivity extends AppCompatActivity {

    EditText name;
    EditText amount;
    Spinner currency;
    Spinner account;

    Button editButton;
    Button deleteButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context incomeContext;

    Income income;

    private AddIncomeViewModel addIncomeViewModel;

    private IncomeViewModel incomeViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_income);

        incomeContext = this;




        name = findViewById(R.id.income_edit_nameField);
        amount = findViewById(R.id.income_edit_price);
        currency = findViewById(R.id.income_edit_source);
        account = findViewById(R.id.income_edit_destination);
        editButton = findViewById(R.id.income_editButton);
        deleteButton = findViewById(R.id.income_deleteButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addIncomeViewModel = new ViewModelProvider(this).get(AddIncomeViewModel.class);

        incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("incomeId")) {
            int id = bundle.getInt("incomeId");
            income = incomeViewModel.getAll().getValue().get(id);
            name.setText(income.getName());
            amount.setText(income.getAmount()+"");
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                income.setName(name.getText().toString());
                income.setAccountId(((Account) account.getSelectedItem()).getId());
                income.setAmount(Integer.parseInt(amount.getText().toString()));
                income.setCurrencyId(((Currency) currency.getSelectedItem()).getId());
                System.out.println(income);
                incomeViewModel.update(income);
                finish();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomeViewModel.delete(income.id);
                finish();

            }
        });
        addIncomeViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
                List<Currency> result = currencies.stream()
                        .filter(item -> item.id==income.getCurrency().id)
                        .collect(Collectors.toList());
                currency.setSelection(currencies.indexOf(result.get(0)));
            }
        });
        addIncomeViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                ArrayAdapter<Account> spinnerAccountAdapter = new ArrayAdapter<Account>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, accounts);
                account.setAdapter(spinnerAccountAdapter);
                List<Account> result = accounts.stream()
                        .filter(item -> item.id==income.getAccountId())
                        .collect(Collectors.toList());
                currency.setSelection(accounts.indexOf(result.get(0)));
            }
        });
    }


}