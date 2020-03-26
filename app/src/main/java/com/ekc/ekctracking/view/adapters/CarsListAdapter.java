package com.ekc.ekctracking.view.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.models.pojo.CarStatus;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivity;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeViewCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarsListAdapter extends RecyclerView.Adapter<CarsListAdapter.viewHolder> {

    private static final String TAG = "CarsListAdapter";
    private ArrayList<CarStatus> data;
    private MainActivity mCurrent;
    private HomeViewCallback mListener;
    private String moving_status, stopped_status, disconnected_status, disabled_status,
            moving_status_eng, stopped_status_eng, disconnected_status_eng, disabled_status_eng;

    public CarsListAdapter(ArrayList<CarStatus> data, MainActivity mCurrent, HomeViewCallback mListener) {
        this.data = data;
        this.mCurrent = mCurrent;
        this.mListener = mListener;

        moving_status = mCurrent.getString(R.string.moving_short);
        stopped_status = mCurrent.getString(R.string.stopped_short);
        disconnected_status = mCurrent.getString(R.string.disconnected_short);
        disabled_status = mCurrent.getString(R.string.disabled_short);

        moving_status_eng = mCurrent.getString(R.string.moving_english);
        stopped_status_eng = mCurrent.getString(R.string.stopped_english);
        disconnected_status_eng = mCurrent.getString(R.string.disconnected_english);
        disabled_status_eng = mCurrent.getString(R.string.disabled_english);

        Log.i(TAG, "CarsListAdapter: data size = " + data.size());
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cars_list_rec_row_item, parent, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        try {
            CarStatus carStatus = data.get(position);
            holder.mCarNo.setText(carStatus.getCarNo());

            if (carStatus.getStatus().equals(moving_status_eng)) {
                holder.mStatusIcon.setImageResource(R.drawable.rounded_ic_green);
//                holder.mCarStatus.setText(moving_status);

            } else if (carStatus.getStatus().equals(stopped_status_eng)) {
                holder.mStatusIcon.setImageResource(R.drawable.rounded_ic_red);
//                holder.mCarStatus.setText(stopped_status);

            } else if (carStatus.getStatus().equals(disconnected_status_eng)) {
                holder.mStatusIcon.setImageResource(R.drawable.rounded_ic_yellow);
//                holder.mCarStatus.setText(disconnected_status);

            } else if (carStatus.getStatus().equals(disabled_status_eng)) {
                holder.mStatusIcon.setImageResource(R.drawable.rounded_ic_blue);
//                holder.mCarStatus.setText(disabled_status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDataChanged(ArrayList<CarStatus> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cars_list_rec_row_item_ic)
        ImageView mStatusIcon;

        @BindView(R.id.cars_list_rec_row_item_car_no)
        TextView mCarNo;
        //
        @BindView(R.id.cars_list_rec_row_item_container)
        ConstraintLayout mContainer;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            try {

                ButterKnife.bind(this, itemView);

                mContainer.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: is called");
            if (v.equals(mContainer)) {
                Log.d(TAG, "onClick: mContainer is clicked");
                if (mListener != null) {
                    mListener.onCarSelected(data.get(getAdapterPosition()));
                }
            }
        }
    }
}
