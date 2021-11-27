package com.ddlele.moneytrack.View;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.ExpenseAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.ExpenseViewModel;
import com.ddlele.moneytrack.ViewModel.UserViewModel;
import com.ddlele.moneytrack.Wrappers.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity {

    RecyclerView expenseList;
    ExpenseAdapter expenseAdapter;

    ExpenseViewModel expenseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        expenseList = findViewById(R.id.recyclerExpenses);
        expenseList.setLayoutManager(new LinearLayoutManager(this));

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        expenseViewModel.getAll();

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Bilka",20,"Dkk"));
        expenses.add(new Expense("Bilka",20,"Dkk"));
        expenses.add(new Expense("Bilka",20,"Dkk"));

        expenseAdapter = new ExpenseAdapter(expenses);
        System.out.println("expenses : "+expenses);
        expenseList.setAdapter(expenseAdapter);

        expenseViewModel.getAll().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                expenseAdapter = new ExpenseAdapter(expenses);
                System.out.println("expenses : "+expenses);
                expenseList.setAdapter(expenseAdapter);
            }
        });


    }
}
