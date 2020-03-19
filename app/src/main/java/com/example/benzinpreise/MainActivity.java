package com.example.benzinpreise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
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

public class MainActivity extends AppCompatActivity implements LocationListener{

    RecyclerView recyclerView;
    List<Station> tanktsellenList;
    Adapter adapter;
    SeekBar radiusSeekBar;
    TextView radiusTextView;
    Button suchen;
    double lat;
    double lng;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.stationsList);
        tanktsellenList = new ArrayList<>();


        //get Current Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);



        //Seekbar
        radiusSeekBar = findViewById(R.id.seekBar);
        radiusTextView = findViewById(R.id.radiusZahl);
        suchen = findViewById(R.id.suchenButton);
        radiusSeekBar.setProgress(7);
        radiusTextView.setText(String.valueOf(radiusSeekBar.getProgress() + " km"));

        radiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                radiusTextView.setText(String.valueOf(progress + " km"));
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
        getTankstellen(radius, String.valueOf(lat), String.valueOf(lng));


        //If Button is Clicked
        suchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String radius = String.valueOf(radiusSeekBar.getProgress());
                adapter.clearList();

                getTankstellen(radius, String.valueOf(lat), String.valueOf(lng));

            }
        });

    }

    private void getTankstellen(String radius, String lat, String lng){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TankstellenApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TankstellenApi tankstellenApi = retrofit.create(TankstellenApi.class);

        Call<Tankstellen> call = tankstellenApi.getTankstellen(lat,lng,radius,"all","dist",TankstellenApi.API_KEY);
        call.enqueue(new Callback<Tankstellen>() {
            @Override
            public void onResponse(Call<Tankstellen> call, Response<Tankstellen> response) {



                Tankstellen antwort = response.body();
                List<Tankstellen.Stations> petrolStation = antwort.stations;

                for(Tankstellen.Stations stationen : petrolStation) {

                    Station tankstelle = new Station();
                    tankstelle.setName(stationen.getName());
                    tankstelle.setBrand(stationen.getBrand());
                    tankstelle.setStreet(stationen.getStreet());
                    tankstelle.setPlace(stationen.getPlace());
                    tankstelle.setLat(stationen.getLat());
                    tankstelle.setLng(stationen.getLng());
                    tankstelle.setDist(stationen.getDist());
                    tankstelle.setDiesel(stationen.getDiesel());
                    tankstelle.setE5(stationen.getE5());
                    tankstelle.setE10(stationen.getE10());
                    tankstelle.setOpen(stationen.isOpen());
                    tankstelle.setHouseNumber(stationen.getHouseNumber());
                    tankstelle.setPostCode(stationen.getPostCode());
                    tanktsellenList.add(tankstelle);

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), tanktsellenList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Tankstellen> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTanktsellenPreis (){

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
}
