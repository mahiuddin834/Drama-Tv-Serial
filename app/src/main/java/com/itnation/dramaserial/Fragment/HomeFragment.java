package com.itnation.dramaserial.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.dramaserial.Activity.SearchActivity;
import com.itnation.dramaserial.Adapter.FeedAdapter;
import com.itnation.dramaserial.Adapter.PopularAdapter;
import com.itnation.dramaserial.ModelClass.FeedModel;
import com.itnation.dramaserial.ModelClass.PopularModel;
import com.itnation.dramaserial.R;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView popular_recycler, feed_recycler;
    LottieAnimationView animationView;
    NestedScrollView mainScroll;
    DatabaseReference databaseReference;
    PopularAdapter popularAdapter;
    PopularModel popularModel;
    FeedAdapter feedAdapter;
    FeedModel feedModel;
    ArrayList<PopularModel>popularModelArrayList;
    ArrayList<FeedModel>feedModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);





        ImageCarousel carousel = v.findViewById(R.id.carousel);
        carousel.addData(new CarouselItem("https://blog.talkcharge.com/wp-content/uploads/2021/09/zee-bangla-serial-list.jpg",""));
        carousel.addData(new CarouselItem("https://upload.wikimedia.org/wikipedia/en/0/02/Raadha.png",""));
        carousel.addData(new CarouselItem("https://i.ytimg.com/vi/70jjBVN5gKg/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLBgNdvTSEhYtYuIh7dieckrbKAYUA",""));
        carousel.addData(new CarouselItem("https://img.tellybest.com/article/202301/620x330_trp-ratings-of-bengali-serials-this-week-2023-top-10-shows-channels-today.jpg",""));
        carousel.addData(new CarouselItem("https://blog.talkcharge.com/wp-content/uploads/2021/09/zee-bangla-serial-list.jpg",""));
        carousel.addData(new CarouselItem("https://upload.wikimedia.org/wikipedia/en/0/02/Raadha.png",""));
        carousel.addData(new CarouselItem("https://i.ytimg.com/vi/RZhsjYgnC3s/maxresdefault.jpg",""));
        carousel.addData(new CarouselItem("https://whizbliz.com/wp-content/uploads/2024/01/31_12_2023-ott_shows_jan_2024_23618168.webp",""));
        carousel.addData(new CarouselItem("https://img.tellybest.com/article/202401/620x330_upcoming-serials-2023-current-future-new-indian-hindi-shows-list.jpg",""));
        carousel.addData(new CarouselItem("https://ww1.yodesiserials.su/wp-content/uploads/2022/12/zee.jpg",""));
        carousel.addData(new CarouselItem("https://i.ytimg.com/vi/D7mBe_ZJp5U/maxresdefault.jpg",""));
        carousel.addData(new CarouselItem("https://exchange4media.gumlet.io/news-photo/105733-serial.jpg?w=400&dpr=2.6",""));
        carousel.addData(new CarouselItem("https://s3images.zee5.com/wp-content/uploads/sites/7/2020/08/ZEE-TV-shows.jpg",""));
        carousel.addData(new CarouselItem("https://www.auditionform.in/wp-content/uploads/2020/07/Zee-TV-Serials-New-Episodes-from-13-July-Check-Schedule-of-all-shows.jpg",""));
        carousel.addData(new CarouselItem("https://d30ny7ijak9wq4.cloudfront.net/s3fs-public/images/story/2023/07/02/tv_shows_and_spin_6-38.jpg",""));
        carousel.addData(new CarouselItem("https://celebritytadka.com/wp-content/uploads/2022/01/BARC-TRP-report-Anupamaa-Imlie-Yeh-Rishta-Kya-kehlata-hai-Udaariyaan.jpg",""));
        carousel.addData(new CarouselItem("https://d30ny7ijak9wq4.cloudfront.net/s3fs-public/images/story/2023/07/02/tv_shows_and_spin_6-38.jpg",""));
        carousel.addData(new CarouselItem("https://i.ytimg.com/vi/70jjBVN5gKg/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLBgNdvTSEhYtYuIh7dieckrbKAYUA",""));
        carousel.addData(new CarouselItem("https://img.tellybest.com/article/202301/620x330_trp-ratings-of-bengali-serials-this-week-2023-top-10-shows-channels-today.jpg",""));




        popular_recycler = v.findViewById(R.id.popular_recyclyer);
        feed_recycler = v.findViewById(R.id.feed_recyclyer);
        animationView = v.findViewById(R.id.animationView);
        mainScroll= v.findViewById(R.id.mainScroll);
        mainScroll.setVisibility(View.GONE);


        popular();
        feed();



        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>









        return v;
    }//============================

    private void popular(){


        databaseReference = FirebaseDatabase.getInstance().getReference("Popular");
        popular_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        popularModelArrayList = new ArrayList<>();

        popularAdapter = new PopularAdapter(getActivity(), popularModelArrayList);
        popular_recycler.setAdapter(popularAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    PopularModel popularModel = dataSnapshot.getValue(PopularModel.class);
                    popularModelArrayList.add(popularModel);


                }

                popularAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    //-----------------------------------------------------------



    private void feed(){



        databaseReference = FirebaseDatabase.getInstance().getReference("Feed");
        feed_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        feedModelArrayList = new ArrayList<>();

        feedAdapter= new FeedAdapter(getActivity(),feedModelArrayList);
        feed_recycler.setAdapter(feedAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    FeedModel feedModel = dataSnapshot.getValue(FeedModel.class);
                    feedModelArrayList.add(feedModel);
                    mainScroll.setVisibility(View.VISIBLE);
                    animationView.setVisibility(View.GONE);

                }

                feedAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




    }
}