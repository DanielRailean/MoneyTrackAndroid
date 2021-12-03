package com.ddlele.moneytrack.View.Expense;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.ExpenseAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.ExpenseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllExpenseActivity extends AppCompatActivity implements ExpenseAdapter.OnListItemClickListener{

    RecyclerView expenseList;
    ExpenseAdapter expenseAdapter;

    ExpenseViewModel expenseViewModel;

    private Context expenseContext;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        expenseContext = this;
        expenseList = findViewById(R.id.recyclerExpenses);
        expenseList.setLayoutManager(new LinearLayoutManager(expenseContext));

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        expenseViewModel.getAll().observe(this, expenses1 -> {
            expenseAdapter = new ExpenseAdapter(expenses1, (ExpenseAdapter.OnListItemClickListener) expenseContext);
            expenseList.setAdapter(expenseAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllExpenseActivity.this, AddExpenseActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(AllExpenseActivity.this, ViewExpenseActivity.class);
        intent.putExtra("expenseId", clickedItemIndex);
        startActivity(intent);
        finish();
    }
}
