package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    EditText etCity, etCountry;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "3fb82e9338fc70859a8ad3958c5c77de";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
    }

    public void getWeatherDetails(View view) {
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();

        // Check if town is not empty
        if (city.equals("")) {
            Toast.makeText(MainActivity.this, "This is my Toast message!",
                    Toast.LENGTH_LONG).show();
        } else {

            // Check if zip code is empty
            if (!country.equals("")) {
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
            } else {
                tempUrl = url + "?q=" + city + "&appid=" + appid;
            }

            // Build volley request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, response -> {
                // Change view and send data to second screen
                Intent intent2 = new Intent(MainActivity.this, WeatherDetails.class);
                intent2.putExtra("Weather", response);
                startActivity(intent2);
            }, error -> Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show());

            // Run request
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}