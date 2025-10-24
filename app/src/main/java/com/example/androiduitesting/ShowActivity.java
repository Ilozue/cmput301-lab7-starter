package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    public static final String EXTRA_CITY_NAME = "extra_city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content); // uses res/layout/content.xml

        TextView tv = findViewById(R.id.content_view);
        String city = "";
        Intent i = getIntent();
        if (i != null) city = i.getStringExtra(EXTRA_CITY_NAME);
        tv.setText(city == null ? "" : city);

        Button back = findViewById(R.id.button_back);
        if (back != null) back.setOnClickListener(v -> finish());
    }
}
