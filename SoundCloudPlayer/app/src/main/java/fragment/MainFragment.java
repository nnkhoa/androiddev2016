package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Khoa on 07-Mar-16.
 */
public class MainFragment extends Fragment {

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView welcomeMess = new TextView(getActivity());

        welcomeMess.setText("Welcome To SoundCloud Player");
        welcomeMess.setBackgroundColor(0xFFE4733F);
        welcomeMess.setTextSize(30);

        return welcomeMess;
    }
}
