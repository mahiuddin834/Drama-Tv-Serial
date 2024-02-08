package com.itnation.dramaserial.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.itnation.dramaserial.Adapter.BanglaSerialAdapter;
import com.itnation.dramaserial.Adapter.PopularAdapter;
import com.itnation.dramaserial.ModelClass.BanglaSerialModel;
import com.itnation.dramaserial.ModelClass.PopularModel;
import com.itnation.dramaserial.R;

import java.util.ArrayList;


public class BanglaFragment extends Fragment {

    RecyclerView banglaSerial_RV;

    BanglaSerialAdapter banglaSerialAdapter;
    BanglaSerialModel banglaSerialModel;
    ArrayList<BanglaSerialModel> banglaSerialModelArrayList;
    DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bangla, container, false);

        banglaSerial_RV= v.findViewById(R.id.banglaSerial_RV);

        banglaSerialList();









        return v;
    }

    private void banglaSerialList(){

        databaseReference = FirebaseDatabase.getInstance().getReference("BanglaDramaList");
        banglaSerial_RV.setLayoutManager(new GridLayoutManager(getActivity(),2));

        banglaSerialModelArrayList = new ArrayList<>();

        banglaSerialAdapter = new BanglaSerialAdapter(getActivity(), banglaSerialModelArrayList);
        banglaSerial_RV.setAdapter(banglaSerialAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    BanglaSerialModel banglaSerialModel = dataSnapshot.getValue(BanglaSerialModel.class);
                    banglaSerialModelArrayList.add(banglaSerialModel);


                }

                banglaSerialAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}