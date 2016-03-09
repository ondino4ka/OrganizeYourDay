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
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        generateToolbar();
        generateNavigationDrawer(toolbar);
    }

    private void generateToolbar() {
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    private void generateNavigationDrawer(Toolbar toolbar) {

        final PrimaryDrawerItem homeButton =  new PrimaryDrawerItem()
                .withName(R.string.nav_bar_home)
                .withIcon(R.drawable.ic_home_black_24dp);
        final SecondaryDrawerItem settingsButton = new SecondaryDrawerItem()
                .withName(R.string.nav_bar_settings)
                .withIcon(R.drawable.ic_build_black_24dp);
        final SecondaryDrawerItem item3 = new SecondaryDrawerItem()
                .withName(R.string.nav_bar_about)
                .withIcon(R.drawable.ic_feedback_black_24dp);
        final  DividerDrawerItem aboutButton = new DividerDrawerItem();

        AccountHeader account = accountHeader();

        Drawer navigationDrawer = new DrawerBuilder()
                .withAccountHeader(account)
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        homeButton,
                        aboutButton,
                        settingsButton,
                        item3
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.equals(settingsButton)){

                            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                            startActivity(intent);
                        }
                        else if (drawerItem.equals(aboutButton)) {
                            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                            startActivity((intent));
                        }
                        return false;
                    }
                })
                .build();
    }

    private AccountHeader accountHeader(){
        AccountHeader account = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("DreamTeam")
                                .withEmail("dreamTeam@gmail.com")
                )
                .withHeaderBackground(R.drawable.head_wallpaper)
                .build();
        return account;
    }



}
