package usth.edu.vn.soundcloudplayer;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Khoa on 23-Mar-16.
 */
public interface SCService {
    @GET("/tracks?client_id=" + Config.CLIENT_ID)
    public void getRecentTrack(@Query("create_at[from]") String date, Callback<List<Track>> cb);
}
