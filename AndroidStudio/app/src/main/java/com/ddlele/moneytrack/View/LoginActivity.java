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

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView textView;
    EditText email;
    EditText password;
    Button loginButton;

    boolean logged;



    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;



    private UserViewModel userViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.mainText);
        email = findViewById(R.id.e_edit_price);
        password = findViewById(R.id.e_edit_source);
        loginButton = findViewById(R.id.loginButton);


        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupDrawerContent(navigationView);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getToken().observe(this, new Observer<JWT>() {
            @Override
            public void onChanged(JWT jwt) {
                if(!jwt.getToken().equals("empty")){
                    password.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    loginButton.setText("LOGOUT");
                    logged = true;
                    Intent intent = new Intent(LoginActivity.this, MainActivityAuth.class);
                    startActivity(intent);
                    finish();
                }else{
                    password.setVisibility(View.VISIBLE);
                    email.setVisibility(View.VISIBLE);
                    loginButton.setText("LOGIN");
                    logged = false;
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logged) {
                    userViewModel.logout();
                    return;
                }

                if(email.getText().toString().equals("")||email.getText().toString().equals("")) return;

                User toLogin = new User(email.getText().toString(),password.getText().toString());

                userViewModel.login(toLogin);
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
                intent= new Intent(LoginActivity.this, LoginActivity.class);

                break;
            case R.id.nav_register:
                intent= new Intent(LoginActivity.this, RegisterActivity.class);
                break;

            default:
                intent= new Intent(LoginActivity.this, MainActivity.class);
        }

        startActivity(intent);
        finish();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    public void signUp(View v){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
    }
}