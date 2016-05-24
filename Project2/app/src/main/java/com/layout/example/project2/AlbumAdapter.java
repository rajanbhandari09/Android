package com.layout.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 2/15/2016.
 */
public class AlbumAdapter extends BaseAdapter {
    private List<Album> adapteralbums;
    private Context mcont;


//AlbumAdapter constructor using activity and list of album objects as input parameters
    public AlbumAdapter(Context app, List<Album> albums){

this.adapteralbums = albums;
        this.mcont = app;

    }

    @Override
    public int getCount() {
        return adapteralbums.size();
    }

    @Override
    public Object getItem(int position) {
        return adapteralbums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mcont);
        //recycling view
        if(convertView==null)
            convertView = inflater.inflate(R.layout.album_layout,parent,false);
        //retrieving textviews and imageview defined in album_layout file
        TextView song  = (TextView) convertView.findViewById(R.id.song);
        TextView artist  = (TextView) convertView.findViewById(R.id.artist);
        ImageView albumimage = (ImageView)convertView.findViewById(R.id.albumimage);
        //setting values for textviews
        song.setText((CharSequence) adapteralbums.get(position).getSong());
        artist.setText((CharSequence) adapteralbums.get(position).getArtist());
        //scaling imageview and setting image
        albumimage.setAdjustViewBounds(true);
        albumimage.setMaxHeight(250);
        albumimage.setMaxWidth(250);
        albumimage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        albumimage.setImageResource(adapteralbums.get(position).getImage());

        return convertView;
    }
}
