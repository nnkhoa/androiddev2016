package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import usth.edu.vn.soundcloudplayer.R;

/**
 * Created by Khoa on 11-Mar-16.
 */
public class LikeFragment extends Fragment {
    public LikeFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_like, null);
        return v;
    }
}
