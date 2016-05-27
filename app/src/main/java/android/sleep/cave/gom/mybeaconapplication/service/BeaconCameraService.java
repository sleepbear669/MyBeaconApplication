package android.sleep.cave.gom.mybeaconapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.Settings;
import android.sleep.cave.gom.mybeaconapplication.MyApplication;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by sleepbear on 2016. 5. 10..
 */
public class BeaconCameraService extends Service{

    private BeaconManager beaconManager;

    @Override
    public void onCreate() {
        super.onCreate();
        beaconManager = MyApplication.beaconManager;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("http://117.17.102.158:8000/cameraShot")
                .build();
        MyApplication.beaconManager.setRangingListener((region, list) -> {
//            AsyncTask<List<Beacon>, Void, Void> asyncTask = new AsyncTask<List<Beacon>, Void, Void>() {
//                @SafeVarargs
//                @Override
//                protected final Void doInBackground(List<Beacon>... params) {
//                    for (Beacon beacon : list) {
//                        if (Utils.computeAccuracy(beacon) < 2) {
//                            try {
//                                okHttpClient.newCall(build)
//                                        .execute();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    return null;
//                }
//            };
//
//            asyncTask.execute(list);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
