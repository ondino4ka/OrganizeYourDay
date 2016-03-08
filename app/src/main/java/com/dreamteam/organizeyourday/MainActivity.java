package com.dreamteam.organizeyourday;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            renderNavigationDrawer(toolbar);
        }

    }

    public void renderNavigationDrawer(Toolbar toolbar){

        AccountHeader account = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem().withName("DreamTeam").withEmail("dreamTeam@gmail.com")
                )
                .withHeaderBackground(R.drawable.head_wallpaper)
                .build();

        Drawer result = new DrawerBuilder()
                .withAccountHeader(account)
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.nav_bar_home),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.nav_bar_settings)
                )
                .build();
    }

    public void toSettings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }
}
