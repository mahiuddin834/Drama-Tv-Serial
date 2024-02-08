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
import com.itnation.dramaserial.Activity.PlayerActivity;
import com.itnation.dramaserial.ModelClass.DramaSerialModel;
import com.itnation.dramaserial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DramaSerialAdapter extends RecyclerView.Adapter<DramaSerialAdapter.ViewHolder> {

    Context context;
    ArrayList<DramaSerialModel>dramaSerialModelArrayList;

    public DramaSerialAdapter(Context context, ArrayList<DramaSerialModel> dramaSerialModelArrayList) {
        this.context = context;
        this.dramaSerialModelArrayList = dramaSerialModelArrayList;
    }

    @NonNull
    @Override
    public DramaSerialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.drama_serial_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DramaSerialAdapter.ViewHolder holder, int position) {

        DramaSerialModel dramaSerialModel = dramaSerialModelArrayList.get(position);

        holder.epName.setText(dramaSerialModel.getNameEp());

        String videoId = dramaSerialModel.getVideoId();


        String thumLink = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
        Picasso.get().load(thumLink).
                placeholder(R.drawable.loading_image).
                into(holder.imgThum);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (videoId != null){

                    PlayerActivity.video_id= videoId;
                    Intent intent = new Intent(context, PlayerActivity.class);
                    context.startActivity(intent);

                }else {

                    Toast.makeText(context,"Item not fond",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return dramaSerialModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThum;
        TextView epName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgThum=itemView.findViewById(R.id.imgThum);
            epName=itemView.findViewById(R.id.epName);
        }
    }
}
