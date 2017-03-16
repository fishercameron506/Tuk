package com.example.cameron.tukme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Registration extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.btnDriverReg = (Button)findViewById(R.id.btnDriverReg);
        this.btnPassengerReg = (Button)findViewById(R.id.btnPassengerReg);

        this.btnDriverReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driverRegIntent = new Intent(Registration.this, DriverReg.class);
                startActivity(driverRegIntent);
            }
        });

        this.btnPassengerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passengerRegIntent = new Intent(Registration.this, PassengerReg.class);
                startActivity(passengerRegIntent);
            }
        });
    }



    private Button btnDriverReg;
    private Button btnPassengerReg;
}
