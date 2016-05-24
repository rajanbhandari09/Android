package com.layout.example.project3app2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by USER on 3/9/2016.
 */
public class FragmentWebPage extends Fragment{
    //index to keep track of current webpage in list of webpages
private int index =-1;
    private WebView webpage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //retain fragment instance when device configuration is changed
        setRetainInstance(true);
    }
//method to return current webpage index
    public int getIndex(){
    return index;
}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_layout,container,false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webpage = (WebView)getActivity().findViewById(R.id.webpage);
        /*webpage.getSettings().setJavaScriptEnabled(true);
        webpage.getSettings().setDomStorageEnabled(true);*/

//to handle webpages inside the webview, define WebViewClient
        webpage.setWebViewClient(new WebViewClient());
//reloading webpage in case activity is destroyed due to device configuration change
        if(this.index!=-1){
            //re-using fragment for ActivityPlacesOfInterestChicago
            if(getActivity().getClass().toString().equals("class com.layout.example.project3app2.ActivityPlacesOfInterestChicago")) {
                webpage.loadUrl(ActivityPlacesOfInterestChicago.placesofinterestwebsites[index]);
            }
            //re-using fragment for ActivityPlacesOfInterestIndianaPolis
            else if(getActivity().getClass().toString().equals("class com.layout.example.project3app2.ActivityPlacesOfInterestIndianaPolis")){

                webpage.loadUrl(ActivityPlacesOfInterestIndianaPolis.placesofinterestwebsites[index]);

            }

        }

    }
//method to display webpage at list index
    public void showWebPageAtIndex(int index){
        this.index = index;
//re-using fragment for ActivityPlacesOfInterestChicago
        if(getActivity().getClass().toString().equals("class com.layout.example.project3app2.ActivityPlacesOfInterestChicago")) {

            webpage.loadUrl(ActivityPlacesOfInterestChicago.placesofinterestwebsites[index]);
        }

        //re-using fragment for ActivityPlacesOfInterestIndianaPolis
        else if(getActivity().getClass().toString().equals("class com.layout.example.project3app2.ActivityPlacesOfInterestIndianaPolis")){

            webpage.loadUrl(ActivityPlacesOfInterestIndianaPolis.placesofinterestwebsites[index]);

        }
    }

}
