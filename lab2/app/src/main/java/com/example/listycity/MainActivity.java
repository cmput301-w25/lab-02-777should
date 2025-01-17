package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editCityName;
    private Button buttonAddCity, buttonDeleteCity;
    private ListView cityList;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize each term
        editCityName = findViewById(R.id.editCityName);
        buttonAddCity = findViewById(R.id.buttonAddCity);
        buttonDeleteCity = findViewById(R.id.buttonDeleteCity);
        cityList = findViewById(R.id.city_list);

        dataList = new ArrayList<>();
        dataList.add("Edmonton");
        dataList.add("Montréal");
        dataList.add("Vancouver");

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // add button
        buttonAddCity.setOnClickListener(v -> {
            String cityName = editCityName.getText().toString();
            if (!cityName.isEmpty() && !dataList.contains(cityName)) {
                dataList.add(cityName);
                // update
                cityAdapter.notifyDataSetChanged();
                editCityName.setText("");
            } else {
                Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show();
            }
        });

        // delete button
        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            dataList.remove(position);
            cityAdapter.notifyDataSetChanged();
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
