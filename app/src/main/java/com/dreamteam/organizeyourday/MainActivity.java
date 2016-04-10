package com.dreamteam.organizeyourday;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dreamteam.organizeyourday.Fragments.FragmentHome;
import com.dreamteam.organizeyourday.Fragments.FragmentShare;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static boolean isFirstStart = true;
    public static boolean isCurrentThemeChanged;

    FragmentShare share;
    FragmentHome home;
    FloatingActionButton fab;
    int counter =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.setCurrentMainTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewCardActivity.class);
                startActivityForResult(intent, RESULT_OK);
                overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);

                //DatabaseHelper db = new DatabaseHelper(ContextContainer.getContainer());
                //db.addCard("test title" + counter++);
                // home.refreshAdapter();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        share = new FragmentShare();
        home = new FragmentHome();

        FragmentTransaction ft = getFragmentManager().beginTransaction().add(R.id.container, home);
        if (isFirstStart) {
            isFirstStart = false;
            //ft.addToBackStack(null);
            ft.commit();
        } else {
            //ft.addToBackStack(null);
            //ft.show(home);
            ft.commit();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        if (isCurrentThemeChanged) {
            isCurrentThemeChanged=false;
            recreate();
        }else {
            isCurrentThemeChanged=false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction ftrans= getFragmentManager().beginTransaction();
        Intent intent;
        switch (id){
            case R.id.home:
                fab.show();
                ftrans.show(home);
                //ftrans.replace(R.id.container, home);
                break;
            case R.id.nav_share:
                fab.hide();
                ftrans.show(share);
                //ftrans.replace(R.id.container, share);
                break;
            case R.id.sorting:
                break;
            case R.id.about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivityForResult(intent, RESULT_OK);
                overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
                break;
            case R.id.settings:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intent,RESULT_OK);
                overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
                break;
            case R.id.notifications:
                fab.hide();
                intent = new Intent(MainActivity.this, TimeNotification.class);
                startActivityForResult(intent, RESULT_OK);
                overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
                break;
        }
ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        home.refreshAdapter();
    }

    public void jumpToAccount(View view) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        Intent intent = new Intent(MainActivity.this, Account.class);
        startActivityForResult(intent,RESULT_OK);
        overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
    }

}
