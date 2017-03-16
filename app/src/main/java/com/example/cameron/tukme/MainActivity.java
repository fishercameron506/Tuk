package com.example.cameron.tukme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myDB = new DatabaseHelper(this);



        Intent LoginIntent = new Intent(this, Login.class);
        startActivity(LoginIntent);
    }



    private DatabaseHelper myDB;

}
