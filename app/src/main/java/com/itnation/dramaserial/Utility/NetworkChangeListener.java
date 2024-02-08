package com.itnation.dramaserial.Utility;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.itnation.dramaserial.R;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        if ( !common.isConnectedToInternet(context)){

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.no_internet, null);
            builder.setView(layout_dialog);

            AppCompatButton retry_button =layout_dialog.findViewById(R.id.retry_button);

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);


            dialog.getWindow().setGravity(Gravity.CENTER);


            retry_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                    onReceive(context, intent);


                }
            });


        }


    }
}
