package com.example.cameron.tukme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class DriverReg extends Activity {

    public DriverReg()
    {
        this.name = "";
        this.lastname = "";
        this.username = "";
        this.password = "";
        this.secrecy = new Secrecy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reg);

        this.etName = (EditText)findViewById(R.id.etName);
        this.etLastname = (EditText)findViewById(R.id.etLastName);
        this.etUsername = (EditText)findViewById(R.id.etEmail);
        this.etPassword = (EditText)findViewById(R.id.etPassword);

        this.btnRegister = (Button) findViewById(R.id.btnRegister);

        this.db = new DatabaseHelper(this);
        //create shared preferences object
        this.sharedPreferences = getSharedPreferences("UserLoginInfo", Context.MODE_PRIVATE);

        this.btnRegister.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                saveToSharedPref();
                Intent loginIntent = new Intent(DriverReg.this, Login.class);
                startActivity(loginIntent);
            }
        });
    }

    private void saveToSharedPref()
    {
        //save users registration information in shared preferences
        SharedPreferences.Editor editor = this.sharedPreferences.edit();

        editor.putString("name", this.etName.getText().toString());
        editor.putString("lastname", this.etLastname.getText().toString());
        editor.putString("username", this.etUsername.getText().toString());
        editor.putString("password", this.secrecy.computeSHAHash(this.etPassword.getText().toString()));
        editor.apply();

        boolean isInserted = this.db.insertData(this.etName.getText().toString(), this.etLastname.getText().toString(),
                this.etUsername.getText().toString(),this.secrecy.computeSHAHash(this.etPassword.getText().toString()), 1,0);

        if(isInserted == true)
        {
            Toast.makeText(DriverReg.this, "Data inserted",Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(DriverReg.this, "Data not inserted",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this,"Registered successfully",Toast.LENGTH_LONG).show();


    }

    private EditText etName;
    private EditText etLastname;
    private EditText etUsername;
    private EditText etPassword;

    private Button btnRegister;

    private String name;
    private String lastname;
    private String username;
    private String password;
    private Secrecy secrecy;
    private SharedPreferences sharedPreferences;

    private DatabaseHelper db;



}
