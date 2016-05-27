package android.sleep.cave.gom.mybeaconapplication.model;

import com.google.gson.annotations.Expose;

/**
 * Created by sleepbear on 2016. 5. 23..
 */
public class Picture {

    @Expose
    private long id;

    @Expose
    private long placeId;

    @Expose
    private String image;

    @Expose
    private String userDeviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(String userDeviceId) {
        this.userDeviceId = userDeviceId;
    }
}
