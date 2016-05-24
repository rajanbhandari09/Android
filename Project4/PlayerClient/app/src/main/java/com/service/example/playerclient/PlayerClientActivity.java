package com.service.example.playerclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.service.example.audioservice.AudioServerServices;

import java.util.ArrayList;
import java.util.List;

public class PlayerClientActivity extends Activity {
    /*
    * Media PlayerClient UI elements
    * */
    private EditText songnumber;
private Button playsong;
    private Button pausesong;
    private Button resumesong;
    private Button stopplayer;
    private Button retrievetransactions;
    //Variable to check if client is bound to server
private boolean mConnected=false;
    //Interface from AIDL file
    private AudioServerServices mAudioServerServices = null;
    //List to hold transactions returned from SQLite database
    public static List<String> records = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_client);
        songnumber = (EditText)findViewById(R.id.songnumber);
        playsong = (Button)findViewById(R.id.playsong);
        pausesong = (Button)findViewById(R.id.pause);
        resumesong = (Button)findViewById(R.id.resume);
        stopplayer = (Button)findViewById(R.id.stop);
        retrievetransactions = (Button)findViewById(R.id.retrieve);

        //Setting up a listener for Play button
        playsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //songnumber field should not be empty
                if(!songnumber.getText().toString().equals("")) {
                    //songnumber field should hold value between 1 and 4
                    if (Integer.parseInt(songnumber.getText().toString()) >= 1 && Integer.parseInt(songnumber.getText().toString()) <= 4) {
                        try {
                            //API method playSong() is invoked when user clicks on Play button after entering song from 1 to 4
                            mAudioServerServices.playSong(Integer.parseInt(songnumber.getText().toString()));

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
//States of buttons after Play button is selected
                        pausesong.setEnabled(true);
                        resumesong.setEnabled(false);
                        stopplayer.setEnabled(true);
                        retrievetransactions.setEnabled(true);
                    }
                }
            }
        });
        //Setting up listener for Pause button
        pausesong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    //API method pauseSong() is invoked when user clicks on Pause button
                    mAudioServerServices.pauseSong();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //State of other buttons when Pause button is clicked
                pausesong.setEnabled(false);
                resumesong.setEnabled(true);
            }
        });
        //Setting up listener for Resume button
        resumesong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //API method resumeSong() is invoked when user clicks on Resume button
                    mAudioServerServices.resumeSong();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                //State of other buttons when Resume button is clicked
                resumesong.setEnabled(false);
                pausesong.setEnabled(true);
            }
        });
        //Setting up listener for Stop button
        stopplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //API method stopPlayer() is invoked when user clicks on Stop button
                    mAudioServerServices.stopPlayer();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                //state of other buttons when Stop button is clicked
                stopplayer.setEnabled(false);
                playsong.setEnabled(true);
                pausesong.setEnabled(false);
                resumesong.setEnabled(false);
            }
        });

        //setting up listener for retrieve transactions button
retrievetransactions.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v) {
        try {
            //API method retrieveList() is invoked when user clicks on retrieve transactions button
            records = mAudioServerServices.retrieveList();
            Log.i("Service Result:",records.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //Clicking on retrieve transactions button starts a new activity
        Intent intent = new Intent(getApplicationContext(),TransactionsActivity.class);
        startActivity(intent);
    }
});
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if the client is already not bound to server, then create a bind service when activity is resumed
        if(!mConnected){
            boolean result = false;

            //Methods for communication among applications in different packages
            Intent intent = new Intent(AudioServerServices.class.getName());
            ResolveInfo info = getPackageManager().resolveService(intent, Context.BIND_AUTO_CREATE);
            intent.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
            //Try to establish a bind connection to a service
            result = bindService(intent,mConnection,BIND_AUTO_CREATE);
            if(result){
                Log.i("connection status","success");
            }
            else{
                Log.i("connection status","fail");
            }
        }

    }

    //Create a ServiceConnection object to handle client server connection and return IBinder object when connection is established
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//TypeCast IBinder object to AIDL interface instance and set connected value to true
            mAudioServerServices = AudioServerServices.Stub.asInterface(service);
            mConnected = true;


        }
//release AIDL object and set connected to false when service is disconnected
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAudioServerServices = null;
            mConnected = false;
        }
    };

//unbind from service when activity is destroyed
    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();

    }
}
