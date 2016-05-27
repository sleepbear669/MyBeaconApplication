package android.sleep.cave.gom.mybeaconapplication.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.sleep.cave.gom.mybeaconapplication.MyApplication;
import android.sleep.cave.gom.mybeaconapplication.R;
import android.sleep.cave.gom.mybeaconapplication.adapter.RecyclerPictureAdapter;
import android.sleep.cave.gom.mybeaconapplication.model.Picture;
import android.sleep.cave.gom.mybeaconapplication.presenter.PicturePresenter;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sleepbear on 2016. 5. 19..
 */
public class PictureListFragment extends Fragment implements PicturePresenter.View{

    public static final String PLACE_ID = "placeId";
    private long placeId;

    private RecyclerPictureAdapter adapter;
    private PicturePresenter presenter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picturn_list_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_picture_list);
        presenter = new PicturePresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RecyclerPictureAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        presenter.showPicture(MyApplication.getDeviceId(),  getArguments().getLong(PLACE_ID));

    }

    @Override
    public void setData(List<Picture> data) {
        adapter.setData(data);
    }
}
