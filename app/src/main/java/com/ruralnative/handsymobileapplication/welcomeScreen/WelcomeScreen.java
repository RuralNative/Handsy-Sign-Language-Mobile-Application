package com.ruralnative.handsymobileapplication.welcomeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruralnative.handsymobileapplication.R;

public class WelcomeScreen extends AppCompatActivity {

    //WelcomeScreen Activity Attributes
    ConstraintLayout mainLayout = findViewById(R.id.main_Layout);
    GridLayout mainView = findViewById(R.id.main_View);
    TextView welcomeText = findViewById(R.id.welcome_Text);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ruralnative.handsymobileapplication.R.layout.activity_welcome_screen);
        setComponentProperties();
    }

    private void setComponentProperties() {
        try {
            mainView.setBackgroundColor(getResources().getColor(R.color.emerald_green));
        } catch (Resources.NotFoundException nfe) {
            nfe.printStackTrace();
        }

    }



}