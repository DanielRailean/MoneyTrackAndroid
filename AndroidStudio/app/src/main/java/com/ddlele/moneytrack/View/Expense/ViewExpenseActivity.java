package com.ddlele.moneytrack.View.Expense;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddExpenseViewModel;
import com.ddlele.moneytrack.ViewModel.ExpenseViewModel;
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
import java.util.stream.Collectors;

public class ViewExpenseActivity extends AppCompatActivity {

    EditText name;
    EditText amount;
    Spinner currency;
    Spinner category;
    Spinner account;

    Button editButton;
    Button deleteButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context expenseContext;

    Expense expense;

    private AddExpenseViewModel addExpenseViewModel;

    private ExpenseViewModel expenseViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_expense);

        expenseContext = this;




        name = findViewById(R.id.e_edit_nameField);
        amount = findViewById(R.id.e_edit_amount);
        currency = findViewById(R.id.e_edit_currency);
        category = findViewById(R.id.e_edit_category);
        account = findViewById(R.id.e_edit_account);
        editButton = findViewById(R.id.e_editButton);
        deleteButton = findViewById(R.id.e_deleteButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addExpenseViewModel = new ViewModelProvider(this).get(AddExpenseViewModel.class);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("expenseId")) {
            int id = bundle.getInt("expenseId");
            expense = expenseViewModel.getAll().getValue().get(id);
            name.setText(expense.getName());
            amount.setText(expense.getAmount()+"");
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expense.setName(name.getText().toString());
                expense.setAccountId(((Account) account.getSelectedItem()).getId());
                expense.setAmount(Integer.parseInt(amount.getText().toString()));
                expense.setCurrencyId(((Currency) currency.getSelectedItem()).getId());
                expense.setCategoryId(((Category) category.getSelectedItem()).getId());
                System.out.println(expense);
                expenseViewModel.update(expense);
                Intent intent = new Intent(ViewExpenseActivity.this, AllExpenseActivity.class);
                startActivity(intent);
                finish();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseViewModel.delete(expense.id);
                Intent intent = new Intent(ViewExpenseActivity.this, AllExpenseActivity.class);
                startActivity(intent);
                finish();

            }
        });
        addExpenseViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                ArrayAdapter<Category> spinnerCategoryAdapter = new ArrayAdapter<Category>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, categories);
                category.setAdapter(spinnerCategoryAdapter);
                List<Category> result = categories.stream()
                        .filter(item -> item.id==expense.getCategory().id)
                        .collect(Collectors.toList());
                currency.setSelection(categories.indexOf(result.get(0)));

            }
        });
        addExpenseViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
                List<Currency> result = currencies.stream()
                        .filter(item -> item.id==expense.getCurrency().id)
                        .collect(Collectors.toList());
                currency.setSelection(currencies.indexOf(result.get(0)));
            }
        });
        addExpenseViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                ArrayAdapter<Account> spinnerAccountAdapter = new ArrayAdapter<Account>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, accounts);
                account.setAdapter(spinnerAccountAdapter);
                List<Account> result = accounts.stream()
                        .filter(item -> item.id==expense.getAccountId())
                        .collect(Collectors.toList());
                currency.setSelection(accounts.indexOf(result.get(0)));
            }
        });
    }


}