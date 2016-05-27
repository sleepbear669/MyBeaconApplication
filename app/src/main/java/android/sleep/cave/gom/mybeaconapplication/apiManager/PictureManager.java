package android.sleep.cave.gom.mybeaconapplication.apiManager;

import android.sleep.cave.gom.mybeaconapplication.apiService.PictureService;
import android.sleep.cave.gom.mybeaconapplication.model.Picture;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sleepbear on 2016. 5. 23..
 */
public class PictureManager {

    private final PictureService pictureService;

    public PictureManager() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://117.17.102.241:8080/")
                .addConverterFactory(GsonConverterFactory.create()) //Json Parser 추가
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        pictureService = build.create(PictureService.class);
    }

    public Observable<List<Picture>> getPictureList(String userDeviceId, long placeId) {
        return pictureService
                .fetchPicture(userDeviceId, placeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
