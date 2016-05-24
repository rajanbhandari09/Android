package com.service.example.playerclient;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

public class TransactionsActivity extends ListActivity {
private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Set the adapter with list of transactions retrieved in PlayerClientActivity
        mAdapter = new ArrayAdapter(this,R.layout.list_item_record,PlayerClientActivity.records);
setListAdapter(mAdapter);

    }

}
