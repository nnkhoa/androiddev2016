package adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.FollowingFragment;
import fragment.LikeFragment;
import fragment.PlaylistFragment;
import usth.edu.vn.soundcloudplayer.PlaylistActivity;
import usth.edu.vn.soundcloudplayer.R;

/**
 * Created by Khoa on 11-Mar-16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context c;

    final int PAGE_COUNT = 3;

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PlaylistFragment();

            case 1:
                return new LikeFragment();

            case 2:
                return new FollowingFragment();
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Playlist";

            case 1:
                return "Like";

            case 2:
                return "Following";
        }

        return null;
    }
}
