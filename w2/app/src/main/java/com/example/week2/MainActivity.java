package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(1000);
        numberPicker.setMinValue(900);
        numberPicker.setValue(999);
    }
}