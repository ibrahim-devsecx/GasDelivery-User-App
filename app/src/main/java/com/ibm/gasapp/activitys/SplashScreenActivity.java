package com.ibm.gasapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ibm.gasapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    int timer;
    String topc;
    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topc = "users";

        subscribeToTopic(topc);

        timer = 3 * 1000;

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(timer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
                finish();

            }
        }).start();
    }

    private void subscribeToTopic(String topc) {
        FirebaseMessaging.getInstance().subscribeToTopic(topc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.d(TAG, "onComplete: subscribeToTopic" + topc);
                    }
                });
    }
}