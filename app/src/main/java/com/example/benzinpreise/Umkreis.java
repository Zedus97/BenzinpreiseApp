package com.example.benzinpreise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.benzinpreise.apiRequest.Tankstellen;
import com.example.benzinpreise.apiRequest.TankstellenApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Umkreis extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Station> tanktsellenList;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umkreis);

        String lat = getIntent().getStringExtra("lat");
        String lng = getIntent().getStringExtra("lng");
        String umkreis = getIntent().getStringExtra("umkreis");

        //Vorherige geladen Liste wird gelöscht um die neue darzustellen
        if (adapter != null) {
            adapter.clearList();
        }
        //Such die RecylcerList und setzte dafür die Variable recylcerView
        recyclerView = findViewById(R.id.stationsList);
        tanktsellenList = new ArrayList<>();


        getTankstellen(umkreis, lat, lng);

    }


    private void getTankstellen(String radius, String lat, String lng) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TankstellenApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TankstellenApi tankstellenApi = retrofit.create(TankstellenApi.class);

        Call<Tankstellen> call = tankstellenApi.getTankstellen(lat, lng, radius, "all", "dist", TankstellenApi.API_KEY);
        call.enqueue(new Callback<Tankstellen>() {
            @Override
            public void onResponse(Call<Tankstellen> call, Response<Tankstellen> response) {


                Tankstellen antwort = response.body();
                List<Tankstellen.Stations> petrolStation = antwort.stations;

                for (Tankstellen.Stations stationen : petrolStation) {

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
