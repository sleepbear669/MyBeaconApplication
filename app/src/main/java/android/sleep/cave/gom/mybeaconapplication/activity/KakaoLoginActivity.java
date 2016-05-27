package android.sleep.cave.gom.mybeaconapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sleep.cave.gom.mybeaconapplication.MyApplication;
import android.sleep.cave.gom.mybeaconapplication.R;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

/**
 * Created by sleepbear on 2016. 5. 27..
 */

public class KakaoLoginActivity extends BaseActivity{

    private SessionCallback callback;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        if (!Session.getCurrentSession().checkAndImplicitOpen()) {
            setContentView(R.layout.layout_common_kakao_login);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            final Intent intent = new Intent(MyApplication.getGlobalApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }

            setContentView(R.layout.layout_common_kakao_login);
        }
    }
}
