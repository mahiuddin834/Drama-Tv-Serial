package com.itnation.dramaserial.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.itnation.dramaserial.Adapter.BanglaSerialAdapter;
import com.itnation.dramaserial.Adapter.HindiSerialAdapter;
import com.itnation.dramaserial.ModelClass.BanglaSerialModel;
import com.itnation.dramaserial.ModelClass.HindiSerialModel;
import com.itnation.dramaserial.R;

import java.util.ArrayList;


public class HindiFragment extends Fragment {

    HindiSerialAdapter hindiSerialAdapter;
    HindiSerialModel hindiSerialModel;
    ArrayList<HindiSerialModel>hindiSerialModelArrayList;

    RecyclerView hindiSerial_RV;
    DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_hindi, container, false);

        hindiSerial_RV=v.findViewById(R.id.hindiSerial_RV);

        hindiSerialList();


        return v;
    }


    private void hindiSerialList(){

        databaseReference = FirebaseDatabase.getInstance().getReference("HindiDramaList");
        hindiSerial_RV.setLayoutManager(new GridLayoutManager(getActivity(),2));

        hindiSerialModelArrayList = new ArrayList<>();

        hindiSerialAdapter = new HindiSerialAdapter(getActivity(), hindiSerialModelArrayList);
        hindiSerial_RV.setAdapter(hindiSerialAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    HindiSerialModel hindiSerialModel = dataSnapshot.getValue(HindiSerialModel.class);
                    hindiSerialModelArrayList.add(hindiSerialModel);


                }

                hindiSerialAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}