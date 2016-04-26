package com.dreamteam.organizeyourday;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
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
import com.dreamteam.organizeyourday.Fragments.FragmentTodayCards;
import com.dreamteam.organizeyourday.adapter.TodayCardListAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static boolean isFirstStart = true;
    public static boolean isCurrentThemeChanged;

    FragmentHome home;
    FragmentShare share;
    FragmentTodayCards todayCards;
    FloatingActionButton fab;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.setCurrentNoActionBarTheme(this);

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        home = new FragmentHome();
        share = new FragmentShare();
        todayCards = new FragmentTodayCards();


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
                    return todayCards;
                default:
                    return home;
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
        DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
        switch (id){
            case R.id.low_priority:
                home.refreshAdapter(db.getCardsWithSamePriority(0));
                break;
            case R.id.medium_priority:
                home.refreshAdapter(db.getCardsWithSamePriority(1));
                break;
            case R.id.hard_priority:
                home.refreshAdapter(db.getCardsWithSamePriority(2));
                break;
            case R.id.nightmare_priority:
                home.refreshAdapter(db.getCardsWithSamePriority(3));
                break;
            case R.id.all_cards:
                home.refreshAdapter(db.getListOfDataBaseComponent());
        }
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
            case R.id.nav_share:

                break;
            case R.id.sorting:
                DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
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
