package android.sleep.cave.gom.mybeaconapplication.apiService;

import android.sleep.cave.gom.mybeaconapplication.model.Place;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by sleepbear on 2016. 5. 16..
 */
public interface PlaceService {

    @GET("place")
    Observable<List<Place>> fetchPlaces();
}
