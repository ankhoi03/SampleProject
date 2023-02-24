package com.example.sampleproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sampleproject.R;

public class SplashActivity extends AppCompatActivity {

    ImageView splash;
    TextView tvKhoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash=findViewById(R.id.splash_k_gif);
        tvKhoi=findViewById(R.id.tvDevKhoi);
        Glide.with(this).load(R.mipmap.k_gif).into(splash);
        tvKhoi.animate().translationX(350).withLayer();
        tvKhoi.animate().setStartDelay(1500);
        tvKhoi.animate().setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }
        },4500);
    }
}