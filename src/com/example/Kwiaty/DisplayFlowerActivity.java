package com.example.Kwiaty;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayFlowerActivity extends Activity
{

    TextView tName, tData;
    ImageView imageView;
    Context context;
    String path, fileName;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower);

        tName = (TextView)findViewById(R.id.FlowerName);
        tData = (TextView)findViewById(R.id.FlowerData);
        imageView = (ImageView)findViewById(R.id.FlowerImage);

        context = getApplicationContext();

        path = "http://xxx/foto/";

        Intent i = getIntent();

        Bundle b = i.getExtras();
        if(b!=null)
        {
            String s1 = (String)b.get("name");
            String s2 = (String)b.get("values");
            fileName =  (String)b.get("foto");
            tName.setText(s1);
            tData.setText(s2);
        }

        new DownloadImage().execute(path+fileName+"");

    }


    public class DownloadImage extends AsyncTask<String, Void, Bitmap>
    {


        @Override
        protected Bitmap doInBackground(String... params) {

            FlowerImage img = new FlowerImage(params[0]);
            Bitmap bitmap = img.loadImage();

            return bitmap;
        }


        @Override
        protected void onPreExecute()
        {
            Toast.makeText(context, "Rozpoczynam pobieranie obrazka", Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
              imageView.setImageBitmap(bitmap);
        }
    }

}
