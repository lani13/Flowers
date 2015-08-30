package com.example.FlowersApp;


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

public class SettingSpinners {


    public static ArrayList<String> setSpinnerPokroj(String address)
    {
        JSONArray jArray;
        ArrayList<String> pokrojList= new ArrayList<String>();
        pokrojList.add("Pokrój");
        String result=null;

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
            try {
                jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject obj = jArray.getJSONObject(i);
                    pokrojList.add(obj.getString("nazwa_pok"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return pokrojList;
    }


    public  static ArrayList<String> setSpinnerStan(String address)
    {
        JSONArray jArray;
        ArrayList<String> stanList= new ArrayList<String>();
        stanList.add("Stanowisko");
        String result=null;

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
            try {
                jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject obj = jArray.getJSONObject(i);
                    stanList.add(obj.getString("nazwa_stan"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stanList;
    }


    public static ArrayList<String> setSpinnerReg(String address)
    {
        JSONArray jArray;
        ArrayList<String> regList= new ArrayList<String>();
        regList.add("Region");
        String result=null;

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
            try {
                jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject obj = jArray.getJSONObject(i);
                    regList.add(obj.getString("nazwa_reg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return regList;
    }


    public  static ArrayList<String> setSpinnerTemp(String address)
    {
        JSONArray jArray;
        ArrayList<String> tempList= new ArrayList<String>();
        tempList.add("Warunki cieplne");
        tempList.add("Chlodne");
        tempList.add("Umiarkowane");
        tempList.add("Cieple");

        String result=null;

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
            try {

                jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject obj = jArray.getJSONObject(i);
                    tempList.add(obj.getString("temp"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return tempList;
    }

    public  static ArrayList<String> setSpinnerTyp(String address)
    {
        JSONArray jArray;
        ArrayList<String> tempList= new ArrayList<String>();
        tempList.add("Typ");
        String result=null;

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
            try {

                jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject obj = jArray.getJSONObject(i);
                    tempList.add(obj.getString("nazwa_typ"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return tempList;
    }



}
