package com.example.week5;
//
//import android.app.Activity;
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.TimePicker;
//import android.widget.TextView;
//import java.text.DateFormat;
//import java.util.Calendar;
//public class MainActivity extends Activity {
//    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
//    TextView lblDateAndTime;
//    Calendar myCalendar = Calendar.getInstance();
//
//    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
//    {
//        public void onDateSet(DatePicker view,
//                              int year, int monthOfYear, int dayOfMonth) {
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, monthOfYear);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateLabel();
//        }
//    };
//
//    private void updateLabel() {
//        lblDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
//    }
//
//    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener()
//    {
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            myCalendar.set(Calendar.MINUTE, minute);
//            updateLabel();
//        }
//    };
//    @Override
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        setContentView(R.layout.date_time);
//        lblDateAndTime = (TextView) findViewById(R.id.lblDateAndTime);
//        Button btnDate = (Button) findViewById(R.id.btnDate);
//        btnDate.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                new DatePickerDialog(MainActivity.this, d,
//                        myCalendar.get(Calendar.YEAR),
//                        myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//        Button btnTime = (Button) findViewById(R.id.btnTime);
//        btnTime.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                new TimePickerDialog(MainActivity.this, t,
//                        myCalendar.get(Calendar.HOUR_OF_DAY),
//                        myCalendar.get(Calendar.MINUTE), true).show();
//            }
//        });
//        updateLabel();
//    }// onCreate
//} //class

//====================================//

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.tab);
        TabHost tabs=(TabHost)findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec;
        spec =tabs.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("1-Clock");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2-Login");
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
        Button btnGo = (Button)findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText txtPerson =(EditText)findViewById(R.id.txtPerson);
                String theUser = txtPerson.getText().toString();
                txtPerson.setText("Hola " + theUser);
            }

        });
    }
}



