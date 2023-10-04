package com.example.week11;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.week11.MyAidlInterface;

public class AidlActivity extends AppCompatActivity {

    private EditText etNumber1;
    private EditText etNumber2;
    private Button btnAdd;
    private TextView tvResult;
    private boolean isServiceBound = false;
    private MyAidlInterface myInterface;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myInterface = MyAidlInterface.Stub.asInterface(iBinder);
            isServiceBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myInterface = null;
            isServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        btnAdd = findViewById(R.id.btnAdd);
        tvResult = findViewById(R.id.tvResult);

        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1Str = etNumber1.getText().toString();
                String num2Str = etNumber2.getText().toString();
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);
                try {
                    int sum = myInterface.add(num1, num2);
                    tvResult.setText("Result: " + sum);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }
}


