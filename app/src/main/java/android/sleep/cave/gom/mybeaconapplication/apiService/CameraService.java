package android.sleep.cave.gom.mybeaconapplication.apiService;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by sleepbear on 2016. 5. 10..
 */
public interface CameraService {
    @GET("cameraShot")
    Observable<String> cameraShot();
}
