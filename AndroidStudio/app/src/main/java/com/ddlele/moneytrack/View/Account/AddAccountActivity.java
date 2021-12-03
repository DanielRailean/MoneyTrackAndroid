package com.ddlele.moneytrack.View.Account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddAccountViewModel;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Currency;
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

public class AddAccountActivity extends AppCompatActivity {

    EditText name;
    EditText balance;
    Spinner currency;

    Button addButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context accountContext;


    private AddAccountViewModel addAccountViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_account);

        accountContext = this;

        name = findViewById(R.id.account_add_nameField);
        balance = findViewById(R.id.account_add_balance);
        currency = findViewById(R.id.account_add_currency);
        addButton = findViewById(R.id.addButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addAccountViewModel = new ViewModelProvider(this).get(AddAccountViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account item = new Account(name.getText().toString(),Float.parseFloat(balance.getText().toString()),((Currency) currency.getSelectedItem()).getId());
                addAccountViewModel.create(item);
                finish();
            }
        });
        addAccountViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
            }
        });
    }


}