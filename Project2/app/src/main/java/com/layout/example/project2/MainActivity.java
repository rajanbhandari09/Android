package com.layout.example.project2;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {
    //List of songs video url
    private List<Uri> mUriSongs = new ArrayList<Uri>();
    //List of songs wiki url
    private List<Uri> mUriSongsWiki = new ArrayList<Uri>();
    //List of artist or band wiki url
    private List<Uri> mUriArtistBandWiki = new ArrayList<Uri>();

   // private List<String> songs = new ArrayList<String>();
   private List<String> songs;
    private List<String> artists = new ArrayList<String>();
    private List<Integer> images = new ArrayList<Integer>();
    //Album object stores song, artist and image resource information. Defined as separate class.
    private List<Album> Albums = new ArrayList<Album>();

    private ListView listview;
private AlbumAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

songs = Arrays.asList(getResources().getStringArray(R.array.songs));
        artists = Arrays.asList(getResources().getStringArray(R.array.artists));
images = Arrays.asList(R.drawable.please_forgive_me,R.drawable.numb,R.drawable.nothing_else_matters,R.drawable.hotel_california,R.drawable.animals,R.drawable.sorry,R.drawable.misirlou);

        //creating album objects list to be later passed into adapter
        for (int i = 0; i <songs.size() ; i++) {
            Album album = new Album();
            album.setSong(songs.get(i));
            album.setArtist(artists.get(i));
            album.setImage(images.get(i));
            Albums.add(album);

        }
//initializing uris for songs
        initializeUriSongs();
        //initializing uris for songs wiki pages
        initializeUriSongsWiki();
        //initializing uris for artist or band wiki pages
        initializeUriArtistBandWiki();
        //AlbumAdapter defined as separate class that extends BaseAdapter
         mAdapter = new AlbumAdapter(this, Albums);
        listview = getListView();
        listview.setAdapter(mAdapter);


        //Creating a listener object for each item click in listview

        ListView.OnItemClickListener click = new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri data = mUriSongs.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW,data);

                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(intent);
            }
        };

        //adding listener object to listview
        listview.setOnItemClickListener(click);

//registering listview for context menu
        registerForContextMenu(listview);
    }

    //creating uri list for songs wiki pages
    private void initializeUriSongsWiki() {
mUriSongsWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Please_Forgive_Me"));
        mUriSongsWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Numb_(Linkin_Park_song)"));
        mUriSongsWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Nothing_Else_Matters"));
        mUriSongsWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Hotel_California"));
        mUriSongsWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Animals_(Maroon_5_song)"));
        mUriSongsWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Sorry_(Justin_Bieber_song)"));
        mUriSongsWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Misirlou"));
    }

    //creating uri list for artist/band wiki pages
    private void initializeUriArtistBandWiki() {
mUriArtistBandWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Bryan_Adams"));
        mUriArtistBandWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Linkin_Park"));
        mUriArtistBandWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Metallica"));
        mUriArtistBandWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Eagles_(band)"));
        mUriArtistBandWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Maroon_5"));
        mUriArtistBandWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Justin_Bieber"));
        mUriArtistBandWiki.add(Uri.parse("https://en.wikipedia.org/wiki/Dick_Dale"));

    }

    //creating uri list for songs video
    private void initializeUriSongs() {
        mUriSongs.add(Uri.parse("https://www.youtube.com/watch?v=9EHAo6rEuas"));
        mUriSongs.add(Uri.parse("https://www.youtube.com/watch?v=kXYiU_JCYtU"));
        mUriSongs.add(Uri.parse("https://www.youtube.com/watch?v=Tj75Arhq5ho"));
        mUriSongs.add(Uri.parse("https://www.youtube.com/watch?v=lrfhf1Gv4Tw"));
        mUriSongs.add(Uri.parse("https://www.youtube.com/watch?v=qpgTC9MDx1o"));
        mUriSongs.add(Uri.parse("https://www.youtube.com/watch?v=fRh_vgS2dFE"));
        mUriSongs.add(Uri.parse("https://www.youtube.com/watch?v=lRH_70_Foow"));
    }
//Implementing onCreateContextMenu method in activity
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }

    //Matching layout menu item against menu item selected in context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {

            case R.id.video_clip:
                launchVideo(info);
                return true;
            case R.id.song_wiki:
                launchSongWiki(info);
                return true;
            case R.id.artist_band_wiki:
                launchArtistBandWiki(info);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
//launching artist/band wiki page when option view artist/band wiki page is selected from contextmenu
    private void launchArtistBandWiki(AdapterView.AdapterContextMenuInfo info) {
        Intent intent = new Intent(Intent.ACTION_VIEW, mUriArtistBandWiki.get(info.position));
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        startActivity(intent);

    }
    //launching song wiki page when option view song wiki page is selected from contextmenu
    private void launchSongWiki(AdapterView.AdapterContextMenuInfo info) {
        Intent intent = new Intent(Intent.ACTION_VIEW, mUriSongsWiki.get(info.position));
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        startActivity(intent);

    }
    //launching song video page when option view song video is selected from contextmenu
    private void launchVideo(AdapterView.AdapterContextMenuInfo info) {
        Intent intent = new Intent(Intent.ACTION_VIEW, mUriSongs.get(info.position));
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        startActivity(intent);

    }



}
