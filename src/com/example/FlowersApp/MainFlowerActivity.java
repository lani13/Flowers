package com.example.FlowersApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainFlowerActivity extends Activity {

      Button bInsert, bSearch, bShow;
      Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bInsert = (Button)findViewById(R.id.ButtonWstaw);
        bSearch = (Button)findViewById(R.id.ButtonWyszukaj);
        bShow = (Button)findViewById(R.id.ButtonWyswietl);

        context = getApplicationContext();

        bInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, InsertActivity.class);
                startActivity(i);

            }
        });

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SearchActivity.class);
                startActivity(i);


            }
        });


        bShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DisplayActivity.class);
                startActivity(i);

            }
        });


    }




}
