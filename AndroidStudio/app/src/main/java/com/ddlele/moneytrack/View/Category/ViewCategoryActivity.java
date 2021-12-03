package com.ddlele.moneytrack.View.Category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddCategoryViewModel;
import com.ddlele.moneytrack.ViewModel.CategoryViewModel;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Category;
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

public class ViewCategoryActivity extends AppCompatActivity {

    EditText name;
    EditText spent;
    EditText total;
    Spinner currency;

    Button editButton;
    Button deleteButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context categoryContext;

    Category category;

    private AddCategoryViewModel addCategoryViewModel;

    private CategoryViewModel categoryViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_category);

        categoryContext = this;




        name = findViewById(R.id.category_edit_nameField);
        spent = findViewById(R.id.category_edit_spent);
        total = findViewById(R.id.category_edit_total);
        currency = findViewById(R.id.category_edit_currency);
        editButton = findViewById(R.id.category_editButton);
        deleteButton = findViewById(R.id.category_deleteButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addCategoryViewModel = new ViewModelProvider(this).get(AddCategoryViewModel.class);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("categoryId")) {
            int id = bundle.getInt("categoryId");
            category = categoryViewModel.getAll().getValue().get(id);
            name.setText(category.getName());
            spent.setText(category.getCurrentSpent()+"");
            total.setText(category.getBudget()+"");
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category.setName(name.getText().toString());
                category.setCurrentSpent(Double.parseDouble(spent.getText().toString()));
                category.setBudget(Integer.parseInt(total.getText().toString()));
                category.setCurrencyId(((Currency) currency.getSelectedItem()).getId());
                System.out.println(category);
                categoryViewModel.update(category);
                finish();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryViewModel.delete(category.id);
                finish();

            }
        });
        addCategoryViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
                List<Currency> result = currencies.stream()
                        .filter(item -> item.id==category.getCurrency().id)
                        .collect(Collectors.toList());
                currency.setSelection(currencies.indexOf(result.get(0)));
            }
        });
    }


}