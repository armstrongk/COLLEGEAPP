package com.example.armstrong.college;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.armstrong.college.FireBaseMessage.Add;
import com.example.armstrong.college.FireBaseMessage.AdminNoticeActivity;
import com.example.armstrong.college.FireBaseMessage.SMS;

public class AdminDrawer extends AppCompatActivity{
    private FragmentManager fragmentManager;
    private View navHeader;
    private DrawerLayout mDrawer;
    private Fragment fragment = null;
    private String[] activityTitles;


    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_drawer);
      // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }





        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);



        //fragmentManager = getSupportFragmentManager();
        //final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragment = new RegisterFragment();
        //fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        //fragmentTransaction.commit();

        disableNavigationViewScrollbars(navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_profile) {
                    Intent intent1 = new Intent(AdminDrawer.this, EditProfileActivity.class);
                    startActivity(intent1);
                    return true;
                    //fragment = new RegisterFragment();
                } else if (id == R.id.nav_chat) {
                    Intent intent=new Intent(AdminDrawer.this, Add.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_contact) {
                    Intent intent = new Intent(AdminDrawer.this,SMS.class);
                    startActivity(intent);
                } else if (id == R.id.nav_online) {
                    Intent sen=new Intent(AdminDrawer.this, AdminNoticeActivity.class);
                    startActivity(sen);

                    }
                else if (id == R.id.nav_settings) {
                    Intent sent=new Intent(AdminDrawer.this, AdminNoticeActivity.class);
                    startActivity(sent);
                    //menu for settings

                } else if (id == R.id.nav_logout) {
                    //login out
                    Intent intent = new Intent(AdminDrawer.this,LoginActivityAdmin.class);
                    startActivity(intent);
                }
               // FragmentTransaction transaction = fragmentManager.beginTransaction();
                //transaction.replace(R.id.main_container_wrapper, fragment);
               // transaction.commit();
              DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                assert mDrawer != null;
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.home_navigation_drawer_open, R.string.home_navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

           @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        mDrawer.addDrawerListener(toggle);//add

        toggle.syncState();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ///about us

            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }


}