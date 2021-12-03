package com.ddlele.moneytrack.View.Currency;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddCurrencyViewModel;
import com.ddlele.moneytrack.ViewModel.CurrencyViewModel;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Category;
import com.ddlele.moneytrack.Wrappers.Currency;
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

public class ViewCurrencyActivity extends AppCompatActivity {

    EditText name;
    EditText amount;

    Button editButton;
    Button deleteButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context currencyContext;

    Currency currency;

    private AddCurrencyViewModel addCurrencyViewModel;

    private CurrencyViewModel currencyViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_currency);

        currencyContext = this;




        name = findViewById(R.id.currency_edit_nameField);
        amount = findViewById(R.id.currency_edit_price);

        editButton = findViewById(R.id.currency_editButton);
        deleteButton = findViewById(R.id.currency_deleteButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addCurrencyViewModel = new ViewModelProvider(this).get(AddCurrencyViewModel.class);

        currencyViewModel = new ViewModelProvider(this).get(CurrencyViewModel.class);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("currencyId")) {
            int id = bundle.getInt("currencyId");
            currency = currencyViewModel.getAll().getValue().get(id);
            name.setText(currency.getName());
            amount.setText(currency.getPriceInEur()+"");
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currency.setName(name.getText().toString());
                currency.setPriceInEur(Float.parseFloat(amount.getText().toString()));
                System.out.println(currency);
                currencyViewModel.update(currency);
                finish();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currencyViewModel.delete(currency.id);
                finish();

            }
        });
    }


}