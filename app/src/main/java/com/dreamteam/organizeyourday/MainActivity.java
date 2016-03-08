package com.dreamteam.organizeyourday;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

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

    public void renderNavigationDrawer(Toolbar toolbar) {

        AccountHeader account = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem().withName("DreamTeam").withEmail("dreamTeam@gmail.com")
                )
                .withHeaderBackground(R.drawable.head_wallpaper)
                .build();

        final PrimaryDrawerItem item1 =  new PrimaryDrawerItem().withName(R.string.nav_bar_home);
        final SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName(R.string.nav_bar_settings);
        final SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName(R.string.nav_bar_about);

        Drawer result = new DrawerBuilder()
                .withAccountHeader(account)
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        item1, item2, item3
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem == item2) {
                            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                            startActivity(intent);
                        }
                        else if (drawerItem == item3) {
                            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                            startActivity((intent));
                        }
                        return false;
                    }
                })

              .build();
    }

}
