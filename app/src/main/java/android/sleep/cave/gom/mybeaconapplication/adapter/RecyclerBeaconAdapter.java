package android.sleep.cave.gom.mybeaconapplication.adapter;

import android.sleep.cave.gom.mybeaconapplication.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sleepbear on 2016. 5. 10..
 */
public class RecyclerBeaconAdapter extends RecyclerView.Adapter<RecyclerBeaconAdapter.BeaconItemViewHolder> {

    private List<Beacon> beaconList = new ArrayList<>();


    @Override
    public BeaconItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beacon_item, parent, false);
        BeaconItemViewHolder beaconItemViewHolder = new BeaconItemViewHolder(v);
        return beaconItemViewHolder;
    }

    @Override
    public void onBindViewHolder(BeaconItemViewHolder holder, int position) {
        Beacon mData = beaconList.get(position);
        double v = Utils.computeAccuracy(mData);
        holder.textViewUuid.setText(mData.getProximityUUID().toString());
        holder.textViewMajor.setText("Major : " + mData.getMajor());
        holder.textViewMinor.setText("minor : " + mData.getMinor() );
        holder.textDistance.setText("minor : " + v );
    }

    @Override
    public int getItemCount() {
        return beaconList.size();
    }


    public void replaceWith(List<Beacon> list) {
        beaconList = list;
        notifyDataSetChanged();
    }

    public class BeaconItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUuid;
        TextView textViewMajor;
        TextView textViewMinor;
        TextView textDistance;

        public BeaconItemViewHolder(View itemView) {
            super(itemView);
            textViewUuid = (TextView) itemView.findViewById(R.id.tv_uuid);
            textViewMajor = (TextView) itemView.findViewById(R.id.tv_major);
            textViewMinor = (TextView) itemView.findViewById(R.id.tv_minor);
            textDistance = (TextView) itemView.findViewById(R.id.tv_distance);
        }

    }
}
