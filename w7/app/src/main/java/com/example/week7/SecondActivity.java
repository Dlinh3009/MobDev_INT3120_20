package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Button btnSecond;
    Button toImplicit;
    TextView txtSecond;
    String hello;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnSecond = (Button) findViewById(R.id.buttonSecond);
        txtSecond = (TextView) findViewById(R.id.textViewSecond);
        toImplicit = (Button) findViewById(R.id.toImplicit);
        Intent intent = getIntent();
        this.hello = intent.getStringExtra("message");
        this.name = intent.getStringExtra("name");
        txtSecond.setText(hello + " Android");
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        toImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thirdAct = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(thirdAct);
            }
        });
    }

    public void goBack(){
        this.onBackPressed();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        String reply = "Hi, " + this.name;
        data.putExtra("reply", reply);
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}