package com.ruralnative.handsy_sign_language_tutorial.welcomeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruralnative.handsy_sign_language_tutorial.R;

public class WelcomeScreenActivity extends AppCompatActivity {

    //Activity Attributes
    ConstraintLayout screenConstraints = findViewById(R.id.constraintLayout);
    LinearLayout mainLayout = findViewById(R.id.mainLayout);
    LinearLayout textLayout = findViewById(R.id.textLayout);
    TextView welcomeText = findViewById(R.id.introductoryText);
    TextView complementaryText = findViewById(R.id.complementaryText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        setContentProperties();
    }

    private void setContentProperties() {
        try {
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
            welcomeText.setText(R.string.simplified_app_name);
            complementaryText.setText(R.string.complementary_app_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveToActivity() {
        Thread screenLoadingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                startActivity(intent);
            }
        });
        screenLoadingThread.start();
    }
}