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
import com.itnation.dramaserial.ModelClass.HindiSerialModel;
import com.itnation.dramaserial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HindiSerialAdapter extends RecyclerView.Adapter<HindiSerialAdapter.ViewHolder> {

    Context context;
    ArrayList<HindiSerialModel>hindiSerialModelArrayList;

    public HindiSerialAdapter(Context context, ArrayList<HindiSerialModel> hindiSerialModelArrayList) {
        this.context = context;
        this.hindiSerialModelArrayList = hindiSerialModelArrayList;
    }

    @NonNull
    @Override
    public HindiSerialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.serial_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HindiSerialAdapter.ViewHolder holder, int position) {
        HindiSerialModel hindiSerialModel = hindiSerialModelArrayList.get(position);

        String serialName= hindiSerialModel.getSerialName();
        String imgUrl = hindiSerialModel.getImgUrl();

        holder.serialName.setText(serialName);
        Picasso.get().load(imgUrl).
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
        return hindiSerialModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView serialImg;
        TextView serialName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serialImg=itemView.findViewById(R.id.serialImg);
            serialName=itemView.findViewById(R.id.serialName);
        }
    }
}
