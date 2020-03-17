package com.example.benzinpreise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import retrofit2.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private List<Tankstellen> tankstellenList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult =findViewById(R.id.text_view_result);
        getTankstellen();

    }

    private void getTankstellen(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TankstellenApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TankstellenApi tankstellenApi = retrofit.create(TankstellenApi.class);
        Call<Tankstellen> call = tankstellenApi.getTankstellen("52.318","10.007","7","all","dist",TankstellenApi.API_KEY);
        call.enqueue(new Callback<Tankstellen>() {
            @Override
            public void onResponse(Call<Tankstellen> call, Response<Tankstellen> response) {

                    String displayResponse = "";
                    Tankstellen antwort = response.body();
                    List<Tankstellen.Stations> petrolStation = antwort.stations;


                    displayResponse += "ok: " +antwort.isOk() +"\n" + "license: "+antwort.getLicense() +"\n"+ "data: "+antwort.getData() +"\n"+ "status: "+antwort.getStatus() +"\n";

                    int i =1;
                    for(Tankstellen.Stations stationen : petrolStation){
                        displayResponse +=  "\n"
                                + "Tankstelle " + i + " \n"
                                + "id: " + stationen.id + " \n"
                                + "name: " +  stationen.name + " \n"
                                + "brand: " +  stationen.brand + " \n"
                                + "street: " +  stationen.street + " \n"
                                + "place: " +  stationen.place + " \n"
                                + "lat: " +  stationen.lat + "\n "
                                + "lng: " +  stationen.lng + " \n"
                                + "dist: " +  stationen.dist + " \n"
                                + "diesel: " +  stationen.diesel + " \n"
                                + "e5: " +  stationen.e5 + " \n"
                                + "e10: " +  stationen.e10 + " \n"
                                + "isOpen: " +  stationen.isOpen + " \n"
                                + "houseNumber: " +  stationen.houseNumber + " \n"
                                + "postCode: " +  stationen.postCode+" \n"
                                + "\n";
                        i++;
                    }
                    textViewResult.setText(displayResponse);



            }

            @Override
            public void onFailure(Call<Tankstellen> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}
