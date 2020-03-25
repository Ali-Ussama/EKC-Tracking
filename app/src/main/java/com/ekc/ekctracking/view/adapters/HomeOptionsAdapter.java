package com.ekc.ekctracking.view.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.models.pojo.HomeOptionsModel;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivity;
import com.ekc.ekctracking.view.fragments.home.HomeFragmentListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeOptionsAdapter extends RecyclerView.Adapter<HomeOptionsAdapter.viewHolder> {

    private static final String TAG = "HomeOptionsAdapter";
    private ArrayList<HomeOptionsModel> data;
    private MainActivity mCurrent;
    private HomeFragmentListener homeFragmentListener;

    public HomeOptionsAdapter(ArrayList<HomeOptionsModel> data, MainActivity mCurrent, HomeFragmentListener homeFragmentListener) {
        this.data = data;
        this.mCurrent = mCurrent;
        this.homeFragmentListener = homeFragmentListener;
        Log.i(TAG, "HomeOptionsAdapter: data size = " + data.size());
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_option_rec_row_item, parent, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        try {
            Log.i(TAG, "onBindViewHolder: position = " + position);
            Log.i(TAG, "onBindViewHolder: data res id = " + data.get(position).getReportIconResId());
            Log.i(TAG, "onBindViewHolder: data report Name = " + data.get(position).getReportName());
//            Picasso.get().load(data.get(position).getReportIconResId()).into(holder.mImageView);

//            Log.i(TAG, "onBindViewHolder: " + mCurrent.getResources().getDrawable(R.drawable.ic_near_me_white_24dp));

            holder.mImageView.setImageResource(data.get(position).getReportIconResId());
            holder.mTextView.setText(data.get(position).getReportName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class viewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_options_rec_row_item_iv)
        ImageView mImageView;

        @BindView(R.id.home_options_rec_row_item_tv)
        TextView mTextView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            try {

                ButterKnife.bind(this, itemView);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
