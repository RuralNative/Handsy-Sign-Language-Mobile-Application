package com.ruralnative.handsy_sign_language_tutorial.mainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ruralnative.handsy_sign_language_tutorial.R;
import com.ruralnative.handsy_sign_language_tutorial.database.DatabaseHelper;

import java.io.IOException;

public class MainScreen extends AppCompatActivity {
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        createDatabase();
    }

    private void createDatabase() {
        try {
            databaseHelper.prepopulateDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}