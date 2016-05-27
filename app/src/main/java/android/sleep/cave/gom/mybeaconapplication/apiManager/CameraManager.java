package android.sleep.cave.gom.mybeaconapplication.apiManager;

import android.sleep.cave.gom.mybeaconapplication.apiService.CameraService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by sleepbear on 2016. 5. 10..
 */
public class CameraManager {

    private final CameraService cameraService;

    public CameraManager() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://117.17.102.158:8000/")
                .addConverterFactory(GsonConverterFactory.create()) //Json Parser 추가
                .build();

        cameraService = build.create(CameraService.class);
    }

    public void cameraShot() {
        cameraService.cameraShot();
    }
}
