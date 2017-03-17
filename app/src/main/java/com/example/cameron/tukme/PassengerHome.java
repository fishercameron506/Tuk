package com.example.cameron.tukme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PassengerHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);

        this.btnAccept = (Button)findViewById(R.id.btnAccept);
        this.btnNext = (Button)findViewById(R.id.btnNext);
        this.btnCancel = (Button)findViewById(R.id.btnCancel);
        this.btnScan = (Button)findViewById(R.id.btnRate);
        this.txtName = (TextView)findViewById(R.id.txtDriverName);
        this.ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        this.db = new DatabaseHelper(this);


        this.driverList = new ArrayList<>();
        getDrivers();

        this.counter = 0;
        if(driverList.size() > counter)
        {
            this.txtName.setText(this.driverList.get(this.counter).getName() + " "+ this.driverList.get(this.counter).getSurname());
            ratingBar.setRating(driverList.get(counter).getRating());
        }


        this.txtScan = (TextView)findViewById(R.id.txtScan);

        this.sharedPreferences = getSharedPreferences("UserLoginInfo", Context.MODE_PRIVATE);




        this.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent mapIntent = new Intent(PassengerHome.this, MapWebView.class);

                mapIntent.putExtra("location", sharedPreferences.getString("Location1", ""));
                startActivity(mapIntent);
            }
        });

        //Update data in database TESTING
        this.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(driverList.size() > counter)
                {
                    txtName.setText(driverList.get(counter).getName() + " "+ driverList.get(counter).getSurname());
                    ratingBar.setRating(driverList.get(counter).getRating());
                }

            }
        });

        //displaying the data from database (TESTING)
        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getAllData();
                if(result.getCount() == 0)
                {
                    showMessage("Error", "No data");
                }

                StringBuffer stringBuffer = new StringBuffer();
                while(result.moveToNext())
                {
                    stringBuffer.append("ID : "+ result.getInt(0)+"\n" );
                    stringBuffer.append("Name : "+ result.getString(1)+"\n" );
                    stringBuffer.append("Surname : "+ result.getString(2)+"\n" );
                    stringBuffer.append("Username : "+ result.getString(3)+"\n" );
                    stringBuffer.append("Password : "+ result.getString(4)+"\n" );
                    stringBuffer.append("UserType : " + result.getString(5)+"\n");
                    stringBuffer.append("Rating : " + result.getString(6)+"\n\n");
                }
                showMessage("Database", stringBuffer.toString());
            }
        });

        this.btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAccept.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                txtScan.setVisibility(View.INVISIBLE);
            }
        });
    }


    private void getDrivers()
    {
        Cursor result = db.getDrivers();
        if(result.getCount() == 0)
        {
            showMessage("Drivers", "No drivers available");
        }


        while(result.moveToNext()) {
            Driver driver = new Driver();
            driver.setName(result.getString(1));
            driver.setSurname(result.getString(2));
            driver.setRating(result.getInt(6));
            this.driverList.add(driver);
        }
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    private Button btnAccept;
    private Button btnNext;
    private TextView txtName;
    private Button btnCancel;
    private TextView txtScan;
    private Button btnScan;
    private SharedPreferences sharedPreferences;
    private RatingBar ratingBar;
    private String name;
    private String lastname;
    private float rating;
    private DatabaseHelper db;
    private ArrayList<Driver> driverList;
    private int counter;
    
}
