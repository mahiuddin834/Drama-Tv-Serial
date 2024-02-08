package com.itnation.dramaserial;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.itnation.dramaserial.Activity.SearchActivity;
import com.itnation.dramaserial.Fragment.BanglaDubbingFragment;
import com.itnation.dramaserial.Fragment.BanglaFragment;
import com.itnation.dramaserial.Fragment.HindiDubbingFragment;
import com.itnation.dramaserial.Fragment.HindiFragment;
import com.itnation.dramaserial.Fragment.HomeFragment;
import com.itnation.dramaserial.Fragment.TodayExclusiveFragment;
import com.itnation.dramaserial.Utility.NetworkChangeListener;

public class MainActivity extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;

    TextView headerMail, headerName;
    View headerView;
    TextView txtMarquee;


    AdView adView;
    int bannerAdsClick=0;
    LinearLayout banner_container;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        materialToolbar= findViewById(R.id.toolbar_layout);

        frameLayout= findViewById(R.id.frame_layout);
        navigationView= findViewById(R.id. navigation_view);
        headerView= navigationView.getHeaderView(0);
        headerName= headerView.findViewById(R.id.headerName);
        txtMarquee =headerView.findViewById(R.id.marqueeText);

        txtMarquee.setSelected(true);

        //initBannerAds
        loadBannerAds();






        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new HomeFragment());
        fragmentTransaction.commit();

        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle
                (MainActivity.this, drawerLayout, materialToolbar, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getItemId()==R.id.home){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.replace(R.id.frame_layout, new HomeFragment());
                    fragmentTransaction.commit();



                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if (item.getItemId()==R.id.bangla){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new BanglaFragment());
                    fragmentTransaction.commit();

                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if (item.getItemId()==R.id.hindi){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new HindiFragment());
                    fragmentTransaction.commit();


                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if (item.getItemId()==R.id.banglaDub){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new BanglaDubbingFragment());
                    fragmentTransaction.commit();


                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if (item.getItemId()==R.id.hindiDub){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new HindiDubbingFragment());
                    fragmentTransaction.commit();


                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if (item.getItemId()==R.id.today){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new TodayExclusiveFragment());
                    fragmentTransaction.commit();


                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if (item.getItemId()==R.id.feedBack){


                    try {
                        Uri marketUri = Uri.parse("market://details?id=" + getPackageName());
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        startActivity(marketIntent);
                    }catch(ActivityNotFoundException e) {
                        Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        startActivity(marketIntent);
                    }

                    drawerLayout.closeDrawer(GravityCompat.START);

                }




                return true;
            }
        });
    }




    @Override
    protected void onStart() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);

        super.onStart();
    }


    @Override
    protected void onStop() {

        unregisterReceiver(networkChangeListener);

        super.onStop();
    }


    //=================================Banner Ads========================================//


    //banner ads
    private void loadBannerAds(){

        adView = new AdView(this, "IMG_16_9_APP_INSTALL#748296343412962_748300210079242", AdSize.BANNER_HEIGHT_50);

// Find the Ad Container
        banner_container= findViewById(R.id.banner_container);

// Add the ad view to your activity layout
        banner_container.addView(adView);




        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {


            }

            @Override
            public void onAdLoaded(Ad ad) {
// Ad loaded callback
            }

            @Override
            public void onAdClicked(Ad ad) {

                bannerAdsClick++;

                if (bannerAdsClick>=2){

                    if (adView!=null){
                        adView.destroy();
                        banner_container.setVisibility(View.GONE);
                    }
                }
// Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
// Ad impression logged callback
            }
        };

        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());
    }
    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }

        super.onDestroy();
    }



}