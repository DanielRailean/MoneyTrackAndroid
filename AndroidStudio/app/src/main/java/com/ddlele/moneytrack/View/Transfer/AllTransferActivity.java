package com.ddlele.moneytrack.View.Transfer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.TransferAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.TransferViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllTransferActivity extends AppCompatActivity implements TransferAdapter.OnListItemClickListener{

    RecyclerView transferList;
    TransferAdapter transferAdapter;

    TransferViewModel transferViewModel;

    private Context transferContext;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        transferContext = this;
        transferList = findViewById(R.id.recycler);
        transferList.setLayoutManager(new LinearLayoutManager(transferContext));

        transferViewModel = new ViewModelProvider(this).get(TransferViewModel.class);

        transferViewModel.getAll().observe(this, transfers1 -> {
            transferAdapter = new TransferAdapter(transfers1, (TransferAdapter.OnListItemClickListener) transferContext);
            transferList.setAdapter(transferAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllTransferActivity.this, AddTransferActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(AllTransferActivity.this, ViewTransferActivity.class);
        intent.putExtra("transferId", clickedItemIndex);
        startActivity(intent);
    }
}
