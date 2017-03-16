package com.example.cameron.tukme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView imgTukTuk = (ImageView) findViewById(R.id.imgTukTuk);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.move);


        imgTukTuk.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                startActivity(mainIntent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}
