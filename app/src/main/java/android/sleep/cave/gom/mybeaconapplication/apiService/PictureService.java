package android.sleep.cave.gom.mybeaconapplication.apiService;


import android.sleep.cave.gom.mybeaconapplication.model.Picture;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by sleepbear on 2016. 5. 23..
 */
public interface PictureService {

    @GET("picture/{userDeviceId}/{placeId}")
    Observable<List<Picture>> fetchPicture(@Path("userDeviceId") String userDeviceId, @Path("placeId") long placeId);
}
