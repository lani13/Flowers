package com.example.Kwiaty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplaySearchedFlowerAcivity extends Activity
{

    String result;
    ArrayList<Flower> flowerList = new ArrayList<Flower>();
    ListView list;
    Context context;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showsearched);

        list = (ListView)findViewById(R.id.SearchedFlowerList);


        context = getApplicationContext();

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
          {
            result = (String)b.get("result");
          }


            try
            {

                JSONArray array = new JSONArray(result);
                for (int i=0;i<array.length();i++)
                {
                    JSONObject data = array.getJSONObject(i);
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



        ArrayAdapter<Flower> adapter = new ArrayAdapter<Flower>(context, R.layout.adapterlayout, flowerList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(context, DisplayFlowerActivity.class);
                Flower temp = flowerList.get(position);

                i.putExtra("name", temp.toString());
                i.putExtra("values", temp.getValues());
                i.putExtra("fotka", temp.getFoto());
                startActivity(i);

            }
        });

    }
}