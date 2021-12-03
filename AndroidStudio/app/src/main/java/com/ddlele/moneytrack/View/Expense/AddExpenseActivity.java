package com.ddlele.moneytrack.View.Expense;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddExpenseViewModel;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Expense;
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

public class AddExpenseActivity extends AppCompatActivity {
    
    EditText name;
    EditText amount;
    Spinner currency;
    Spinner category;
    Spinner account;
    
    Button addButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context expenseContext;


    private AddExpenseViewModel addExpenseViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_expense);

        expenseContext = this;

        name = findViewById(R.id.e_edit_nameField);
        amount = findViewById(R.id.e_edit_price);
        currency = findViewById(R.id.e_edit_source);
        category = findViewById(R.id.e_category);
        account = findViewById(R.id.e_edit_destination);
        addButton = findViewById(R.id.addButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addExpenseViewModel = new ViewModelProvider(this).get(AddExpenseViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expense item = new Expense(name.getText().toString(),((Account) account.getSelectedItem()).getId(),Integer.parseInt(amount.getText().toString()),((Currency) currency.getSelectedItem()).getId(),((Category) category.getSelectedItem()).getId());
                addExpenseViewModel.create(item);
                Intent intent = new Intent(AddExpenseActivity.this, AllExpenseActivity.class);
                startActivity(intent);
                finish();
            }
        });
        addExpenseViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                ArrayAdapter<Category> spinnerCategoryAdapter = new ArrayAdapter<Category>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, categories);
                category.setAdapter(spinnerCategoryAdapter);
            }
        });
        addExpenseViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
            }
        });
        addExpenseViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                ArrayAdapter<Account> spinnerAccountAdapter = new ArrayAdapter<Account>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, accounts);
                account.setAdapter(spinnerAccountAdapter);
            }
        });
    }


}