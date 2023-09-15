package com.example.registrationsetup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
    Button next;

    private androidx.appcompat.widget.Toolbar customToolbar;
    private TextView pageTitle;
    private TextView pageCounter;
    private TextView step1TextView;
    private TextView step2TextView;
    private TextView step3TextView;
    private TextView step4TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the status bar.
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //++       WindowManager.LayoutParams.FLAG_FULLSCREEN);

// Hide the action bar.
//        getSupportActionBar().hide();

        // Get the window object
        Window window = getWindow();

// Set the status bar color
        window.setStatusBarColor(getResources().getColor(R.color.darkPink));


        setContentView(R.layout.activity_main);

        customToolbar = findViewById(R.id.toolbar);
        pageTitle = findViewById(R.id.tvPageTitle);
        pageCounter = findViewById(R.id.tvPageNum);
        // progress bar
        step1TextView = customToolbar.findViewById(R.id.step_1);
        step2TextView = customToolbar.findViewById(R.id.step_2);
        step3TextView = customToolbar.findViewById(R.id.step_3);
        step4TextView = customToolbar.findViewById(R.id.step_4);

        setSupportActionBar(customToolbar);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BasicInfoForm()).commit();


        }


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new BasicInfoForm());
        fragmentTransaction.commit();


    }

    // Method to update the text of the first TextView in the custom toolbar
    public void updatePageTitle(String newText) {
        pageTitle.setText(newText);
    }

    // Method to update the text of the second TextView in the custom toolbar
    public void updatePageCounter(String newText) {
        pageCounter.setText(newText);
    }

    public void updateStepProgressBar(int currentStep) {
        switch (currentStep) {
            case 1:
                step1TextView.setTextColor(getResources().getColor(android.R.color.white));
                step1TextView.setBackgroundResource(R.drawable.current_progressbar);
                step2TextView.setTextColor(getResources().getColor(android.R.color.white));
                step2TextView.setBackgroundResource(R.drawable.not_visit_progressbarxml);
                step3TextView.setTextColor(getResources().getColor(android.R.color.white));
                step3TextView.setBackgroundResource(R.drawable.not_visit_progressbarxml);
                step4TextView.setTextColor(getResources().getColor(android.R.color.white));
                step4TextView.setBackgroundResource(R.drawable.not_visit_progressbarxml);
                break;
            case 2:
                step1TextView.setTextColor(getResources().getColor(android.R.color.white));
                step1TextView.setBackgroundResource(R.drawable.visit_progressbar);
                step2TextView.setTextColor(getResources().getColor(android.R.color.white));
                step2TextView.setBackgroundResource(R.drawable.current_progressbar);
                step3TextView.setTextColor(getResources().getColor(android.R.color.white));
                step3TextView.setBackgroundResource(R.drawable.not_visit_progressbarxml);
                step4TextView.setTextColor(getResources().getColor(android.R.color.white));
                step4TextView.setBackgroundResource(R.drawable.not_visit_progressbarxml);
                break;
            case 3:
                step1TextView.setTextColor(getResources().getColor(android.R.color.white));
                step1TextView.setBackgroundResource(R.drawable.visit_progressbar);
                step2TextView.setTextColor(getResources().getColor(android.R.color.white));
                step2TextView.setBackgroundResource(R.drawable.visit_progressbar);
                step3TextView.setTextColor(getResources().getColor(android.R.color.white));
                step3TextView.setBackgroundResource(R.drawable.current_progressbar);
                step4TextView.setTextColor(getResources().getColor(android.R.color.white));
                step4TextView.setBackgroundResource(R.drawable.not_visit_progressbarxml);
                break;

            case 4:
                step1TextView.setTextColor(getResources().getColor(android.R.color.white));
                step1TextView.setBackgroundResource(R.drawable.visit_progressbar);
                step2TextView.setTextColor(getResources().getColor(android.R.color.white));
                step2TextView.setBackgroundResource(R.drawable.visit_progressbar);
                step3TextView.setTextColor(getResources().getColor(android.R.color.white));
                step3TextView.setBackgroundResource(R.drawable.visit_progressbar);
                step4TextView.setTextColor(getResources().getColor(android.R.color.white));
                step4TextView.setBackgroundResource(R.drawable.current_progressbar);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}