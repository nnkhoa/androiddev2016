package usth.edu.vn.soundcloudplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.ListViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView scMenuDrawer;
    private ArrayAdapter<String> scAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scMenuDrawer = (ListView)findViewById(R.id.menu_nav);

        addMenuItem();

//        Toolbar sideMenu = (Toolbar)findViewById(R.id.sidemenu);
//        setSupportActionBar(sideMenu);

//        mMediaPlayer = new MediaPlayer();
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


    }

    private void addMenuItem(){
        String menuItemArray[] = {"Home", "Playlist", "Setting"};
        scAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItemArray);
        scMenuDrawer.setAdapter(scAdapter);
    }
}
