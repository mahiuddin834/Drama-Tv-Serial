package com.itnation.dramaserial.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.dramaserial.Adapter.BanglaSerialAdapter;
import com.itnation.dramaserial.Adapter.DramaSerialAdapter;
import com.itnation.dramaserial.ModelClass.BanglaSerialModel;
import com.itnation.dramaserial.ModelClass.DramaSerialModel;
import com.itnation.dramaserial.R;

import java.util.ArrayList;

public class DramaSerialActivity extends AppCompatActivity {

    ImageView back;
    TextView dramaName;
    RecyclerView drama_episodeRV;

    DramaSerialAdapter dramaSerialAdapter;
    DramaSerialModel dramaSerialModel;
    ArrayList<DramaSerialModel> dramaSerialModelArrayList;

    DatabaseReference databaseReference;

    public static String repo ="";

    InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama_serial);

        back= findViewById(R.id.back);
        dramaName= findViewById(R.id.dramaName);
        drama_episodeRV= findViewById(R.id.drama_episodeRV);


        //fullscreenAds

        fullScreenAds();

        //====================

        dramaName.setText(repo);


        databaseReference = FirebaseDatabase.getInstance().getReference(repo);
        drama_episodeRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        dramaSerialModelArrayList = new ArrayList<>();

        dramaSerialAdapter = new DramaSerialAdapter(DramaSerialActivity.this,dramaSerialModelArrayList);
        drama_episodeRV.setAdapter(dramaSerialAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    DramaSerialModel dramaSerialModel = dataSnapshot.getValue(DramaSerialModel.class);
                    dramaSerialModelArrayList.add(dramaSerialModel);


                }

                dramaSerialAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DramaSerialActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }




    //===================FullScreen Ads ============================

    @Override
    protected void onDestroy() {

        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }


    //Full Screen ADs---------------------------

    public void fullScreenAds(){



        interstitialAd = new InterstitialAd(this, "748296343412962_748300473412549");
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(com.facebook.ads.Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(com.facebook.ads.Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");



            }

            @Override
            public void onError(com.facebook.ads.Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(com.facebook.ads.Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");


                if (interstitialAd!=null && interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }

            }

            @Override
            public void onAdClicked(com.facebook.ads.Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");


            }

            @Override
            public void onLoggingImpression(com.facebook.ads.Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());




    }


}