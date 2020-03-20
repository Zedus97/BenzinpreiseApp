package com.example.benzinpreise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benzinpreise.apiRequest.Tankstellen;
import com.example.benzinpreise.apiRequest.TankstellenApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LocationListener {


    SeekBar radiusSeekBar;
    TextView radiusTextView;
    Button suchen;
    Button preis;
    double lat;
    double lng;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get Current Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        onLocationChanged(location);


        //Seekbar
        radiusSeekBar = findViewById(R.id.seekBar);
        radiusTextView = findViewById(R.id.radiusZahl);
        suchen = findViewById(R.id.suchenButton);
        preis = findViewById(R.id.preisSuche);
        radiusSeekBar.setProgress(7);
        radiusTextView.setText(radiusSeekBar.getProgress() + " km");

        radiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                radiusTextView.setText(progress + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Get Radius from seekbar and set it to radius as a String
        String radius = String.valueOf(radiusSeekBar.getProgress());
        //Get Tankstellen with the radius from the Seekbar


        //If Button is Clicked
        suchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUmkreisActivity();
                /*
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                onLocationChanged(location1);
                */
            }
        });

        preis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreisActivity();
                /*
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                onLocationChanged(location1);
                */
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        lng = location.getLongitude();
        lat = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void openUmkreisActivity() {
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(radiusSeekBar.getProgress()));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(lat));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(lng));
        Intent intent = new Intent(this, Umkreis.class);
        intent.putExtra("lat", String.valueOf(lat));
        intent.putExtra("lng", String.valueOf(lng));
        intent.putExtra("umkreis", String.valueOf(radiusSeekBar.getProgress()));
        startActivity(intent);

    }

    private void openPreisActivity() {
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(radiusSeekBar.getProgress()));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(lat));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(lng));
        Intent intent = new Intent(this, Preis.class);
        intent.putExtra("lat", String.valueOf(lat));
        intent.putExtra("lng", String.valueOf(lng));
        intent.putExtra("umkreis", String.valueOf(radiusSeekBar.getProgress()));
        startActivity(intent);

    }
}
