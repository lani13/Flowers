package com.example.Kwiaty;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class FlowerData {


    String result, address;
    JSONArray jArray;
    ArrayList<Flower> flowerList;


   public FlowerData(String address)
    {
        result = null;
        this.address = address;
    }


    public ArrayList<Flower> downloadFlowers()
    {
        flowerList = new ArrayList<Flower>();

        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(address);
            ResponseHandler<String> handler = new BasicResponseHandler();
            result = client.execute(httpGet, handler);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result != null)
        {
            try
            {
                jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject data = jArray.getJSONObject(i);
                    Flower kwiat = new Flower(
                            data.getLong("gatunek_id"),
                            data.getString("nazwa_rodz"),
                            data.getString("nazwa_gat"),
                            data.getString("nazwa_reg"),
                            data.getString("nazwa_pok"),
                            data.getString("nazwa_typ"),
                            data.getString("nazwa_stan"),
                            data.getString("temp"),
                            data.getString("kolor_kwiatow"),
                            data.getString("foto")
                            );
                    flowerList.add(kwiat);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return flowerList;
    }


}
