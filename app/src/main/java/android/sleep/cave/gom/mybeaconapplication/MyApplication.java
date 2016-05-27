package android.sleep.cave.gom.mybeaconapplication;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.sleep.cave.gom.mybeaconapplication.activity.KakaoSDKAdapter;
import android.sleep.cave.gom.mybeaconapplication.activity.MainActivity;
import android.sleep.cave.gom.mybeaconapplication.service.BeaconCameraService;

import com.android.volley.toolbox.ImageLoader;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Region;
import com.kakao.auth.KakaoSDK;

import java.util.List;
import java.util.UUID;

/**
 * Created by sleepbear on 2016. 5. 10..
 */
public class MyApplication extends Application {
    private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);
    private static final Region MY_REGION = new Region("ranged region"
            ,UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D")
            ,null
            ,null);
    static public BeaconManager beaconManager;
    private static Context context;
    public static String getDeviceId() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private static volatile MyApplication instance = null;
    private static volatile Activity currentActivity = null;
    private ImageLoader imageLoader;

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        MyApplication.currentActivity = currentActivity;
    }
    public static MyApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());


        // Initializes Estimote SDK with your App ID and App Token from Estimote Cloud.
        // You can find your App ID and App Token in the
        // Apps section of the Estimote Cloud (http://cloud.estimote.com).
        EstimoteSDK.initialize(this, "sleepbear669-gmail-com-s-y-hip", "7b745fa86614d91d3085ec8dd6f28108");

        // Configure verbose debug logging.
        EstimoteSDK.enableDebugLogging(true);
        final Region region = new Region("monitored region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
        // this is were we left off:
        beaconManager = MyBeaconManager.getInstance(getApplicationContext());
// add this below:
        beaconManager.connect(() -> beaconManager.startMonitoring(ALL_ESTIMOTE_BEACONS_REGION));

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                beaconManager.startRanging(MY_REGION);
                showNotification(
                        "Your gate closes in 47 minutes.",
                        "Current security wait time is 15 minutes, "
                                + "and it's a 5 minute walk from security to the gate. "
                                + "Looks like you've got plenty of time!");
            }
            @Override
            public void onExitedRegion(Region region) {

                beaconManager.stopRanging(MY_REGION);
                showNotification(
                        "exit",
                        "Current security wait time is 15 minutes, "
                                + "and it's a 5 minute walk from security to the gate. "
                                + "Looks like you'e got plenty of time!");
            }
        });
        Intent Service = new Intent(this, BeaconCameraService.class);
        startService(Service);
    }
    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();
            notification.defaults |= Notification.DEFAULT_SOUND;
        }
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public static Context getContext() {
        return context;
    }
}
