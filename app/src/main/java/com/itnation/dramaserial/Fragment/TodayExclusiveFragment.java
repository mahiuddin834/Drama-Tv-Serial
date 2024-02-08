package com.itnation.dramaserial.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.dramaserial.Adapter.FeedAdapter;
import com.itnation.dramaserial.Adapter.TodayExclusiveAdapter;
import com.itnation.dramaserial.ModelClass.FeedModel;
import com.itnation.dramaserial.ModelClass.TodayExclusiveModel;
import com.itnation.dramaserial.R;

import java.util.ArrayList;


public class TodayExclusiveFragment extends Fragment {

    RecyclerView todayExclusive_RV;
    DatabaseReference databaseReference;
    TodayExclusiveAdapter todayExclusiveAdapter;
    ArrayList<TodayExclusiveModel>todayExclusiveModelArrayList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_today_exclusive, container, false);
        todayExclusive_RV=v.findViewById(R.id.todayExclusive_RV);




        todayExclusive();







        return v;
    }

    private void todayExclusive(){



        databaseReference = FirebaseDatabase.getInstance().getReference("TodayExclusive");
        todayExclusive_RV.setLayoutManager(new LinearLayoutManager(getActivity()));

        todayExclusiveModelArrayList = new ArrayList<>();

        todayExclusiveAdapter= new TodayExclusiveAdapter(getActivity(), todayExclusiveModelArrayList);
        todayExclusive_RV.setAdapter(todayExclusiveAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    TodayExclusiveModel todayExclusiveModel = dataSnapshot.getValue(TodayExclusiveModel.class);
                    todayExclusiveModelArrayList.add(todayExclusiveModel);


                }

                todayExclusiveAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



    }



}