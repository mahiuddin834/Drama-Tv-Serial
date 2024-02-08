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
import com.itnation.dramaserial.Adapter.BanglaDubbingAdapter;
import com.itnation.dramaserial.Adapter.HindiDubbingAdapter;
import com.itnation.dramaserial.ModelClass.BanglaDubbingModel;
import com.itnation.dramaserial.ModelClass.HindiDubbingModel;
import com.itnation.dramaserial.R;

import java.util.ArrayList;


public class HindiDubbingFragment extends Fragment {

    RecyclerView hindiDubbingSerial_RV;
    DatabaseReference databaseReference;
    HindiDubbingAdapter hindiDubbingAdapter;
    ArrayList<HindiDubbingModel> hindiDubbingModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_hindi_dubbing, container, false);

        hindiDubbingSerial_RV=v.findViewById(R.id.hindiDubbingSerial_RV);

        hindiDubbingSerial();






        return v;
    }

    private void hindiDubbingSerial(){



        databaseReference = FirebaseDatabase.getInstance().getReference("HindiDubbingDramaList");

        hindiDubbingSerial_RV.setLayoutManager(new GridLayoutManager(getActivity(),2));
        hindiDubbingModelArrayList = new ArrayList<>();
        hindiDubbingAdapter = new HindiDubbingAdapter(getActivity(), hindiDubbingModelArrayList);
        hindiDubbingSerial_RV.setAdapter(hindiDubbingAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    HindiDubbingModel hindiDubbingModel = dataSnapshot.getValue(HindiDubbingModel.class);
                    hindiDubbingModelArrayList.add(hindiDubbingModel);


                }

                hindiDubbingAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}