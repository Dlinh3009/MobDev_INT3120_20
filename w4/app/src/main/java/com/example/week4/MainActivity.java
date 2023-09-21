package com.example.week4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button dntButton;
    private NumberPicker numberPicker;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.item1);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1) {
            Intent linear1 = new Intent(MainActivity.this, LinearActivity1.class);
            startActivity(linear1);
//            Toast.makeText(MainActivity.this, "Item 1 selected", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.item2) {
//            Toast.makeText(MainActivity.this, "Item 2 selected", Toast.LENGTH_LONG).show();
            Intent linear2 = new Intent(MainActivity.this, LinearActivity2.class);
            startActivity(linear2);
        }
        return super.onOptionsItemSelected(item);
    }
}