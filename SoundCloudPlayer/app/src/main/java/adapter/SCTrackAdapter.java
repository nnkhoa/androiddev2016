package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import usth.edu.vn.soundcloudplayer.R;
import usth.edu.vn.soundcloudplayer.Track;

/**
 * Created by Khoa on 24-Mar-16.
 */
public class SCTrackAdapter extends BaseAdapter {

    private Context scContext;
    private List<Track> scTracks;

    public SCTrackAdapter(Context context, List<Track> Tracks){
        scContext = context;
        scTracks = Tracks;
    }

    @Override
    public int getCount() {
        return scTracks.size();
    }

    @Override
    public Track getItem(int position) {
        return scTracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Track track = getItem(position);

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(scContext).inflate(R.layout.track_list, parent, false);

            holder = new ViewHolder();
            holder.trackImageView = (ImageView) convertView.findViewById(R.id.track_image);
            holder.trackNameView = (TextView) convertView.findViewById(R.id.track_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.trackNameView.setText(track.getScTitle());
        Picasso.with(scContext).load(track.getScArtworkURL()).into(holder.trackImageView);

        return convertView;
    }

    static class ViewHolder{
        ImageView trackImageView;
        TextView trackNameView;
    }
}
