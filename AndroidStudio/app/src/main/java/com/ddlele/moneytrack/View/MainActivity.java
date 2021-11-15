package com.ddlele.moneytrack.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.ViewModel.UserViewModel;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.User;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    ProgressBar progressBar;



    private UserViewModel userViewModel;

    public static Context contextOfApplication;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        displayExpenses();
        userViewModel.getToken().observe(this, new Observer<JWT>() {
            @Override
            public void onChanged(JWT jwt) {
                if(jwt.getToken().equals("loading")){
                    progressBar.setVisibility(View.VISIBLE);
                }else if
                (jwt.getToken().equals("empty")){
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                    loginButton.setVisibility(View.INVISIBLE);
                    registerButton.setVisibility(View.INVISIBLE);
                }

//                progressBar.setVisibility(View.INVISIBLE);


            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}