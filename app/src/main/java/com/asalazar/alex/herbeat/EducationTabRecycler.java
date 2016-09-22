package com.asalazar.alex.herbeat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

/**
 * Created by sjand on 7/3/2016.
 */
public class EducationTabRecycler  extends RecyclerView.Adapter<EducationTabRecycler.RecyclerViewHolder> {

    private static final int RECOVERY_REQUEST = 1;

    Activity ctx;

    public EducationTabRecycler(Activity context) {
        this.ctx = context;
    }

    @Override
    public EducationTabRecycler.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item, parent, false);
        return new RecyclerViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final EducationTabRecycler.RecyclerViewHolder holder, final int position) {
        switch (position){
            case 0:
                break;
            case 1:
//                holder.description.setVisibility(View.GONE);
//                holder.youTubeView.setVisibility(View.VISIBLE);
//
//                holder.youTubeView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
//                    @Override
//                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
//
//                        if (!wasRestored) {
//                            player.cueVideo("C92dVS8NIw4"); // Plays https://www.youtube.com/watch?v=0aNNYEUARAk//
//                        }
//                    }
//                    @Override
//                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
//                        if (errorReason.isUserRecoverableError()) {
//                            errorReason.getErrorDialog(ctx, RECOVERY_REQUEST).show();
//                        } else {
//                            String error = String.format(ctx.getString(R.string.player_error), errorReason.toString());
//                            Toast.makeText(ctx, error, Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
//                        return holder.youTubeView;
//                    }
//
//                });
                break;
            case 2:
//                holder.description.setVisibility(View.GONE);
//                holder.youTubeView.setVisibility(View.VISIBLE);
//
//                holder.youTubeView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
//                    @Override
//                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
//
//                        if (!wasRestored) {
//                            player.cueVideo("fR3NxCR9z2U"); // Plays https://www.youtube.com/watch?v=0aNNYEUARAk//
//                        }
//                    }
//                    @Override
//                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
//                        if (errorReason.isUserRecoverableError()) {
//                            errorReason.getErrorDialog(ctx, RECOVERY_REQUEST).show();
//                        } else {
//                            String error = String.format(ctx.getString(R.string.player_error), errorReason.toString());
//                            Toast.makeText(ctx, error, Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
//                        return holder.youTubeView;
//                    }
//
//                });
                break;
            case 3:
                holder.icon.setVisibility(View.GONE);
                holder.text.setText("Stress Management");
                holder.description.setText("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.");
                break;
            case 4:
                holder.icon.setVisibility(View.GONE);
                holder.text.setText("Medications");
                holder.description.setText("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.");
                break;
            case 5:
                holder.icon.setVisibility(View.GONE);
                holder.text.setText("Bahavior Change Principles");
                holder.description.setText("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.");
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {


        ImageView icon;
        TextView text;
        TextView description;
        YouTubePlayerView youTubeView;
        View view;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.startIcon);
            text = (TextView) itemView.findViewById(R.id.startTV);
            youTubeView = (YouTubePlayerView) itemView.findViewById(R.id.youtube_view);
            view = (View) itemView.findViewById(R.id.div);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
