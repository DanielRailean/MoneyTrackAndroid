package com.ddlele.moneytrack.View.Account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.AccountAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.AccountViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllAccountActivity extends AppCompatActivity implements AccountAdapter.OnListItemClickListener{

    RecyclerView accountList;
    AccountAdapter accountAdapter;

    AccountViewModel accountViewModel;

    private Context accountContext;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        accountContext = this;
        accountList = findViewById(R.id.recyclerAccount);
        accountList.setLayoutManager(new LinearLayoutManager(accountContext));

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getAll().observe(this, accounts1 -> {
            accountAdapter = new AccountAdapter(accounts1, (AccountAdapter.OnListItemClickListener) accountContext);
            accountList.setAdapter(accountAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllAccountActivity.this, AddAccountActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(AllAccountActivity.this, ViewAccountActivity.class);
        intent.putExtra("accountId", clickedItemIndex);
        startActivity(intent);
    }
}
