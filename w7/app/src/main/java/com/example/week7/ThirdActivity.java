package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {
    Button phoneBtn;
    Button googleBtn;
    Button sendBtn;
    Button smsBtn;
    Button pictureBtn;
    Button musicBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        phoneBtn = (Button) findViewById(R.id.phone);
        googleBtn = (Button) findViewById(R.id.google);
        sendBtn = (Button) findViewById(R.id.send);
        smsBtn = (Button) findViewById(R.id.sms);
        pictureBtn = (Button) findViewById(R.id.picture);
        musicBtn = (Button) findViewById(R.id.music);
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123-1234"));

        Intent googleIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        googleIntent.putExtra(SearchManager.QUERY, "UET VNU");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "NGUYEN DUY LINH");
        sendIntent.addCategory(Intent.CATEGORY_DEFAULT);
        sendIntent.setComponent(new ComponentName("com.example.week7","com.example.week7.ThirdActivity"));

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:1231234"));
        smsIntent.putExtra("sms body", "Nguyen Duy Linh");

        Intent pictureIntent = new Intent();
        pictureIntent.setType("image/pictures/*");
        pictureIntent.setAction(Intent.ACTION_GET_CONTENT);

        String myData = "content://contacts/people/";
        Intent contactIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));

        Intent musicIntent = new Intent("android.intent.action.MUSIC_PLAYER");
        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(phoneIntent);
            }
        });
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(googleIntent);
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(sendIntent);
            }
        });
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(smsIntent);
            }
        });
        pictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(pictureIntent);
                startActivity(contactIntent);
            }
        });
        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(musicIntent);
            }
        });
    }
}