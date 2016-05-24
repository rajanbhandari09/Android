    package com.intent.example.project1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText inputtext;
    //requestCode for startActivityForResult() method
    final int SEND_MESSAGE_REQUEST = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button pressme = (Button) findViewById(R.id.button);
        pressme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkToStartActivity();

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
//method to check if user input contains embedded mobile number in (xxx) yy-zzzz or (xxx)yy-zzz format
    public void checkToStartActivity(){

        inputtext = (EditText) findViewById(R.id.editText);
        String input = inputtext.getText().toString();
        //1st mobile number format (xxx)yy-zzz
        Pattern mobilepattern1 = Pattern.compile("\\(\\d\\d\\d\\)\\d\\d\\d-\\d\\d\\d\\d");
        //2nd mobile number format (xxx) yy-zzz
        Pattern mobilepattern2 = Pattern.compile("\\(\\d\\d\\d\\)\\s\\d\\d\\d-\\d\\d\\d\\d");
        Matcher match1 = mobilepattern1.matcher(input);
        Matcher match2 = mobilepattern2.matcher(input);

        if(match1.find()){
            String mobilenumber = "sms:"+match1.group();
            Uri data = Uri.parse(mobilenumber);
            Intent request = new Intent(Intent.ACTION_VIEW);

            request.setData(data);

            //startActivity(request);
            startActivityForResult(request, SEND_MESSAGE_REQUEST);

        }
        else if(match2.find()){
            String mobilenumber = "sms:"+match2.group();
            Uri data = Uri.parse(mobilenumber);
            Intent request = new Intent(Intent.ACTION_VIEW);
            request.setData(data);
            //startActivity(request);
            startActivityForResult(request, SEND_MESSAGE_REQUEST);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(requestCode==SEND_MESSAGE_REQUEST){

                        //below message is displayed when message activity is stopped and user returns to main activity
                        inputtext.setText("Returning from Compose Message...");

        }
    }

    /*@Override
    protected void onRestart() {
        super.onRestart();
inputtext.setText("Returning from Compose Message...");
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.intent.example.project1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.intent.example.project1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
