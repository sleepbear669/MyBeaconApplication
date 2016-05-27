package android.sleep.cave.gom.mybeaconapplication.activity;

import android.app.Activity;
import android.sleep.cave.gom.mybeaconapplication.MyApplication;

/**
 * @author leoshin, created at 15. 7. 20..
 */
public class BaseActivity extends Activity {
    protected static Activity self;

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.setCurrentActivity(this);
        self = BaseActivity.this;
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences() {
        Activity currActivity = MyApplication.getCurrentActivity();
        if (currActivity != null && currActivity.equals(this)) {
            MyApplication.setCurrentActivity(null);
        }
    }

}
