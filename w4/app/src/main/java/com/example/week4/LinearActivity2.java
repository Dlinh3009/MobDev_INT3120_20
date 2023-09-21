package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toolbar;

public class LinearActivity2 extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button dntButton;
    private NumberPicker numberPicker;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout_2);

        toolbar = (Toolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);
    }

    private void setSupportActionBar(Toolbar toolbar) {}
}