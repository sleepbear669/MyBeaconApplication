package android.sleep.cave.gom.mybeaconapplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.sleep.cave.gom.mybeaconapplication.R;
import android.sleep.cave.gom.mybeaconapplication.activity.MainActivity;
import android.sleep.cave.gom.mybeaconapplication.adapter.RecyclerPlaceAdapter;
import android.sleep.cave.gom.mybeaconapplication.listener.RecyclerItemListener;
import android.sleep.cave.gom.mybeaconapplication.model.Place;
import android.sleep.cave.gom.mybeaconapplication.presenter.PlacePresenter;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sleepbear on 2016. 5. 18..
 */
public class PlaceFragment extends Fragment implements PlacePresenter.View{

    private RecyclerPlaceAdapter adapter;
    private RecyclerView placeRecyclerView;
    private PlacePresenter placePresenter;
    private SelectPlaceListener selectPlaceListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "onCreateView", Toast.LENGTH_SHORT).show();
        selectPlaceListener = (MainActivity) getActivity();
        View inflate = inflater.inflate(R.layout.place_list_fragment, container, false);
        placeRecyclerView = (RecyclerView) inflate.findViewById(R.id.rv_place_list);
        placeRecyclerView.addOnItemTouchListener(
                new RecyclerItemListener(
                        getActivity(),
                        (view, position) -> {
                            if (selectPlaceListener != null)
                                selectPlaceListener.onPlaceSelected(adapter.getItem(position));
                        })
        );
        adapter = new RecyclerPlaceAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflate.getContext());
        placeRecyclerView.setLayoutManager(linearLayoutManager);
        placeRecyclerView.setAdapter(adapter);
        placePresenter = new PlacePresenter(this);
        Button button = (Button) inflate.findViewById(R.id.refresh);
        button.setOnClickListener(v -> placePresenter.refresh());
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "onViewCreated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        placePresenter.refresh();
        Toast.makeText(getActivity(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "onDestroyView", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(List<Place> places) {
        Toast.makeText(getActivity(), "" + places.get(0).getImage(), Toast.LENGTH_SHORT).show();
        adapter.replaceWith(places);
    }

    public interface SelectPlaceListener {
        void onPlaceSelected(Place place);
    }

}
