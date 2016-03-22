package com.dreamteam.organizeyourday;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.dreamteam.organizeyourday.Fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int index = 0;
    private static boolean isFirstStart = true;
    public  static boolean isCurrentThemeChanged;
    FragmentShare share;
    FragmentHome home;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        seCurrentTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditCardActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments


        share = new FragmentShare();
        home = new FragmentHome();
        FragmentTransaction ft = getFragmentManager().beginTransaction().add(R.id.container, home);
        if (isFirstStart) {
            isFirstStart = false;
            ft.addToBackStack(null);
            ft.commit();
        } else {
            ft.show(home);
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

    private void seCurrentTheme()
    {
        int theme;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        switch(index)
        {
            case 0:
                theme = sp.getInt("THEME", R.style.AppTheme_NoActionBar);
                setTheme(theme);
                break;
            case 1:
                theme = sp.getInt("THEME", R.style.Blue_NoActionBar);
                setTheme(theme);
                break;
            case 2:
                theme = sp.getInt("THEME", R.style.Pink_NoActionBar);
                setTheme(theme);
                break;
            default:
                break;
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ftrans= getFragmentManager().beginTransaction();
        if (id == R.id.home) {
            fab.show();
            ftrans.replace(R.id.container, home);
        }
        else if (id == R.id.nav_share){
            fab.hide();
            ftrans.replace(R.id.container, share);
        }
        else if (id == R.id.about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.settings){
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
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



    public void jumpToAccount(View view)
    {
        Intent intent = new Intent(MainActivity.this, Account.class);
        startActivity(intent);
    }
}
