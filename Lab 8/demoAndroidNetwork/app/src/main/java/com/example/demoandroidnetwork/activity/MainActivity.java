package com.example.demoandroidnetwork.activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoandroidnetwork.R;
import com.example.demoandroidnetwork.adapter.WeatherAdapter;
import com.example.demoandroidnetwork.model.WeatherForecastModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtCityName;
    private TextView txtTemperature;
    private TextView txtCityTemperature;
    private EditText editTextTextPersonName;
    private ImageView imgSearch;
    private ImageView imgSeasonForecast;
    private ListView lvWeatherForecast;
    private WeatherAdapter weatherAdapter;
    private final List<WeatherForecastModel> weatherForecastModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtCityName = findViewById(R.id.txtCityName);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtCityTemperature = findViewById(R.id.txtCityTemperature);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        imgSearch = findViewById(R.id.imgSearch);
        imgSeasonForecast = findViewById(R.id.imgSeasonForecast);
        lvWeatherForecast = findViewById(R.id.lvWeatherForecast);
        weatherAdapter = new WeatherAdapter(MainActivity.this, R.layout.layout_weather_items, weatherForecastModelList);
        lvWeatherForecast.setAdapter(weatherAdapter);
        GetData("Hanoi");
        imgSearch.setOnClickListener(view -> {
            GetData(editTextTextPersonName.getText().toString());
            //weatherAdapter.notifyDataSetChanged();
        });
    }
    private void GetData(String cityName) {
        String url = "https://api.weatherapi.com/v1/forecast.json?key=8086fbd51e64464199265103251904&q="+cityName+"&days=1&aqi=no&alerts=no";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        Log.d("BBB", url.toString());
        txtCityName.setText(cityName);

        weatherForecastModelList.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String temperature = response.getJSONObject("current").getString("temp_c");
                    txtTemperature.setText(temperature + "°C");
                    String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                    String conditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");

                    txtCityTemperature.setText(condition);
                    Picasso.get().load("https:" + conditionIcon).into(imgSeasonForecast);
                    Log.d("AAA",""+conditionIcon);
                    JSONObject forecastObj = response.getJSONObject("forecast");
                    JSONObject forecast0 = forecastObj.getJSONArray("forecastday").getJSONObject(0);
                    JSONArray hourArray = forecast0.getJSONArray("hour");
                    for (int i = 0; i < hourArray.length(); i++) {
                        JSONObject hourObj = hourArray.getJSONObject(i);
                        String time = hourObj.getString("time");
                        String temper = hourObj.getString("temp_c");
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        String wind = hourObj.getString("wind_kph");
                        Log.d("AAA", " " + time + " " + temper + " " + img + " " + wind);
                        weatherForecastModelList.add(new WeatherForecastModel(time, temper, img, wind));
                    }
                    //weatherAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "" +temperature , Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("AAA", "Có lổi xảy ra" + url.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}