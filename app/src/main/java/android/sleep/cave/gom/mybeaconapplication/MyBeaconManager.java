package android.sleep.cave.gom.mybeaconapplication;

import android.content.Context;

import com.estimote.sdk.BeaconManager;

/**
 * Created by sleepbear on 2016. 5. 10..
 */
public class MyBeaconManager {
    private static BeaconManager beaconManager;

    private MyBeaconManager() {}

    public static BeaconManager getInstance(Context context) {
        if (beaconManager == null) {
            beaconManager = new BeaconManager(context);
        }
        return beaconManager;
    }
}
