package usth.edu.vn.soundcloudplayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import adapter.SCTrackAdapter;

import retrofit.Callback;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private ListView scMenuDrawer;
    private String[] menuItemArray;
    private ArrayAdapter<String> scAdapter;
    private ActionBarDrawerToggle scDrawerToggle;
    private DrawerLayout scDrawerLayout;
    private CharSequence scActivityName;
    private static final String TAG = "MainActivity";

    private List<Track> scListItems;
    private SCTrackAdapter scTrackAdapter;

    private TextView scPlayingTrackName;
    private ImageView scPlayingTrackImage;

    private MediaPlayer scMediaPlayer;
    private ImageView scPlayerControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scMediaPlayer = new MediaPlayer();
        scMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        scMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });

        scListItems = new ArrayList<Track>();
        ListView listView = (ListView)findViewById(R.id.track_list_view);
        scTrackAdapter = new SCTrackAdapter(this, scListItems);
        listView.setAdapter(scTrackAdapter);

        scPlayingTrackImage =(ImageView)findViewById(R.id.playing_track_image);
        scPlayingTrackName =(TextView)findViewById(R.id.playing_track_name);
        scPlayerControl = (ImageView)findViewById(R.id.player_control);

        scPlayerControl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });

        scMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                scPlayerControl.setImageResource(R.drawable.ic_play);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = scListItems.get(position);

                scPlayingTrackName.setText(track.getScTitle());
                Picasso.with(MainActivity.this).load(track.getScArtworkURL()).into(scPlayingTrackImage);

                if(scMediaPlayer.isPlaying()){
                    scMediaPlayer.stop();
                    scMediaPlayer.reset();
                }

                try{
                    scMediaPlayer.setDataSource(track.getScStreamURL() + "?client_id=" + Config.CLIENT_ID);
                    scMediaPlayer.prepareAsync();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        SCService scService = SoundCloud.getService();
        scService.getRecentTrack(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), new Callback<List<Track>>() {
            @Override
            public void success(List<Track> tracks, Response response) {
                loadTrack(tracks);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error " + error);
            }
        });

        scMenuDrawer = (ListView)findViewById(R.id.menu_nav);
        scDrawerLayout = (DrawerLayout)findViewById(R.id.menu_drawer);
        scActivityName = getTitle();
        Log.i("onCreate Title", scActivityName.toString());

        addMenuItem();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void addMenuItem(){
        menuItemArray = getResources().getStringArray(R.array.menuArray);
        scAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItemArray);
        scMenuDrawer.setAdapter(scAdapter);

        scMenuDrawer.setOnItemClickListener(new DrawerItemClickListener());
    }

    private void setupDrawer() {
        scDrawerToggle = new ActionBarDrawerToggle(this, scDrawerLayout, R.string.drawer_open, R.string.drawer_close){

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                scActivityName = getTitle();
                getSupportActionBar().setTitle(scActivityName);
                invalidateOptionsMenu();
                Log.i("name_on_close", getSupportActionBar().getTitle().toString());

            }
        };

        scDrawerToggle.setDrawerIndicatorEnabled(true);
        scDrawerLayout.addDrawerListener(scDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(scDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        scDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        scDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    private void selectItem(int position){
        switch(position){
            case 0:
                scDrawerLayout.closeDrawers();
                break;
            case 1:
                startActivity(new Intent(this, PlaylistActivity.class));
                break;
//            case 2:
//                startActivity(new Intent(this, LikeActivity.class));
//                break;
//            case 3:
//                startActivity(new Intent(this, FollowingActivity.class));
//                break;
//            case 4:
//                startActivity(new Intent(this, SettingActivity.class));
            default:
                break;
        }

    }

    private void loadTrack(List<Track> tracks){
        scListItems.clear();
        scListItems.addAll(tracks);
        scTrackAdapter.notifyDataSetChanged();
    }

    private void togglePlayPause(){
        if(scMediaPlayer.isPlaying()){
            scMediaPlayer.pause();
            scPlayerControl.setImageResource(R.drawable.ic_play);
        }else{
            scMediaPlayer.start();
            scPlayerControl.setImageResource(R.drawable.ic_pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(scMediaPlayer != null){
            if(scMediaPlayer.isPlaying()){
                scMediaPlayer.stop();
            }

            scMediaPlayer.release();
            scMediaPlayer = null;
        }
    }
}
