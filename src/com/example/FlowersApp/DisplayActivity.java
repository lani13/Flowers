package com.example.FlowersApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayActivity extends Activity
{

    public ListView list;
    String address;
    public ArrayList<Flower> flowers;
    public ArrayAdapter<Flower> adapter;
    Context context;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showall);


        context = getApplicationContext();

       list = (ListView)findViewById(R.id.FlowerList);

       address = "http://xxx/wyswietl_mobile.php";

       new Download().execute(address);

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id)
           {
               Intent i = new Intent(context, DisplayFlowerActivity.class);
               Flower temp = flowers.get(position);

               i.putExtra("name", temp.toString());
               i.putExtra("values", temp.getValues());
               i.putExtra("foto", temp.getFoto());
               startActivity(i);

           }
       });



    }



    public class Download extends AsyncTask<String, Void, ArrayList<Flower>>
    {

        @Override
        protected ArrayList<Flower> doInBackground(String... params) {

           FlowerData dataFlowers = new FlowerData(params[0]);
           flowers = dataFlowers.downloadFlowers();

           return flowers;
        }


        @Override
        protected void onPreExecute()
        {
            Toast.makeText(context, "Rozpoczynam pobieranie danych", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(ArrayList<Flower> flowerList)
        {
          adapter = new ArrayAdapter<Flower>(context,R.layout.adapterlayout, flowerList);

          list.setAdapter(adapter);
        }
    }


}