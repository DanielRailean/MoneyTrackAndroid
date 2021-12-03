package com.ddlele.moneytrack.View.Transfer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AddTransferViewModel;
import com.ddlele.moneytrack.ViewModel.TransferViewModel;
import com.ddlele.moneytrack.Wrappers.Account;
import com.ddlele.moneytrack.Wrappers.Currency;
import com.ddlele.moneytrack.Wrappers.Transfer;
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

public class ViewTransferActivity extends AppCompatActivity {

    EditText name;
    EditText amount;
    Spinner currency;
    Spinner destination;
    Spinner source;

    Button editButton;
    Button deleteButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;

    private Context transferContext;

    Transfer transfer;

    private AddTransferViewModel addTransferViewModel;

    private TransferViewModel transferViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_transfer);

        transferContext = this;




        name = findViewById(R.id.transfer_edit_nameField);
        amount = findViewById(R.id.transfer_edit_price);
        currency = findViewById(R.id.transfer_edit_currency);
        destination = findViewById(R.id.transfer_edit_destination);
        source = findViewById(R.id.transfer_edit_source);
        editButton = findViewById(R.id.transfer_editButton);
        deleteButton = findViewById(R.id.transfer_deleteButton);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addTransferViewModel = new ViewModelProvider(this).get(AddTransferViewModel.class);

        transferViewModel = new ViewModelProvider(this).get(TransferViewModel.class);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("transferId")) {
            int id = bundle.getInt("transferId");
            transfer = transferViewModel.getAll().getValue().get(id);
            name.setText(transfer.getDescription());
            amount.setText(transfer.getAmount()+"");
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transfer.setDescription(name.getText().toString());
                transfer.setSourceId(((Account) source.getSelectedItem()).getId());
                transfer.setDestinationId(((Account) destination.getSelectedItem()).getId());
                transfer.setAmount(Integer.parseInt(amount.getText().toString()));
                transfer.setCurrencyId(((Currency) currency.getSelectedItem()).getId());
                System.out.println(transfer);
                transferViewModel.update(transfer);
                finish();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferViewModel.delete(transfer.id);
                finish();

            }
        });
        addTransferViewModel.getAllCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                ArrayAdapter<Currency> spinnerCurrencyAdapter = new ArrayAdapter<Currency>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, currencies);
                currency.setAdapter(spinnerCurrencyAdapter);
                List<Currency> result = currencies.stream()
                        .filter(item -> item.id==transfer.getCurrency().id)
                        .collect(Collectors.toList());
                currency.setSelection(currencies.indexOf(result.get(0)));
            }
        });
        addTransferViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                ArrayAdapter<Account> spinnerAccountAdapter = new ArrayAdapter<Account>(contextOfApplication, android.R.layout.simple_spinner_dropdown_item, accounts);
                source.setAdapter(spinnerAccountAdapter);
                destination.setAdapter(spinnerAccountAdapter);
                List<Account> result = accounts.stream()
                        .filter(item -> item.id==transfer.getSourceId())
                        .collect(Collectors.toList());
                source.setSelection(accounts.indexOf(result.get(0)));

                result = accounts.stream()
                        .filter(item -> item.id==transfer.getDestinationId())
                        .collect(Collectors.toList());
                destination.setSelection(accounts.indexOf(result.get(0)));
            }
        });
    }


}