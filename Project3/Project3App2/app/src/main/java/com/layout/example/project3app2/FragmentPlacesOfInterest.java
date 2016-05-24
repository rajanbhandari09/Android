package com.layout.example.project3app2;

import android.app.Activity;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by USER on 3/9/2016.
 */
public class FragmentPlacesOfInterest extends ListFragment{

    private ListSelectionListener mListener;
    //variable used to hold list index position
    private int index =-1;

    //define interface for interaction with the activity
public interface ListSelectionListener{
    public void onListSelection(int index);
}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //In order to re-use fragment across 2 different activities, resolve based on the class of calling activity
       if(getActivity().getClass().toString().equals("class com.layout.example.project3app2.ActivityPlacesOfInterestChicago")) {
           //In case fragment is re-created due to device configuration change, set list item as checked
           if(index!=-1) {
               getListView().setItemChecked(index, true);


           }
            setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, ActivityPlacesOfInterestChicago.placesofinterest));

       }
       //re-use fragment for ActivityPlacesOfInterestIndianaPolis
        else if(getActivity().getClass().toString().equals("class com.layout.example.project3app2.ActivityPlacesOfInterestIndianaPolis")) {
           //In case fragment is re-created due to device configuration change, set list item as checked
           if(index!=-1)
           getListView().setItemChecked(index, true);
           setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, ActivityPlacesOfInterestIndianaPolis.placesofinterest));

        }





    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //retain fragment instance in case of device configuration changes
        setRetainInstance(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        index = position;
        l.setItemChecked(position,true);
        mListener.onListSelection(position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //assign activity as listener to the fragment
            mListener = (ListSelectionListener) activity;
        }
        catch (ClassCastException e){
            e.printStackTrace();

        }
    }
}
