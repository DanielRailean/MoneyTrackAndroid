package com.ddlele.moneytrack.View.Currency;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.CurrencyAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.CurrencyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllCurrencyActivity extends AppCompatActivity implements CurrencyAdapter.OnListItemClickListener{

    RecyclerView currencyList;
    CurrencyAdapter currencyAdapter;

    CurrencyViewModel currencyViewModel;

    private Context currencyContext;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        currencyContext = this;
        currencyList = findViewById(R.id.recycler);
        currencyList.setLayoutManager(new LinearLayoutManager(currencyContext));

        currencyViewModel = new ViewModelProvider(this).get(CurrencyViewModel.class);

        currencyViewModel.getAll().observe(this, currencys1 -> {
            currencyAdapter = new CurrencyAdapter(currencys1, (CurrencyAdapter.OnListItemClickListener) currencyContext);
            currencyList.setAdapter(currencyAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllCurrencyActivity.this, AddCurrencyActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(AllCurrencyActivity.this, ViewCurrencyActivity.class);
        intent.putExtra("currencyId", clickedItemIndex);
        startActivity(intent);
    }
}
