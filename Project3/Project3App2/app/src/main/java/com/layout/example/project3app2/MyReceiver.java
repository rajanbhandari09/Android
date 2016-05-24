package com.layout.example.project3app2;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by USER on 3/9/2016.
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("com.layout.example.project3.Receiver.Chicago")) {
            intent = new Intent(context, ActivityPlacesOfInterestChicago.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
else if(intent.getAction().equals("com.layout.example.project3.Receiver.IndianaPolis")){
            intent = new Intent(context, ActivityPlacesOfInterestIndianaPolis.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }




    }
}
