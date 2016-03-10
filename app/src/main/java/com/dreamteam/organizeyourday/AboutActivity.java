package com.dreamteam.organizeyourday;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spanned;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class AboutActivity extends AppCompatActivity{

    Toolbar toolbar;
    private byte count = 0;
    String rep_url = "https://github.com/mkinitcpio/OrganizeYourDay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        generateToolbar();
        generateNavigationDrawer();


    }

    public void jumpToRepository(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(rep_url));
        startActivity(browser);
    }

    public void setTextAbout(View view) {


        TextView aboutText = (TextView)findViewById(R.id.textRepository);
        RelativeLayout relativeLayout =(RelativeLayout) findViewById(R.id.aboutLayout);
        aboutText.setText("Checkout our repository:\n " + rep_url);

        count++;
        if(count==15)
        {
            relativeLayout.setBackgroundResource(R.drawable.hack_0);
        }
        if(count>17)
        {
            relativeLayout.setBackgroundResource(R.color.colorBackground);
            count =0;
        }
    }


    private void generateToolbar() {
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    private void generateNavigationDrawer() {

        final PrimaryDrawerItem homeButton =  new PrimaryDrawerItem()
                .withName(R.string.nav_bar_home)
                .withIcon(R.drawable.ic_home_black_24dp);
        final SecondaryDrawerItem settingsButton = new SecondaryDrawerItem()
                .withName(R.string.nav_bar_settings)
                .withIcon(R.drawable.ic_build_black_24dp);
        final SecondaryDrawerItem aboutButton = new SecondaryDrawerItem()
                .withName(R.string.nav_bar_about)
                .withIcon(R.drawable.ic_feedback_black_24dp);
        final DividerDrawerItem devider = new DividerDrawerItem();

        AccountHeader account = accountHeader();

        Drawer navigationDrawer = new DrawerBuilder()
                .withAccountHeader(account)
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        homeButton,
                        devider,
                        settingsButton,
                        aboutButton
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem.equals(homeButton)){
                            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else if (drawerItem.equals(settingsButton)) {
                            Intent intent = new Intent(AboutActivity.this, SettingsActivity.class);
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
