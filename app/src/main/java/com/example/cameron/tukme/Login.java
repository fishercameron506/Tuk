package com.example.cameron.tukme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //init variables
        this.etUsername = (EditText)findViewById(R.id.etEmail);
        this.etPassword = (EditText) findViewById(R.id.etPassword);

        this.btnLogin = (Button)findViewById(R.id.btnLogin);

        this.txtSignUp = (TextView)findViewById(R.id.txtSignUp);
        this.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(Login.this, Registration.class);
                startActivity(regIntent);
            }
        });

        this.db = new DatabaseHelper(this);







        this.secrecy = new Secrecy();
        //set on click listener
        this.btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //get data from shared preferences



                int userType = getType(etUsername.getText().toString(), secrecy.computeSHAHash(etPassword.getText().toString()));


                if(userExists(etUsername.getText().toString(), secrecy.computeSHAHash(etPassword.getText().toString())))
                {

                    if(userType == 0)
                    {

                        //passenger
                        Intent homeIntent = new Intent(Login.this, PassengerHome.class);
                        startActivity(homeIntent);
                    }else if(userType == 1)
                    {

                        //driver
                        Intent homeIntent = new Intent(Login.this, DriverHome.class);
                        startActivity(homeIntent);
                    }
                }


            }
        });


    }

    private boolean userExists(String username,String password)
    {
        boolean toReturn = true;
        Cursor result = db.getData(username,password);
        if(result.getCount() == 0)
        {
            showMessage("No user found","Please create an account");
            toReturn = false;
            return toReturn;
        }

        return toReturn;
    }

    private int getType(String username, String password)
    {
        int toReturn = 0;
        Cursor result = db.getData(username,password);
        if(result.getCount() == 0)
        {
            //showMessage("Error", "No data found");
        }
        while(result.moveToNext())
        {

            toReturn = result.getInt(5);
        }
        return toReturn;
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    private Button btnLogin;

    private EditText etUsername;
    private EditText etPassword;
    private TextView txtSignUp;
    private Secrecy secrecy;
    private SharedPreferences sharedPreferences;


    private DatabaseHelper db;

    private LocationManager locationManager;
    private LocationListener locationListener;

}
