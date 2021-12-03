package com.ddlele.moneytrack.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.View.Account.AllAccountActivity;
import com.ddlele.moneytrack.View.Category.AllCategoryActivity;
import com.ddlele.moneytrack.View.Currency.AllCurrencyActivity;
import com.ddlele.moneytrack.View.Expense.AddExpenseActivity;
import com.ddlele.moneytrack.View.Expense.AllExpenseActivity;
import com.ddlele.moneytrack.View.Income.AllIncomeActivity;
import com.ddlele.moneytrack.View.Transfer.AllTransferActivity;
import com.ddlele.moneytrack.ViewModel.UserViewModel;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivityAuth extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;



    private UserViewModel userViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_auth);

        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout_auth);
        navigationView = findViewById(R.id.navigationViewAuth);


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(navigationView);



        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getToken().observe(this, new Observer<JWT>() {
            @Override
            public void onChanged(JWT jwt) {
                if (jwt.getToken().equals("empty") || jwt.getToken().equals("loading")) {
                    Intent intent = new Intent(MainActivityAuth.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_settings:
                userViewModel.logout();
                Intent intent = new Intent(MainActivityAuth.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {

        Intent intent;
        switch(menuItem.getItemId()) {
            case R.id.nav_expenses:
                intent= new Intent(MainActivityAuth.this, AllExpenseActivity.class);

                break;
            case R.id.nav_incomes:
                intent= new Intent(MainActivityAuth.this, AllIncomeActivity.class);
                break;
            case R.id.nav_transfers:
                intent= new Intent(MainActivityAuth.this, AllTransferActivity.class);
                break;
            case R.id.nav_currencies:
                intent= new Intent(MainActivityAuth.this, AllCurrencyActivity.class);
                break;
            case R.id.nav_accounts:
                intent= new Intent(MainActivityAuth.this, AllAccountActivity.class);
                break;
            case R.id.nav_categories:
                intent= new Intent(MainActivityAuth.this, AllCategoryActivity.class);
                break;
            default:
                intent= new Intent(MainActivityAuth.this, AllExpenseActivity.class);
        }

        startActivity(intent);

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    public void addNewExpense(View view) {
        Intent intent= new Intent(MainActivityAuth.this, AddExpenseActivity.class);
        startActivity(intent);

    }
}