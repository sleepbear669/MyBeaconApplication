package android.sleep.cave.gom.mybeaconapplication.adapter;

import android.sleep.cave.gom.mybeaconapplication.MyApplication;
import android.sleep.cave.gom.mybeaconapplication.R;
import android.sleep.cave.gom.mybeaconapplication.model.Place;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sleepbear on 2016. 5. 16..
 */
public class RecyclerPlaceAdapter extends RecyclerView.Adapter<RecyclerPlaceAdapter.PlaceItemViewHolder> {

    private List<Place> placeList = new ArrayList<>();

    @Override
    public PlaceItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);
        return new PlaceItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceItemViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeId = place.getId();
        holder.textViewPlaceName.setText(place.getName());
        Picasso.with(MyApplication.getContext())
                .load("http://117.17.102.241:8080/image/" + place.getImage())
                .resize(1000, 400)
                .into(holder.imageViewPlacePicture);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public Place getItem(int position) {
        return placeList.get(position);
    }
    public void replaceWith(List<Place> list) {
        placeList = list;
        notifyDataSetChanged();
    }

    public class PlaceItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPlaceName;
        ImageView imageViewPlacePicture;
        long placeId;

        public PlaceItemViewHolder(View itemView) {
            super(itemView);
            textViewPlaceName = (TextView) itemView.findViewById(R.id.tv_place_name);
            imageViewPlacePicture = (ImageView) itemView.findViewById(R.id.iv_place_picture);

        }

    }
}
