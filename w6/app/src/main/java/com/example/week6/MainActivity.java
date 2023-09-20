package com.example.week6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button popupBtn;
    Button colorBtn;
    boolean isContentPopupSelected = false;
    boolean isColorSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popupBtn = (Button) findViewById(R.id.popup_btn);
        colorBtn = (Button) findViewById(R.id.color_btn);


        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu();
            }
        });
        registerForContextMenu(colorBtn);
    }

    private void showPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this, popupBtn);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.content1) {
                    popupBtn.setText("Content 1");
                    isContentPopupSelected = true;
                } else if (itemId == R.id.content2) {
                    popupBtn.setText("Content 2");
                    isContentPopupSelected = true;
                }
                return false;
            }
        });
        popupMenu.show();
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu popupMenu) {
                if (!isContentPopupSelected) {
                    popupBtn.setText("Choose content");
                }
                isContentPopupSelected = false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        isColorSelected = false;
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.color1) {
            colorBtn.setTextColor(Color.RED);
            isColorSelected = true;
        } else if (itemId == R.id.color2) {
            colorBtn.setTextColor(Color.GREEN);
            isColorSelected = true;
        } else if (itemId == R.id.color3) {
            colorBtn.setTextColor(Color.BLUE);
            isColorSelected = true;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
        if (!isColorSelected) {
            colorBtn.setTextColor(Color.WHITE);
        }
        // Reset the color selection flag
        isColorSelected = false;
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
            Toast.makeText(MainActivity.this, "Item 1 selected", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.item2) {
            Toast.makeText(MainActivity.this, "Item 2 selected", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.item3_1) {
            Toast.makeText(MainActivity.this, "Item 3.1 selected", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.item3_2) {
            Toast.makeText(MainActivity.this, "Item 3.2 selected", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}