package android.sleep.cave.gom.mybeaconapplication.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.sleep.cave.gom.mybeaconapplication.MyApplication;
import android.sleep.cave.gom.mybeaconapplication.R;
import android.sleep.cave.gom.mybeaconapplication.fragment.PictureListFragment;
import android.sleep.cave.gom.mybeaconapplication.fragment.PlaceFragment;
import android.sleep.cave.gom.mybeaconapplication.model.Place;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class MainActivity extends Activity implements PlaceFragment.SelectPlaceListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.logout).setOnClickListener(v -> {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {
                    final Intent intent = new Intent(MyApplication.getGlobalApplicationContext(), KakaoLoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }
            });
        });
        getFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment, new PlaceFragment())
                .commit();

    }

    @Override
    public void onPlaceSelected(Place place) {
        PictureListFragment pictureListFragment = new PictureListFragment();
        Bundle bundle = new Bundle();
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.main_fragment);
        bundle.putLong(PictureListFragment.PLACE_ID, place.getId());
        pictureListFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .remove(currentFragment)
                .replace(R.id.main_fragment, pictureListFragment)
                .addToBackStack(null)
                .commit();
    }
}
