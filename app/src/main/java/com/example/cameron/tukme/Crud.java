package com.example.cameron.tukme;

import android.app.AlertDialog;
import android.database.Cursor;
import android.widget.Toast;

/**
 * Created by Cameron on 3/3/2017.
 */
public class Crud {

    private void update()
    {
        boolean isUpdate = db.updatePassword(1, "Cameron", "Fisher", "Fishercameron505@gmail.com", "Password");

        if(isUpdate)
        {

        }else
        {

        }
    }

    private void display()
    {
        Cursor result = db.getData("b","b");
        if(result.getCount() == 0)
        {

        }

        StringBuffer stringBuffer = new StringBuffer();
        while(result.moveToNext())
        {
            stringBuffer.append("ID : "+ result.getInt(0)+"\n" );
            stringBuffer.append("Name : "+ result.getString(1)+"\n" );
            stringBuffer.append("Surname : "+ result.getString(2)+"\n" );
            stringBuffer.append("Username : "+ result.getString(3)+"\n" );
            stringBuffer.append("Password : "+ result.getString(4)+"\n" );
            stringBuffer.append("UserType : " + result.getString(5)+"\n\n");
        }


    }

    private DatabaseHelper db;
}
