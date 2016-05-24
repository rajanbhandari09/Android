package com.layout.example.project3app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button buttonA;
   private Button buttonB;
    //Action for starting activity for places of interest in Chicago
    private static final String ACTION_1 = "com.layout.example.project3.Receiver.Chicago";
    //Action for starting activity for places of interest in Chicago
    private static final String ACTION_2 = "com.layout.example.project3.Receiver.IndianaPolis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonA = (Button)findViewById(R.id.ButtonA);
        buttonB = (Button)findViewById(R.id.ButtonB);
buttonA.setOnClickListener(new View.OnClickListener() {

    public void onClick(View v) {
        /*Button A on clicking displays short toast message for chicago and then starts activity for places of interest in Chicago
        */
        Toast.makeText(v.getContext(), "Broadcast for Chicago city in action...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(ACTION_1);
        sendBroadcast(intent);

    }


});

        buttonB.setOnClickListener(new View.OnClickListener(){
            /*Button B on clicking displays short toast message for IndianaPolis and then starts activity for places of interest in IndianaPolis
                    */
            public void onClick(View v){
                Toast.makeText(v.getContext(),"Broadcast for IndianaPolis city in action...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ACTION_2);
                sendBroadcast(intent);

            }


        });


    }


}
