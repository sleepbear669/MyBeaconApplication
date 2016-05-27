package android.sleep.cave.gom.mybeaconapplication.presenter;

import android.sleep.cave.gom.mybeaconapplication.apiManager.PictureManager;
import android.sleep.cave.gom.mybeaconapplication.model.Picture;

import java.util.List;

/**
 * Created by sleepbear on 2016. 5. 23..
 */
public class PicturePresenter {

    private View view;
    private PictureManager pictureManager;

    public PicturePresenter(View view) {
        pictureManager = new PictureManager();
        this.view = view;
    }

    public void showPicture(String userDeviceId, long placeId) {
        pictureManager.getPictureList(userDeviceId, placeId)
                .subscribe(pictures -> view.setData(pictures));
    }

    public interface View {

        void setData(List<Picture> data);

    }
}
