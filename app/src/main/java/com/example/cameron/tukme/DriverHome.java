package com.example.cameron.tukme;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriverHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        this.btnAccept = (Button)findViewById(R.id.btnAccept);
        this.btnNext = (Button)findViewById(R.id.btnNext);
        this.btnCancel = (Button)findViewById(R.id.btnCancel);
        this.btnScan = (Button)findViewById(R.id.btnRate);

        this.txtScan = (TextView)findViewById(R.id.txtScan);

        this.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.btnRate = (Button)findViewById(R.id.btnRate);
        this.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rateIntent = new Intent(DriverHome.this, RatingsReview.class);
                startActivity(rateIntent);
            }
        });

        showNotification();
    }

    public void showNotification()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.passenger);
        builder.setContentTitle("My Notifications");

        builder.setContentText("Pick up");
        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder.setSound(notificationSound);


        Intent notificationIntent = new Intent(this, DriverHome.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DriverHome.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
    private Button btnAccept;
    private Button btnNext;
    private Button btnCancel;
    private TextView txtScan;
    private Button btnScan;
    private Button btnRate;
}
