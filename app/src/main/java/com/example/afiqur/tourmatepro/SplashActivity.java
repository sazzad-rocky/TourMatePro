package com.example.afiqur.tourmatepro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.afiqur.tourmatepro.TravelEvent.TravelActivity;

public class SplashActivity extends Activity {
    private ImageView spashIV;
    private static boolean splashLoaded = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        spashIV = (ImageView) findViewById(R.id.splash);

        if (!splashLoaded) {
            setContentView(R.layout.activity_splash);
            long secondsDelayed = (long) 1;

            new Handler().postDelayed(new Runnable() {

                public void run() {
                    startActivity(new Intent(SplashActivity.this, TravelActivity.class));
                    finish();
                }

            }, secondsDelayed * 1500);

            splashLoaded = true;
        }
        else {
            finish();
        }
    }

}
