package com.ddlele.moneytrack.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.ExpenseViewModel;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Expense;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddExpenseActivity extends AppCompatActivity {
    
    EditText name;
    EditText amount;
    Spinner currency;
    Spinner category;
    Spinner accounts;
    
    Button addButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;



    private ExpenseViewModel expenseViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_expense);
        
        name = findViewById(R.id.e_nameField);
        amount = findViewById(R.id.e_amount);
        currency = findViewById(R.id.e_currency);
        category = findViewById(R.id.e_category);
        accounts = findViewById(R.id.e_account);
        addButton = findViewById(R.id.addButton);

        ArrayList<Currency> currencyArray = new ArrayList<Currency>();
        ArrayList<Account> accountArray = new ArrayList<Account>();
        ArrayList<Category> categoryArray = new ArrayList<Category>();

        ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(this, android.R.layout.simple_spinner_dropdown_item, currencyArray);
        ArrayAdapter<Account> spinnerAccountAdapter = new ArrayAdapter<Account>(this, android.R.layout.simple_spinner_dropdown_item, accountArray);
        ArrayAdapter<Category> spinnerCategoryAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_dropdown_item, categoryArray);

        currency.setAdapter(spinnerCurrencyAdapter);
        category.setAdapter(spinnerAccountAdapter);
        accounts.setAdapter(spinnerCategoryAdapter);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupDrawerContent(navigationView);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
//        displayExpenses();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expense item = new Expense(name.getText().toString(),((Account)accounts.getSelectedItem()).getId(),Integer.parseInt(amount.getText().toString()),currency.getId(),category.getId());
                expenseViewModel.create(item);
                Intent intent = new Intent(AddExpenseActivity.this, AllExpensesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goLogin(View v){
        Intent intent = new Intent(AddExpenseActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {

        Intent intent;
        switch(menuItem.getItemId()) {
            case R.id.nav_expenses:
                intent= new Intent(AddExpenseActivity.this, AllExpensesActivity.class);

                break;
            case R.id.nav_incomes:
                intent= new Intent(AddExpenseActivity.this, AddExpenseActivity.class);
                break;

            default:
                intent= new Intent(AddExpenseActivity.this, MainActivity.class);
        }

        startActivity(intent);
        finish();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
}