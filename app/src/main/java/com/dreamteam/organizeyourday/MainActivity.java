package com.dreamteam.organizeyourday;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.Fragments.FragmentHome;
import com.dreamteam.organizeyourday.Fragments.FragmentShare;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static boolean isFirstStart = true;
    public static boolean isCurrentThemeChanged;

    FragmentShare share;
    FragmentHome home;
    FloatingActionButton fab;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.setCurrentMainTheme(this);
        home = new FragmentHome();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewCardActivity.class);
                startActivityForResult(intent, RESULT_OK);
                overridePendingTransition(R.anim.from_down_translate, R.anim.alpha_out);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return home;
                case 1:
                default:
                    return new FragmentShare();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "All";
                case 1:
                default:
                    return "Today";
            }
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
        home.refreshAdapter();
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
                break;
            case R.id.sorting:
                DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
                home.refreshAdapter(db.searchCards());
                break;
            case R.id.about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivityForResult(intent, RESULT_OK);
                overridePendingTransition(R.anim.from_down_translate, R.anim.alpha_out);
                break;
            case R.id.settings:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intent,RESULT_OK);
                overridePendingTransition(R.anim.from_down_translate, R.anim.alpha_out);
                break;
            case R.id.notifications:
                fab.hide();
                intent = new Intent(MainActivity.this, TimeNotification.class);
                startActivityForResult(intent,RESULT_OK);
                overridePendingTransition(R.anim.rotate_anim, R.anim.alpha_out);
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
    }

    public void jumpToAccount(View view) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        Intent intent = new Intent(MainActivity.this, Account.class);
        startActivityForResult(intent,RESULT_OK);
        overridePendingTransition(R.anim.from_down_translate, R.anim.alpha_out);
    }

}
