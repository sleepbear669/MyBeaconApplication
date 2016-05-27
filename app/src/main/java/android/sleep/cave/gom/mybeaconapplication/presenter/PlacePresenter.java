package android.sleep.cave.gom.mybeaconapplication.presenter;

import android.sleep.cave.gom.mybeaconapplication.apiManager.PlaceManager;
import android.sleep.cave.gom.mybeaconapplication.model.Place;

import java.util.List;

/**
 * Created by sleepbear on 2016. 5. 20..
 */
public class PlacePresenter {

    private View view;
    private PlaceManager placeManager;

    public PlacePresenter(View view) {
        placeManager = new PlaceManager();
        this.view = view;
    }

    public void refresh() {
        placeManager.getPlaceList()
        .subscribe(places -> view.setData(places));
    }


    public interface View {
        void setData(List<Place> places);
    }
}
