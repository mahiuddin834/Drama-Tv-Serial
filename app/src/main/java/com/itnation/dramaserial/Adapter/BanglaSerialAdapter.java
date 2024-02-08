package com.itnation.dramaserial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itnation.dramaserial.Activity.DramaSerialActivity;
import com.itnation.dramaserial.Fragment.BanglaFragment;
import com.itnation.dramaserial.ModelClass.BanglaSerialModel;
import com.itnation.dramaserial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BanglaSerialAdapter extends RecyclerView.Adapter<BanglaSerialAdapter.ViewHoilder> {

    Context context;
    ArrayList<BanglaSerialModel> banglaSerialModelArrayList;

    public BanglaSerialAdapter(Context context, ArrayList<BanglaSerialModel> banglaSerialModelArrayList) {
        this.context = context;
        this.banglaSerialModelArrayList = banglaSerialModelArrayList;
    }

    @NonNull
    @Override
    public BanglaSerialAdapter.ViewHoilder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.serial_list_item, parent, false);
        return new ViewHoilder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BanglaSerialAdapter.ViewHoilder holder, int position) {

        BanglaSerialModel banglaSerialModel = banglaSerialModelArrayList.get(position);

        String serialName = banglaSerialModel.getSerialName();
        String ImgUrl = banglaSerialModel.getImgUrl();
        holder.serialName.setText(serialName);
        Picasso.get().load(ImgUrl).
                placeholder(R.drawable.loading_image).
                into(holder.serialImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (serialName != null){

                    DramaSerialActivity.repo= serialName;
                    Intent intent = new Intent(context, DramaSerialActivity.class);
                    context.startActivity(intent);

                }else {

                    Toast.makeText(context,"Item not fond",Toast.LENGTH_SHORT).show();
                }



            }
        });





    }

    @Override
    public int getItemCount() {
        return banglaSerialModelArrayList.size();
    }

    public static class ViewHoilder extends RecyclerView.ViewHolder {

        ImageView serialImg;
        TextView serialName;
        public ViewHoilder(@NonNull View itemView) {
            super(itemView);

            serialImg=itemView.findViewById(R.id.serialImg);
            serialName=itemView.findViewById(R.id.serialName);
        }
    }
}
