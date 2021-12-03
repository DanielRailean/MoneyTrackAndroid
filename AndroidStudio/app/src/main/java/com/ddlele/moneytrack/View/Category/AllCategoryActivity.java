package com.ddlele.moneytrack.View.Category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.Adapters.CategoryAdapter;
import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllCategoryActivity extends AppCompatActivity implements CategoryAdapter.OnListItemClickListener{

    RecyclerView categoryList;
    CategoryAdapter categoryAdapter;

    CategoryViewModel categoryViewModel;

    private Context categoryContext;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryContext = this;
        categoryList = findViewById(R.id.recycler);
        categoryList.setLayoutManager(new LinearLayoutManager(categoryContext));

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getAll().observe(this, categorys1 -> {
            categoryAdapter = new CategoryAdapter(categorys1, (CategoryAdapter.OnListItemClickListener) categoryContext);
            categoryList.setAdapter(categoryAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllCategoryActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(AllCategoryActivity.this, ViewCategoryActivity.class);
        intent.putExtra("categoryId", clickedItemIndex);
        startActivity(intent);
    }
}
