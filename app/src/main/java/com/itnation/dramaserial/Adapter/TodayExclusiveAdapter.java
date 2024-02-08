package com.itnation.dramaserial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itnation.dramaserial.Activity.PlayerActivity;
import com.itnation.dramaserial.ModelClass.TodayExclusiveModel;
import com.itnation.dramaserial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TodayExclusiveAdapter extends RecyclerView.Adapter<TodayExclusiveAdapter.ViewHolder> {

    Context context;
    ArrayList<TodayExclusiveModel> todayExclusiveModelArrayList;

    public TodayExclusiveAdapter(Context context, ArrayList<TodayExclusiveModel> todayExclusiveModelArrayList) {
        this.context = context;
        this.todayExclusiveModelArrayList = todayExclusiveModelArrayList;
    }

    @NonNull
    @Override
    public TodayExclusiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.feed_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayExclusiveAdapter.ViewHolder holder, int position) {

        TodayExclusiveModel todayExclusiveModel=todayExclusiveModelArrayList.get(position);


        String name = todayExclusiveModel.getName();

        String videoId= todayExclusiveModel.getVideoId();

        holder.name.setText(name);

        String thumLink = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
        Picasso.get().load(thumLink).
                placeholder(R.drawable.loading_image).
                into(holder.child_image);

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
        return todayExclusiveModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView child_image;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            child_image=itemView.findViewById(R.id.child_image);
            name=itemView.findViewById(R.id.name);
        }
    }
}
