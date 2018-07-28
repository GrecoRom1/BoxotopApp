package com.perrusset.romain.boxotop.UIL.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, BoxOfficeActivity.class);
        startActivity(intent);
        finish();
    }
}
