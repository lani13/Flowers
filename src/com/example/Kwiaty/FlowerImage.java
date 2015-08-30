package com.example.Kwiaty;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FlowerImage
{
    Bitmap image;
    String address;
    URL url;
    URLConnection connect;

     public FlowerImage(String address)
      {
        this.address=address;
      }


    public Bitmap loadImage()
      {

          try
          {
              url = new URL(address);
              connect = url.openConnection();
              image = BitmapFactory.decodeStream(connect.getInputStream());
          }
            catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
          return image;
      }

}
