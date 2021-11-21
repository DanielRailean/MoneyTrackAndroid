package com.ddlele.moneytrack.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.UserViewModel;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.User;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private TextView textView;
    EditText name;
    EditText email;
    EditText password;
    Button registerButton;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    boolean logged;



    private UserViewModel userViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        textView = findViewById(R.id.mainText);
        name = findViewById(R.id.nameField);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        registerButton = findViewById(R.id.registerButton);


        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupDrawerContent(navigationView);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        displayExpenses();
        userViewModel.getToken().observe(this, new Observer<JWT>() {
            @Override
            public void onChanged(JWT jwt) {
                if(jwt.getToken().equals("empty")||jwt.getToken().equals("loading")){
                    password.setVisibility(View.VISIBLE);
                    email.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    registerButton.setVisibility(View.VISIBLE);
                    registerButton.setText("SUBMIT");
                    logged = false;
                }else{
                    password.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.INVISIBLE);
                    registerButton.setText("LOGOUT");
                    logged = true;
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logged) {
                    textView.setText("Logging out");
                    userViewModel.logout();
                    return;
                }

                if(email.getText().toString().equals("")||name.getText().toString().equals("")||password.getText().toString().equals("")) return;

                User toReg = new User(email.getText().toString(),password.getText().toString(),name.getText().toString());
                textView.setText(toReg.toString());

                userViewModel.register(toReg);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goLogin(View v){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
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
            case R.id.nav_login:
                intent= new Intent(RegisterActivity.this, LoginActivity.class);

                break;
            case R.id.nav_register:
                intent= new Intent(RegisterActivity.this, RegisterActivity.class);
                break;

            default:
                intent= new Intent(RegisterActivity.this, MainActivity.class);
        }

        startActivity(intent);
        finish();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
}