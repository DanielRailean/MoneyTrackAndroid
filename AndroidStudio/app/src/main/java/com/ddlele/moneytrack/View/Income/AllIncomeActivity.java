package com.ddlele.moneytrack.View.Income;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.IncomeAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.IncomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllIncomeActivity extends AppCompatActivity implements IncomeAdapter.OnListItemClickListener{

    RecyclerView incomeList;
    IncomeAdapter incomeAdapter;

    IncomeViewModel incomeViewModel;

    private Context incomeContext;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        incomeContext = this;
        incomeList = findViewById(R.id.recycler);
        incomeList.setLayoutManager(new LinearLayoutManager(incomeContext));

        incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);

        incomeViewModel.getAll().observe(this, incomes1 -> {
            incomeAdapter = new IncomeAdapter(incomes1, (IncomeAdapter.OnListItemClickListener) incomeContext);
            incomeList.setAdapter(incomeAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllIncomeActivity.this, AddIncomeActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(AllIncomeActivity.this, ViewIncomeActivity.class);
        intent.putExtra("incomeId", clickedItemIndex);
        startActivity(intent);
    }
}
