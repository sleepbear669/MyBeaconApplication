package android.sleep.cave.gom.mybeaconapplication.model;

import com.google.gson.annotations.Expose;

/**
 * Created by sleepbear on 2016. 5. 16..
 */

public class Place {

    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
