package com.itnation.dramaserial.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.dramaserial.Adapter.BanglaDubbingAdapter;
import com.itnation.dramaserial.Adapter.BanglaSerialAdapter;
import com.itnation.dramaserial.ModelClass.BanglaDubbingModel;
import com.itnation.dramaserial.ModelClass.BanglaSerialModel;
import com.itnation.dramaserial.R;

import java.util.ArrayList;


public class BanglaDubbingFragment extends Fragment {
    TextView banglaDubbingSerialText;
    RecyclerView banglaDubbingSerial_RV;

    BanglaDubbingAdapter banglaDubbingAdapter;
    ArrayList<BanglaDubbingModel> banglaDubbingModelArrayList;
    DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_bangla_dubbing, container, false);

        banglaDubbingSerial_RV=v.findViewById(R.id.banglaDubbingSerial_RV);
        banglaDubbingSerialText=v.findViewById(R.id.banglaDubbingSerialText);


        banglaDubbingSerial();




        return v;
    }

    private void banglaDubbingSerial(){


        databaseReference = FirebaseDatabase.getInstance().getReference("BanglaDubbingDramaList");
        banglaDubbingSerial_RV.setLayoutManager(new GridLayoutManager(getActivity(),2));

        banglaDubbingModelArrayList = new ArrayList<>();

        banglaDubbingAdapter = new BanglaDubbingAdapter(getActivity(), banglaDubbingModelArrayList);
        banglaDubbingSerial_RV.setAdapter(banglaDubbingAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    BanglaDubbingModel banglaDubbingModel = dataSnapshot.getValue(BanglaDubbingModel.class);
                    banglaDubbingModelArrayList.add(banglaDubbingModel);


                }

                banglaDubbingAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}