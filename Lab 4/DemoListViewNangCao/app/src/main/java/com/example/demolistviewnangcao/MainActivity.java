package com.example.demolistviewnangcao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvCity;
    ArrayList<City> cityArrayList = new ArrayList<>();
    CityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lvCity = findViewById(R.id.lvCity);
        cityArrayList.add(new City("New York",R.drawable.newyork,"http://1.com"));
        cityArrayList.add(new City("Paris",R.drawable.paris,"http://2.com"));
        cityArrayList.add(new City("Rome",R.drawable.rome,"http://3.com"));
        adapter = new
                CityAdapter(this,R.layout.dong_thanh_pho,cityArrayList);

        lvCity.setAdapter(adapter);
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }
}