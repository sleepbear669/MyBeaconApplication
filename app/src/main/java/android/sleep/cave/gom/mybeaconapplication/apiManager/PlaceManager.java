package android.sleep.cave.gom.mybeaconapplication.apiManager;

import android.sleep.cave.gom.mybeaconapplication.apiService.PlaceService;
import android.sleep.cave.gom.mybeaconapplication.model.Place;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sleepbear on 2016. 5. 16..
 */
public class PlaceManager {

    private final PlaceService placeService;

    public PlaceManager() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://117.17.102.241:8080/")
                .addConverterFactory(GsonConverterFactory.create()) //Json Parser 추가
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.placeService = build.create(PlaceService.class);
    }

    public Observable<List<Place>> getPlaceList() {
        return placeService
                .fetchPlaces()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
