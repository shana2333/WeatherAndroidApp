package com.example.shana.gsonweather;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    /* Layout */
    private TextView textView;
    private Button buttonSummary;
    private Button buttonTimezone;
    private Button buttonTemperature;
    private Button buttonWindSpeed;
    private Button buttonAuthor;
    private Button buttonTodo;
    private TextView positionTextView;
    /* Needed Json */
    private String weatherSummary;
    private String timezone;
    private Float temperature;
    private Float windSpeed;
    /* Value in the app */
    private LocationManager locationManager;
    private String provider;
    private String latitude;
    private String longitude;
    private Double Dlatitude;
    private Double Dlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Get current location by using LocationManager */
        positionTextView = (TextView) findViewById(R.id.position_text_view);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "No location provider to use",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        // add check permission for location
        if (location != null) {
            showLocation(location);
        }
        /* Change location from double to string and also change format into WebAPI required */
        Dlatitude = location.getLatitude();
        Dlongitude = location.getLongitude();
        latitude = String.format("%.6f", Dlatitude);
        longitude = String.format("%.6f", Dlongitude);

        /* Button Activity, need one method for all same Json Object */
        buttonSummary = findViewById(R.id.getWeatherSummary);
        buttonSummary.setOnClickListener(new getWeatherButtonListener());
        buttonTimezone = findViewById(R.id.getTimezone);
        buttonTimezone.setOnClickListener(new getTimezoneButtonListener());
        buttonTemperature = findViewById(R.id.getTemperature);
        buttonTemperature.setOnClickListener(new getTemperatureButtonListener());
        buttonWindSpeed = findViewById(R.id.getWindSpeed);
        buttonWindSpeed.setOnClickListener(new getWindSpeedButtonListener());
        buttonAuthor = findViewById(R.id.getme);
        buttonAuthor.setOnClickListener(new getmeButtonListener());
        buttonTodo = findViewById(R.id.todo);
        buttonTodo.setOnClickListener(new getTodoButtonListener());

        /* Using retrofit to handle webAPI */
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DarkSkyResponse> call = service.getForecastForLocation(latitude, longitude);
        call.enqueue(new Callback<DarkSkyResponse>() {

            @Override
            public void onResponse(Call<DarkSkyResponse> call, retrofit2.Response<DarkSkyResponse> response) {
                if( response.body() != null ) {
                    weatherSummary = response.body().currently.summary;
                    timezone = response.body().timezone;
                    temperature = response.body().currently.temperature;
                    windSpeed = response.body().currently.windSpeed;
                    //Log.d("Jiatong", "the weather is " + response.body().currently.summary);
                    //Log.d("Jiatong", "the weather view is " + weatherSummary);
                } else {
                    Log.e("error", response.toString());
                    Toast.makeText(MainActivity.this, "No body in response!! WebAPI failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DarkSkyResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class getWeatherButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            textView = findViewById(R.id.result);
            textView.setText("The Weather is ：" + weatherSummary + " now");
        }
    }

    private class getTimezoneButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            textView = findViewById(R.id.result);
            textView.setText("You are in ：" + timezone);
            //Toast.makeText(MainActivity.this,"Your are in the ：" + timezone,Toast.LENGTH_LONG).show();
        }
    }

    private class getTemperatureButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            textView = findViewById(R.id.result);
            textView.setText("The temperature now is ：" + temperature);
            //Toast.makeText(MainActivity.this,"The temperature now is ：" + temperature,Toast.LENGTH_LONG).show();
        }
    }

    private class getWindSpeedButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            textView = findViewById(R.id.result);
            textView.setText("The WindSpeed now is ：" + windSpeed);
            //Toast.makeText(MainActivity.this,"The WindSpeed now is ：" + windSpeed,Toast.LENGTH_LONG).show();
        }
    }

    private void showLocation(final Location location) {
        String currentPosition = "Your latitude is " + location.getLatitude() + "\n" + "Your longitude is " + location.getLongitude();
        positionTextView.setText(currentPosition);
    }

    private class getmeButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            textView = findViewById(R.id.result);
            textView.setText("The Author is : Jiatong Li     Please contact Jiatong@kth.se" );
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    private class getTodoButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            textView = findViewById(R.id.result);
            textView.setText("Add method for all Json. Add permission check. Add other Activity" );
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

}
