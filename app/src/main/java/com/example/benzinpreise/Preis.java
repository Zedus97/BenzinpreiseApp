package com.example.benzinpreise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.benzinpreise.apiRequest.Tankstellen;
import com.example.benzinpreise.apiRequest.TankstellenApi;
import com.example.benzinpreise.apiRequest.TankstellenPreis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Preis extends AppCompatActivity {

    RecyclerView recyclerView;
    List<StationPreis> tanktsellenListPreis;
    AdapterPreis adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preis);
        String lat = getIntent().getStringExtra("lat");
        String lng = getIntent().getStringExtra("lng");
        String umkreis = getIntent().getStringExtra("umkreis");

        //Vorherige geladen Liste wird gelöscht um die neue darzustellen
        if (adapter != null) {
            adapter.clearList();
        }
        //Such die RecylcerList und setzte dafür die Variable recylcerView
        recyclerView = findViewById(R.id.preisList);
        tanktsellenListPreis = new ArrayList<>();


        getTankstellenPreis(umkreis, lat, lng, "diesel", "price");

    }


    private void getTankstellenPreis(String radius, String lat, String lng, String type, String filter) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TankstellenApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TankstellenApi tankstellenApi = retrofit.create(TankstellenApi.class);

        Call<TankstellenPreis> call = tankstellenApi.getTankstellenPreis(lat, lng, radius, "diesel", "price", TankstellenApi.API_KEY);
        call.enqueue(new Callback<TankstellenPreis>() {
            @Override
            public void onResponse(Call<TankstellenPreis> call, Response<TankstellenPreis> response) {


                TankstellenPreis antwort = response.body();
                List<TankstellenPreis.Stations> petrolStation = antwort.stationsPreis;

                for (TankstellenPreis.Stations stationenPreis : petrolStation) {

                    StationPreis tankstelle = new StationPreis();
                    tankstelle.setName(stationenPreis.getName());
                    tankstelle.setBrand(stationenPreis.getBrand());
                    tankstelle.setStreet(stationenPreis.getStreet());
                    tankstelle.setPlace(stationenPreis.getPlace());
                    tankstelle.setLat(stationenPreis.getLat());
                    tankstelle.setLng(stationenPreis.getLng());
                    tankstelle.setDist(stationenPreis.getDist());
                    tankstelle.setPrice(stationenPreis.getPrice());
                    tankstelle.setOpen(stationenPreis.isOpen());
                    tankstelle.setHouseNumber(stationenPreis.getHouseNumber());
                    tankstelle.setPostCode(stationenPreis.getPostCode());
                    tanktsellenListPreis.add(tankstelle);

                    Log.d("Preis des Kraftstoffes", stationenPreis.getPrice());

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new AdapterPreis(getApplicationContext(), tanktsellenListPreis);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<TankstellenPreis> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}