package com.example.FlowersApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;


public class SearchActivity extends Activity
{
    EditText ecolor, especies, egenus;
    Spinner splace, sshape, stemp, sregion, stype;
    Context context;
    String color ="", species ="", genus ="", place, shape, temp, region, type;
    String address;
    Button bSearch;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        ecolor = (EditText)findViewById(R.id.EditKolor);
        especies = (EditText)findViewById(R.id.EditGatunek);
        egenus = (EditText)findViewById(R.id.EditRodzaj);

        splace = (Spinner)findViewById(R.id.SpinnerStanowisko);
        sshape = (Spinner)findViewById(R.id.SpinnerPokroj);
        stemp = (Spinner)findViewById(R.id.SpinnerTemp);
        sregion = (Spinner)findViewById(R.id.SpinnerRegion);
        stype = (Spinner)findViewById(R.id.SpinnerTyp);

        bSearch = (Button)findViewById(R.id.buttonSearch);

        context = getApplicationContext();

        new LoadPokroj().execute("http://xxx/spinner_pokroj_mobile.php");
        new LoadStan().execute("http://xxx/spinner_stanowisko_mobile.php");
        new LoadReg().execute("http://xxx/spinner_region_mobile.php");
        new LoadTemp().execute("http://xxx/spinner_temp_mobile.php");
        new LoadTyp().execute("http://xxx/spinner_typ_mobile.php");


        address = "http://xxx/wyszukaj_mobile.php";

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                color = ecolor.getText().toString();
                species = especies.getText().toString();
                genus = egenus.getText().toString();


                place = splace.getSelectedItem().toString();
                shape = sshape.getSelectedItem().toString();
                temp = stemp.getSelectedItem().toString();
                region = sregion.getSelectedItem().toString();
                type = stype.getSelectedItem().toString();

                if ("Stanowisko".equals(place))
                    place = "";
                if ("Pokrój".equals(shape))
                    shape = "";
                if ("Warunki cieplne".equals(temp))
                    temp = "";
                if ("Region".equals(region))
                    region = "";
                if ("Typ".equals(type))
                    type = "";

                new SearchFlower().execute(address, genus, species, color, place, shape, temp, region, type);

            }
        });



    }


   public class LoadPokroj extends AsyncTask<String, Void, ArrayList<String>>
   {

       @Override
       protected ArrayList<String> doInBackground(String... params) {

         ArrayList<String> itemList = SettingSpinners.setSpinnerPokroj(params[0]);
         return itemList;
       }

       @Override
       protected void onPostExecute(ArrayList<String> strings) {
           ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, strings);
           dataAdapter.setDropDownViewResource(R.layout.myspinner);
           sshape.setAdapter(dataAdapter);
           }
   }


    public class LoadStan extends AsyncTask<String, Void, ArrayList<String>>
    {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            ArrayList<String> itemList = SettingSpinners.setSpinnerStan(params[0]);
            return itemList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, strings);
            dataAdapter.setDropDownViewResource(R.layout.myspinner);
            splace.setAdapter(dataAdapter);
        }
    }

    public class LoadReg extends AsyncTask<String, Void, ArrayList<String>>
    {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            ArrayList<String> itemList = SettingSpinners.setSpinnerReg(params[0]);
            return itemList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, strings);
            dataAdapter.setDropDownViewResource(R.layout.myspinner);
            sregion.setAdapter(dataAdapter);
        }
    }

    public class LoadTemp extends AsyncTask<String, Void, ArrayList<String>>
    {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            ArrayList<String> itemList = SettingSpinners.setSpinnerTemp(params[0]);
            return itemList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, strings);
            dataAdapter.setDropDownViewResource(R.layout.myspinner);
            stemp.setAdapter(dataAdapter);
        }
    }


   public class SearchFlower extends AsyncTask<String, Void, String>
     {
         String result;

         @Override
         protected String doInBackground(String... params)
         {
            // (address, genus, species, color, place, sshape, temp, region, type)

             ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
             list.add(new BasicNameValuePair("genus", params[1]));
             list.add(new BasicNameValuePair("species", params[2]));
             list.add(new BasicNameValuePair("color", params[3]));
             list.add(new BasicNameValuePair("place", params[4]));
             list.add(new BasicNameValuePair("sshape", params[5]));
             list.add(new BasicNameValuePair("temp", params[6]));
             list.add(new BasicNameValuePair("region", params[7]));
             list.add(new BasicNameValuePair("type", params[8]));

             HttpClient client = new DefaultHttpClient();
             HttpPost httpPost = new HttpPost(params[0]);



             try
             {
                 httpPost.setEntity(new UrlEncodedFormEntity(list));
                 HttpResponse response = client.execute(httpPost);

                 InputStream inputStream = response.getEntity().getContent();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                 StringBuilder stringBuilder = new StringBuilder();
                 String line = null;

                 int chars;

                 while( (chars = bufferedReader.read())>= 0)
                   {
                     if (chars == (int)'<')
                         break;
                     else
                         stringBuilder.append((char)chars);
                   }

                 result = stringBuilder.toString();

              }catch (ClientProtocolException e) {
                 e.printStackTrace();
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }

           return result;
         }


         @Override
         protected void onPostExecute(String res)
         {

              Intent i = new Intent(context, DisplaySearchedFlowerAcivity.class);
              i.putExtra("result", res);
              startActivity(i);
              SearchActivity.this.finish();

         }
     }


    public class LoadTyp extends AsyncTask<String, Void, ArrayList<String>>
    {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            ArrayList<String> itemList = SettingSpinners.setSpinnerTyp(params[0]);
            return itemList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, strings);
            dataAdapter.setDropDownViewResource(R.layout.myspinner);
            stype.setAdapter(dataAdapter);
        }
    }



}