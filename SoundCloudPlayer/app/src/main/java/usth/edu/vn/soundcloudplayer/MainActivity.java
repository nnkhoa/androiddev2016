package usth.edu.vn.soundcloudplayer;

import android.app.Activity;
import android.app.ActivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fragment.FollowingFragment;
import fragment.LikeFragment;
import fragment.MainFragment;
import fragment.PlaylistFragment;

public class MainActivity extends AppCompatActivity {

    private ListView scMenuDrawer;
    private String[] menuItemArray;
    private ArrayAdapter<String> scAdapter;
    private ActionBarDrawerToggle scDrawerToggle;
    private DrawerLayout scDrawerLayout;
    private CharSequence scActivityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scMenuDrawer = (ListView)findViewById(R.id.menu_nav);
        scDrawerLayout = (DrawerLayout)findViewById(R.id.menu_drawer);
        scActivityName = getTitle();
        Log.i("oncreate Title", scActivityName.toString());

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
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new PlaylistFragment();
                break;
            case 2:
                fragment = new LikeFragment();
                break;
            case 3:
                fragment = new FollowingFragment();
            default:
                break;
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainFrag, fragment).commit();

            scMenuDrawer.setItemChecked(position, true);
            scMenuDrawer.setSelection(position);
            setTitle(menuItemArray[position]);
            scDrawerLayout.closeDrawer(scMenuDrawer);
            Log.i("name", menuItemArray[position]);
        }else{
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

//    private void selectItem(int position){
//        switch(position){
//            case 0:
//                startActivity(new Intent(this, MainActivity.class));
//                break;
//            case 1:
//                startActivity(new Intent(this, PlaylistActivity.class));
//                break;
//            case 2:
//                startActivity(new Intent(this, LikeActivity.class));
//                break;
//            case 3:
//                startActivity(new Intent(this, FollowingActivity.class));
//                break;
//            case 4:
//                startActivity(new Intent(this, SettingActivity.class));
//            default:
//                break;
//        }
//
//    }

}
