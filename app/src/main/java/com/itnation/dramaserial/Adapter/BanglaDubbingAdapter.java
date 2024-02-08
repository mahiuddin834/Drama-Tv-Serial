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
import com.itnation.dramaserial.ModelClass.BanglaDubbingModel;
import com.itnation.dramaserial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BanglaDubbingAdapter extends RecyclerView.Adapter<BanglaDubbingAdapter.ViewHoldder> {

    Context context;
    ArrayList<BanglaDubbingModel> banglaDubbingModelArrayList;

    public BanglaDubbingAdapter(Context context, ArrayList<BanglaDubbingModel> banglaDubbingModelArrayList) {
        this.context = context;
        this.banglaDubbingModelArrayList = banglaDubbingModelArrayList;
    }

    @NonNull
    @Override
    public BanglaDubbingAdapter.ViewHoldder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.serial_list_item, parent, false);
        return new ViewHoldder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BanglaDubbingAdapter.ViewHoldder holder, int position) {


        BanglaDubbingModel banglaDubbingModel = banglaDubbingModelArrayList.get(position);

        String serialName = banglaDubbingModel.getSerialName();
        String ImgUrl = banglaDubbingModel.getImgUrl();
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
        return banglaDubbingModelArrayList.size();
    }

    public static class ViewHoldder extends RecyclerView.ViewHolder {

        ImageView serialImg;
        TextView serialName;
        public ViewHoldder(@NonNull View itemView) {
            super(itemView);

            serialImg=itemView.findViewById(R.id.serialImg);
            serialName=itemView.findViewById(R.id.serialName);
        }
    }
}
