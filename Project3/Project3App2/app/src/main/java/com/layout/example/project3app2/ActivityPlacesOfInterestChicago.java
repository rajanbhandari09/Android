package com.layout.example.project3app2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ActivityPlacesOfInterestChicago extends AppCompatActivity implements FragmentPlacesOfInterest.ListSelectionListener {


//Array of strings to hold places of interest in Chicago
    public static String[] placesofinterest;
    //Array of strings to hold websites for places of interest in Chicago
    public static String[] placesofinterestwebsites;
    private FragmentManager mFragmentManager;
    //Frame to hold FragmentPlacesOfInterest
    private FrameLayout placesofinterestframe;
    //Frame to hold FragmentWebPage
    private FrameLayout webpageframe;
    //Fragment instance for FragmentPlacesOfInterest
    FragmentPlacesOfInterest mfragmentPlacesOfInterest;
    //Fragment instance for FragmentWebPage
    FragmentWebPage mfragmentwebpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //Adding Toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        placesofinterest = getResources().getStringArray(R.array.chicagoplacesofinterest);
        placesofinterestwebsites = getResources().getStringArray(R.array.chicagoplacesofinterestwebsites);
        placesofinterestframe = (FrameLayout)findViewById(R.id.placesofinterest);
        webpageframe = (FrameLayout)findViewById(R.id.website);

        mFragmentManager = getFragmentManager();
//If instances for FragmentPlacesOfInterest and FragmentWebPage are present, retrieve them using FragmentManager.
// This may happen because activity was restarted due device configuration change but the fragments were still retained due to setRetainInstance set to true
mfragmentPlacesOfInterest = (FragmentPlacesOfInterest)mFragmentManager.findFragmentByTag("placesofinterestChicago");
        mfragmentwebpage = (FragmentWebPage)mFragmentManager.findFragmentByTag("webpageChicago");

        //Actions when current device configuration is set to Portrait
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            //When the activity is launched for the first time
if (mfragmentPlacesOfInterest==null&&mfragmentwebpage==null){
    mfragmentPlacesOfInterest = new FragmentPlacesOfInterest();
    mfragmentwebpage = new FragmentWebPage();
    FragmentTransaction transaction = mFragmentManager.beginTransaction();
    transaction.add(R.id.placesofinterest, mfragmentPlacesOfInterest, "placesofinterestChicago");
    transaction.commit();
    mFragmentManager.executePendingTransactions();
}

//When the device was in landscape mode with only places of interest fragment displaying and then device configuration changed to portrait mode
         else if(mfragmentPlacesOfInterest!=null&&mfragmentwebpage==null)   {
    mfragmentwebpage = new FragmentWebPage();
    placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1));
    webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0));

    }

//When the device was in landscape mode with both fragments displaying and then device configuration changed to portrait mode
else if(mfragmentPlacesOfInterest!=null&&mfragmentwebpage!=null){
    placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,0));
    webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

}

            //defining backstackchange listener when device is in portrait mode
            mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener(){
                @Override
                public void onBackStackChanged() {
                    //re-setting layout in portrait mode whenever fragment backstack change occurs
                    setPortraitLayout();
                }
            });


        }

        //Actions when current device configuration is set to Landscape mode
        else if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
//When the activity is launched for the first time
            if (mfragmentPlacesOfInterest==null&&mfragmentwebpage==null){
                mfragmentPlacesOfInterest = new FragmentPlacesOfInterest();
                mfragmentwebpage = new FragmentWebPage();
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.add(R.id.placesofinterest, mfragmentPlacesOfInterest, "placesofinterestChicago");
                transaction.commit();
                mFragmentManager.executePendingTransactions();
            }

            //When the device was in portrait mode with only places of interest fragment displaying and then device configuration changed to landscape mode
            else if (mfragmentPlacesOfInterest!=null&&mfragmentwebpage==null)   {
                mfragmentwebpage = new FragmentWebPage();
                placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1));
                webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0));

            }
//When the device was in portrait mode with both fragments displaying and then device configuration changed to landscape mode
            else if(mfragmentPlacesOfInterest!=null&&mfragmentwebpage!=null){
                placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
                webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2f));


            }
//defining backstackchange listener when device is in landscape mode
            mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener(){
                @Override
                public void onBackStackChanged() {
                    //re-setting layout in landscape mode whenever fragment backstack change occurs
                    setLandScapeLayout();
                }
            });

        }






    }

    //method defining re-setting of layout in landscape orientation, whenever a fragment backstack change occurs
    public void setLandScapeLayout(){
        if(!mfragmentwebpage.isAdded()){
            placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1));
            webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,0));
        }

        else{
            placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f));
            webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,2f));
        }

    }
    //method defining re-setting of layout in portrait orientation, whenever a fragment backstack change occurs
public void setPortraitLayout(){

    if(!mfragmentwebpage.isAdded()){
        placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1));
        webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,0));
    }

else{
        placesofinterestframe.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,0));
        webpageframe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1));
    }

}
    //method defining actions whenever a listitem in FragmentPlacesOfInterest is clicked
    @Override
    public void onListSelection(int index) {
        if(!mfragmentwebpage.isAdded()){
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.website, mfragmentwebpage,"webpageChicago");
            transaction.addToBackStack(null);
            transaction.commit();
            mFragmentManager.executePendingTransactions();

        }

        if(mfragmentwebpage.getIndex()!=index){
            mfragmentwebpage.showWebPageAtIndex(index);

        }




    }
//method defining actions whenever back button is pressed on device
    @Override
    public void onBackPressed() {
        if(mFragmentManager.getBackStackEntryCount()>0){
            mFragmentManager.popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
//creating options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//defining actions whenever an options menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_chicago) {

            return true;
        }
else if(id == R.id.action_indianapolis){
            Intent intent = new Intent(this,ActivityPlacesOfInterestIndianaPolis.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
