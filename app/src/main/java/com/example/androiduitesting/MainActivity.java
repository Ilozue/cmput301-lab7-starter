package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView cityListView;
    private LinearLayout nameField;     // @id/field_nameEntry
    private EditText newName;           // @id/editText_name
    private Button addCityButton;       // @id/button_add
    private Button confirmButton;       // @id/button_confirm
    private Button deleteButton;        // @id/button_clear

    private ArrayList<String> cityData;
    private ArrayAdapter<String> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views (IDs taken from your activity_main.xml)
        cityListView   = findViewById(R.id.city_list);
        nameField      = findViewById(R.id.field_nameEntry);
        newName        = findViewById(R.id.editText_name);
        addCityButton  = findViewById(R.id.button_add);
        confirmButton  = findViewById(R.id.button_confirm);
        deleteButton   = findViewById(R.id.button_clear);

        // Data + adapter
        cityData = new ArrayList<>();
        if (!cityData.contains("Edmonton")) cityData.add("Edmonton"); // match your screenshot
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityData);
        cityListView.setAdapter(cityAdapter);

        // Initially hide the name entry row (if you designed it that way)
        if (nameField != null) nameField.setVisibility(View.GONE);

        // Add: reveal the entry row
        addCityButton.setOnClickListener(v -> {
            if (nameField != null) nameField.setVisibility(View.VISIBLE);
            newName.requestFocus();
        });

        // Confirm: add typed city, clear input, hide entry row
        confirmButton.setOnClickListener(v -> {
            String cityName = newName.getText().toString().trim();
            if (!cityName.isEmpty()) {
                cityAdapter.add(cityName);
                cityAdapter.notifyDataSetChanged();
                newName.getText().clear();
                if (nameField != null) nameField.setVisibility(View.GONE);
            }
        });

        // Clear all
        deleteButton.setOnClickListener(v -> {
            cityAdapter.clear();
            cityAdapter.notifyDataSetChanged();
        });

        // >>> Lab 7 requirement: click a city to open ShowActivity and display its name
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            String cityName = cityAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            intent.putExtra(ShowActivity.EXTRA_CITY_NAME, cityName);
            startActivity(intent);
        });
    }
}
