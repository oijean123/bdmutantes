package com.example.rodrigo.bdmutantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity implements Runnable {
    private final int delay = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Handler h = new Handler();
        h.postDelayed(this,delay);
    }

    @Override
    public void run(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
