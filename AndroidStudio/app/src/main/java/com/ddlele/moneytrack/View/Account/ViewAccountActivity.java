package com.ddlele.moneytrack.View.Account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddAccountViewModel;
import com.ddlele.moneytrack.ViewModel.AccountViewModel;
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
import java.util.stream.Collectors;

public class ViewAccountActivity extends AppCompatActivity {

    EditText name;
    EditText balance;
    Spinner currency;


    Button editButton;
    Button deleteButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context accountContext;

    Account account;

    private AddAccountViewModel addAccountViewModel;

    private AccountViewModel accountViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_account);

        accountContext = this;




        name = findViewById(R.id.account_edit_nameField);
        balance = findViewById(R.id.account_edit_balance);
        currency = findViewById(R.id.account_edit_currency);
        editButton = findViewById(R.id.account_editButton);
        deleteButton = findViewById(R.id.account_deleteButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addAccountViewModel = new ViewModelProvider(this).get(AddAccountViewModel.class);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("accountId")) {
            int id = bundle.getInt("accountId");
            account = accountViewModel.getAll().getValue().get(id);
            name.setText(account.getName());
            balance.setText(account.getBalance()+"");
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account.setName(name.getText().toString());
                account.setBalance(Double.parseDouble(balance.getText().toString()));
                account.setCurrencyId(((Currency) currency.getSelectedItem()).getId());
                System.out.println(account);
                accountViewModel.update(account);
                finish();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountViewModel.delete(account.id);
                finish();

            }
        });
        addAccountViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
                List<Currency> result = currencies.stream()
                        .filter(item -> item.id==account.getCurrency().id)
                        .collect(Collectors.toList());
                currency.setSelection(currencies.indexOf(result.get(0)));
            }
        });
    }


}