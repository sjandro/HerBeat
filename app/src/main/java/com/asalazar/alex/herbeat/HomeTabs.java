package com.asalazar.alex.herbeat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sjand on 7/2/2016.
 */
public class HomeTabs extends RecyclerView.Adapter<HomeTabs.RecyclerViewHolder> {
    @Override
    public HomeTabs.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_bttn, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeTabs.RecyclerViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.icon.setImageResource(R.mipmap.ic_stars_black_24dp);
                holder.text.setText("Start Here");
                break;
            case 1:
                holder.icon.setImageResource(R.mipmap.ic_school_black_24dp);
                holder.text.setText("Education");
                break;
            case 2:
                holder.icon.setImageResource(R.mipmap.ic_poll_black_24dp);
                holder.text.setText("Health Data");
                break;
            case 3:
                holder.icon.setImageResource(R.mipmap.ic_face_black_24dp);
                holder.text.setText("Health Coach");
                break;
            case 4:
                holder.icon.setImageResource(R.mipmap.ic_people_black_24dp);
                holder.text.setText("Communication");
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {


        ImageView icon;
        TextView text;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.startIcon);
            text = (TextView) itemView.findViewById(R.id.startTV);
        }
    }

}
