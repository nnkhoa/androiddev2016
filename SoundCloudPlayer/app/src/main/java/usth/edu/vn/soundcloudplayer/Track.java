package usth.edu.vn.soundcloudplayer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Khoa on 23-Mar-16.
 */
public class Track {
    @SerializedName("title")
    private String scTitle;

    @SerializedName("id")
    private int scID;

    @SerializedName("stream_url")
    private String scStreamURL;

    @SerializedName("artwork_url")
    private String scArtworkURL;

    public String getScTitle() {
        return scTitle;
    }

    public int getScID() {
        return scID;
    }

    public String getScStreamURL() {
        return scStreamURL;
    }

    public String getScArtworkURL() {
        return scArtworkURL;
    }
}
