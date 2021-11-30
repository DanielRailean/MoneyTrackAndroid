package com.ddlele.moneytrack.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.ExpenseAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.ExpenseViewModel;
import com.ddlele.moneytrack.Wrappers.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AllExpensesActivity extends AppCompatActivity implements ExpenseAdapter.OnListItemClickListener{

    RecyclerView expenseList;
    ExpenseAdapter expenseAdapter;

    ExpenseViewModel expenseViewModel;

    private Context expenseContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        expenseContext = this;
        expenseList = findViewById(R.id.recyclerExpenses);
        expenseList.setLayoutManager(new LinearLayoutManager(expenseContext));

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        expenseViewModel.getAll();

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Loading",0,""));

        expenseAdapter = new ExpenseAdapter(expenses, this);
        System.out.println("expenses : "+expenses);
        expenseList.setAdapter(expenseAdapter);

        expenseViewModel.getAll().observe(this, expenses1 -> {
            expenseAdapter = new ExpenseAdapter(expenses1, (ExpenseAdapter.OnListItemClickListener) expenseContext);
            System.out.println("expenses : "+ expenses1);
            expenseList.setAdapter(expenseAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllExpensesActivity.this, AddExpenseActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        int pokemonNumber = clickedItemIndex + 1;
        Toast.makeText(this, "Expense Number: " + pokemonNumber, Toast.LENGTH_SHORT).show();
    }
}
