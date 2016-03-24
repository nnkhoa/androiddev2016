package usth.edu.vn.soundcloudplayer;

import retrofit.RestAdapter;

/**
 * Created by Khoa on 24-Mar-16.
 */
public class SoundCloud {

    private final static RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(Config.API_URL).build();
    private final static SCService SERVICE = REST_ADAPTER.create(SCService.class);

    public static SCService getService(){
        return SERVICE;
    }
}
