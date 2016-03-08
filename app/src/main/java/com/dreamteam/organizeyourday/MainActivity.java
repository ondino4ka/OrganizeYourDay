package com.dreamteam.organizeyourday;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

       Drawer result = new DrawerBuilder()
               .withActivity(this)
               .withToolbar(toolbar)
               .withActionBarDrawerToggleAnimated(true)
               .build();
    }
}
