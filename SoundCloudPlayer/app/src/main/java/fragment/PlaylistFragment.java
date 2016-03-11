package fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import usth.edu.vn.soundcloudplayer.R;

/**
 * Created by Khoa on 10-Mar-16.
 */
public class PlaylistFragment extends Fragment {

    public PlaylistFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_playlist, null);
        return v;
    }
}
