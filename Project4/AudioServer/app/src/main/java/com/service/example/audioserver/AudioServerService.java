package com.service.example.audioserver;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.service.example.audioservice.AudioServerServices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AudioServerService extends Service {
    private List<String> songs = new ArrayList<String>();
    //MediaPlayer service instance
    private MediaPlayer mPlayer;
    //DatabaseOpenHelper and ContentValues instances to work with SQLitedatabase
    private DatabaseOpenHelper mDBhelper;
    private ContentValues mContentValues;
    private int currentsong;
    //previoussong and currentstate variables keep track of previous song number and current song number request
    private int previoussong=-1;
    private String currentstate ="Idle";
    //records list will keep list of all transactions in SQLite database
    private List records = new ArrayList();
    private String row;

    private final AudioServerServices.Stub mBinder = new AudioServerServices.Stub(){

        public void playSong(int songnumber){
            currentsong = songnumber;
            //Creating a database entry for Play song request
            mContentValues.put(DatabaseOpenHelper.DATE_TIME, Calendar.getInstance().getTime().toString());
            mContentValues.put(DatabaseOpenHelper.REQUEST_TYPE,"Play");
            mContentValues.put(DatabaseOpenHelper.CLIP_NUMBER, Integer.toString(currentsong));
            mContentValues.put(DatabaseOpenHelper.CURRENT_STATE,currentstate);
            currentstate = "Playing clip number "+Integer.toString(currentsong);
            //Song is played based on songnumber
            switch (songnumber) {
                case 1:
                    //If mediaplayer instance is not null for e.g. it is in paused, stopped or resumed state
                    if(mPlayer!=null) {
                        //If mediaplayer was already playing song other than song number 1
                        if(mPlayer.isPlaying()&&previoussong!=1){
                            mPlayer.reset();
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song1);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }
                        //If mediaplayer was already playing song number 1
                        else if(mPlayer.isPlaying()&&previoussong==1){
                            mPlayer.seekTo(0);
                        }
                        //If mediaplayer was not playing i.e. stopped
                        else{
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song1);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }


                    }
                    //If mediaplayer is not initialized
                    else {
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song1);
                        mPlayer.setLooping(false);
                        mPlayer.start();

                    }
                    break;
//Similar cases for song 2 as for song 1
                case 2:
                    if(mPlayer!=null) {
                        if(mPlayer.isPlaying()&&previoussong!=2){
                            mPlayer.reset();
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song2);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }
                        else if(mPlayer.isPlaying()&&previoussong==2){
                            mPlayer.seekTo(0);
                        }
                        else{
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song2);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }


                    }
                    else {
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song2);
                        mPlayer.setLooping(false);
                        mPlayer.start();

                    }
                    break;
                //Similar cases for song 3 as for song 1
                case 3:
                    if(mPlayer!=null) {
                        if(mPlayer.isPlaying()&&previoussong!=3){
                            mPlayer.reset();
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song3);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }
                        else if(mPlayer.isPlaying()&&previoussong==3){
                            mPlayer.seekTo(0);
                        }
                        else{
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song3);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }


                    }
                    else {
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song3);
                        mPlayer.setLooping(false);
                        mPlayer.start();

                    }
                    break;
                //Similar cases for song 4 as for song 1
                case 4:
                    if(mPlayer!=null) {
                        if(mPlayer.isPlaying()&&previoussong!=4){
                            mPlayer.reset();
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song4);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }
                        else if(mPlayer.isPlaying()&&previoussong==4){
                            mPlayer.seekTo(0);
                        }
                        else{
                            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song4);
                            mPlayer.setLooping(false);
                            mPlayer.start();
                        }


                    }
                    else {
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song4);
                        mPlayer.setLooping(false);
                        mPlayer.start();

                    }
                    break;

            }

            //inserting the Play request in database
            mDBhelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, mContentValues);
            mContentValues.clear();
            previoussong = currentsong;
        }
        public void pauseSong(){
            //Creating a Pause song entry in database
            mContentValues.put(DatabaseOpenHelper.DATE_TIME, Calendar.getInstance().getTime().toString());
            mContentValues.put(DatabaseOpenHelper.REQUEST_TYPE,"Pause");
            mContentValues.put(DatabaseOpenHelper.CLIP_NUMBER, Integer.toString(currentsong));
            mContentValues.put(DatabaseOpenHelper.CURRENT_STATE,currentstate);
            currentstate = "Paused while playing clip number "+Integer.toString(currentsong);

if(mPlayer!=null){
    //if media player is already playing then pause
    if(mPlayer.isPlaying()){
        mPlayer.pause();
    }
}
            //inserting Pause entry in database
            mDBhelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, mContentValues);
            mContentValues.clear();
        }
        public void resumeSong(){
            //Creating Resume song entry for media player
            mContentValues.put(DatabaseOpenHelper.DATE_TIME, Calendar.getInstance().getTime().toString());
            mContentValues.put(DatabaseOpenHelper.REQUEST_TYPE,"Resume");
            mContentValues.put(DatabaseOpenHelper.CLIP_NUMBER, Integer.toString(currentsong));
            mContentValues.put(DatabaseOpenHelper.CURRENT_STATE,currentstate);
            currentstate = "Resumed playing clip number "+Integer.toString(currentsong);
            if(mPlayer!=null){
                //if media player is not playing i.e. paused, start mediaplayer
                if(!mPlayer.isPlaying()){
                    mPlayer.start();
                }
            }
            mDBhelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, mContentValues);
            mContentValues.clear();
        }
        public void stopPlayer(){
            //creating stop song entry for media player
            mContentValues.put(DatabaseOpenHelper.DATE_TIME, Calendar.getInstance().getTime().toString());
            mContentValues.put(DatabaseOpenHelper.REQUEST_TYPE,"Stop");
            mContentValues.put(DatabaseOpenHelper.CLIP_NUMBER, Integer.toString(currentsong));
            mContentValues.put(DatabaseOpenHelper.CURRENT_STATE,currentstate);
            currentstate = "Stopped playing clip number "+Integer.toString(currentsong);
            if(mPlayer!=null){
                //if mediaplayer is playing, then stop mediaplayer
                if(mPlayer.isPlaying()){
                    mPlayer.stop();
                }
            }
            mDBhelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, mContentValues);
            mContentValues.clear();
        }
        public List retrieveList(){
            records.clear();
            //retrieving all records in SQLite database
            Cursor mCursor =
            mDBhelper.getWritableDatabase().query(DatabaseOpenHelper.TABLE_NAME,DatabaseOpenHelper.COLUMNS,null,new String[] {}, null, null,
                    null);
            while(mCursor.moveToNext()){
                row = "Date&Time: "+mCursor.getString(1)+"\n"+"RequestType: "+mCursor.getString(2)+"\n"+"ClipNumber: "+mCursor.getString(3)+"\n"+"CurrentStateWhenRequestReceived: "+mCursor.getString(4);
                records.add(row);
            }
            return records;
        }


    };

    @Override
    public IBinder onBind(Intent intent) {
        //Creating DatabaseOpenHelper and ContentValues instance when client binds to service
mDBhelper = new DatabaseOpenHelper(this);
mContentValues = new ContentValues();
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
//Closing and deleting database when client unbinds from service
        mDBhelper.getWritableDatabase().close();
        mDBhelper.deleteDatabase();
        //Releasing mediaplayer service when client unbinds from service
        if(mPlayer!=null) {
            mPlayer.release();
            mPlayer = null;
        }
        return super.onUnbind(intent);
    }
}
