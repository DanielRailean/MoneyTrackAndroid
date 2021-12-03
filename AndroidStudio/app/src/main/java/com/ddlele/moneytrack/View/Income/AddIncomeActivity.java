package com.ddlele.moneytrack.View.Income;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddIncomeViewModel;
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

public class AddIncomeActivity extends AppCompatActivity {

    EditText name;
    EditText amount;
    Spinner currency;
    Spinner account;

    Button addButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context incomeContext;


    private AddIncomeViewModel addIncomeViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_income);

        incomeContext = this;

        name = findViewById(R.id.income_add_nameField);
        amount = findViewById(R.id.income_add_price);
        currency = findViewById(R.id.income_add_source);
        account = findViewById(R.id.income_add_destination);
        addButton = findViewById(R.id.addButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addIncomeViewModel = new ViewModelProvider(this).get(AddIncomeViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Income item = new Income(name.getText().toString(),((Account) account.getSelectedItem()).getId(),Integer.parseInt(amount.getText().toString()),((Currency) currency.getSelectedItem()).getId());
                addIncomeViewModel.create(item);
                finish();
            }
        });
        addIncomeViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
            }
        });
        addIncomeViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                ArrayAdapter<Account> spinnerAccountAdapter = new ArrayAdapter<Account>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, accounts);
                account.setAdapter(spinnerAccountAdapter);
            }
        });
    }


}