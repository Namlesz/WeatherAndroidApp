package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class WeatherDetails extends AppCompatActivity {

    TextView town, feel_temp, wind, temperature, pressure, humidity, cloudiness;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        town = findViewById(R.id.town);
        feel_temp = findViewById(R.id.feel_temp);
        wind = findViewById(R.id.wind);
        temperature = findViewById(R.id.temperature);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        cloudiness = findViewById(R.id.cloudiness);

        Intent intent = getIntent();
        String response = intent.getStringExtra("Weather");
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");

            double temp = jsonObjectMain.getDouble("temp") - 273.15;

            double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;

            float pressureVal = jsonObjectMain.getInt("pressure");

            int humidityVal = jsonObjectMain.getInt("humidity");

            String wind_sp = jsonResponse.getJSONObject("wind").getString("speed");

            String clouds = jsonResponse.getJSONObject("clouds").getString("all");

            JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
            String countryName = jsonObjectSys.getString("country");
            String cityName = jsonResponse.getString("name");

            String _town = cityName + " (" + countryName + ")";
            town.setText(_town);

            String _feel_temp = "Odczuwalna temperatura:\n" +
                    df.format(feelsLike) +" °C";
            feel_temp.setText(_feel_temp);

            String _wind = "Wiatr:\n" + wind_sp + "m/s";
            wind.setText(_wind);

            String _temperature = "Temperatura:\n" + df.format(temp) + " °C";
            temperature.setText(_temperature);

            String _pressure = "Ciśnienie:\n" + pressureVal + " hPa";
            pressure.setText(_pressure);

            String _humidity = "Wilgotność:\n" + humidityVal + "%";
            humidity.setText(_humidity);

            String _cloudiness = "Zachmurzenie:\n" + clouds + "%";
            cloudiness.setText(_cloudiness);

        } catch (
                JSONException e) {
            e.printStackTrace();
        }

    }
}