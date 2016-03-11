package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.FollowingFragment;
import fragment.LikeFragment;
import fragment.PlaylistFragment;

/**
 * Created by Khoa on 11-Mar-16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
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
}
