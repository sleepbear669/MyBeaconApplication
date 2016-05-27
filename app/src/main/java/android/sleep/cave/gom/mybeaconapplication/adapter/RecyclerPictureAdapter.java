package android.sleep.cave.gom.mybeaconapplication.adapter;

import android.sleep.cave.gom.mybeaconapplication.MyApplication;
import android.sleep.cave.gom.mybeaconapplication.R;
import android.sleep.cave.gom.mybeaconapplication.model.Picture;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by sleepbear on 2016. 5. 23..
 */
public class RecyclerPictureAdapter extends RecyclerView.Adapter<RecyclerPictureAdapter.PictureItemViewHolder>{

    private List<Picture> pictureList = Collections.emptyList();

    @Override
    public PictureItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_item, parent, false);
        return new PictureItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(PictureItemViewHolder holder, int position) {
        Picture picture = pictureList.get(position);
        Picasso.with(MyApplication.getContext())
                .load("http://117.17.102.241:8080/" + picture.getImage())
                .resize(1000, 400)
                .into(holder.imageViewMyPicture);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public void setData(List<Picture> data) {
        this.pictureList = data;
        notifyDataSetChanged();
    }

    public class PictureItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewMyPicture;
        public PictureItemViewHolder(View itemView) {
            super(itemView);
            imageViewMyPicture = (ImageView) itemView.findViewById(R.id.iv_my_picture);
        }
    }

}
