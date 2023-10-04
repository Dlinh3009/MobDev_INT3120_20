package com.example.week9;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_CODE = 1001;
    EditText mEditText;
    Button saveBtn;
    Button loadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        mEditText = findViewById(R.id.textInputEditText);
        isExternalStorageWritable();
        isExternalStorageReadable();
        saveBtn = (Button) findViewById(R.id.saveButton);
        loadBtn = (Button) findViewById(R.id.loadButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile(view);
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile(view);
            }
        });
        if (checkPermission()) {
            createAppDirectory();
        }
    }

    private boolean isExternalStorageWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("State", "Writable!!");
            return true;
        } else {
            Log.i("State", "Not Writable!!");
            return false;
        }
    }
    private boolean isExternalStorageReadable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
            Log.i("State", "Readable!!");
            return true;
        } else {
            return false;
        }
    }

    public void writeFile(View v){
        String fileName = "sample.txt";
        String content = mEditText.getText().toString();

        try {
            String directoryName = Environment.DIRECTORY_DOCUMENTS; // Thư mục "Documents"
            File directory = Environment.getExternalStoragePublicDirectory(directoryName);

            File file = new File(directory, fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Cannot save", Toast.LENGTH_SHORT).show();
        }
    }

    public void readFile(View view){
        String fileName = "sample.txt";
        StringBuilder content = new StringBuilder();

        try {
            String directoryName = Environment.DIRECTORY_DOCUMENTS; // Thư mục "Documents"
            File directory = Environment.getExternalStoragePublicDirectory(directoryName);
            File file = new File(directory, fileName);

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                mEditText.setText(content.toString().trim());
            } else {
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Cannot read file", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission() {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_CODE
            );
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createAppDirectory();
            } else {
                Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void createAppDirectory() {
        String directoryName = Environment.DIRECTORY_DOCUMENTS;
        File directory = Environment.getExternalStoragePublicDirectory(directoryName);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                Toast.makeText(this, "Directory created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cannot create Directory", Toast.LENGTH_SHORT).show();
            }
        }
    }
}