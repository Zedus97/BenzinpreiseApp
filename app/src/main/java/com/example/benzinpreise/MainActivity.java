package com.example.benzinpreise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benzinpreise.Location.LocationFinder;
import com.example.benzinpreise.apiRequest.Tankstellen;
import com.example.benzinpreise.apiRequest.TankstellenApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Station> tanktsellenList;
    Adapter adapter;
    SeekBar radiusSeekBar;
    TextView radiusTextView;
    Button suchen;
    double lat;
    double lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.stationsList);
        tanktsellenList = new ArrayList<>();


        radiusSeekBar = findViewById(R.id.seekBar);
        radiusTextView = findViewById(R.id.radiusZahl);
        suchen = findViewById(R.id.suchenButton);
        radiusSeekBar.setProgress(7);
        radiusTextView.setText(String.valueOf(radiusSeekBar.getProgress() + " km"));

        LocationFinder finder;
        lat=0.0;
        lng = 0.0;
        finder = new LocationFinder(this);
        if(finder.canGetLocation()){
            lat = finder.getLatitude();
            lng = finder.getLongitude();
        }else{
            finder.showSettingsAlert();
        }


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
        String radius = String.valueOf(radiusSeekBar.getProgress());

        getTankstellen(radius);

        suchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String radius = String.valueOf(radiusSeekBar.getProgress());
                String lat = String.valueOf(finder.getLatitude());
                String lng = String.valueOf(finder.getLongitude());
                adapter.clearList();
                getTankstellen(radius);

            }
        });

    }

    private void getTankstellen(String radius){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TankstellenApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TankstellenApi tankstellenApi = retrofit.create(TankstellenApi.class);

        Call<Tankstellen> call = tankstellenApi.getTankstellen("52.318","10.007",radius,"all","dist",TankstellenApi.API_KEY);
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
}
