// AudioServerServices.aidl
package com.service.example.audioservice;

// Declare any non-default types here with import statements

interface AudioServerServices {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */


void playSong(int songnumber);
void pauseSong();
void resumeSong();
void stopPlayer();
List retrieveList();

}
