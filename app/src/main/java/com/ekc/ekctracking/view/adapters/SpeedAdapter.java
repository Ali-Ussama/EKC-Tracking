package com.ekc.ekctracking.view.adapters;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.models.pojo.CarStatus;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivity;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeViewCallback;
import com.esri.arcgisruntime.geometry.PointCollection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeedAdapter extends RecyclerView.Adapter<SpeedAdapter.viewHolder> {

    private static final String TAG = "SpeedAdapter";
    private List<CarStatus> data;
    private PointCollection points;
    private MainActivity mContext;
    private HomeViewCallback listener;

    private int selectedPosition;
    private String selectedSpeed;

    public SpeedAdapter(List<CarStatus> data, PointCollection points, MainActivity mContext, HomeViewCallback listener) {
        this.data = data;
        this.points = points;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speeds_rec_row_item, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if (data.get(position).equals(selectedSpeed) && position == selectedPosition) {
            holder.container.setBackground(mContext.getResources().getDrawable(R.drawable.speed_row_item_custom_pressed_background));
            holder.mSpeedTV.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.mSpeedLbl.setTextColor(mContext.getResources().getColor(R.color.white));

        } else {
            holder.container.setBackground(mContext.getResources().getDrawable(R.drawable.speed_row_item_custom_unpressed_background));
            holder.mSpeedTV.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            holder.mSpeedLbl.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }

        holder.mSpeedTV.setText(data.get(position).getSpeed2());
        Log.d(TAG, "displaySpeedList: speed = " + data.get(position));
    }

    public void dataChanged(PointCollection point, List<CarStatus> speeds) {
        Log.d(TAG, "dataChanged: is called");
        data = speeds;
        points = point;
        Log.d(TAG, "dataChanged: points size" + points.size() + " - speeds sizes = " + speeds.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.speeds_rec_row_item_tv)
        TextView mSpeedTV;

        @BindView(R.id.speeds_rec_row_item_lbl)
        TextView mSpeedLbl;

        @BindView(R.id.speeds_rec_row_item_container)
        ConstraintLayout container;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            try {
                ButterKnife.bind(this, itemView);

                container.setOnClickListener(v -> {
                    try {
                        container.setBackground(mContext.getResources().getDrawable(R.drawable.speed_row_item_custom_pressed_background));
                        mSpeedTV.setTextColor(mContext.getResources().getColor(R.color.white));
                        mSpeedLbl.setTextColor(mContext.getResources().getColor(R.color.white));
                        selectedPosition = getAdapterPosition();
                        selectedSpeed = data.get(selectedPosition).getSpeed2();
                        Log.d(TAG, "onClick: selected Position = " + selectedPosition + " - Speed = " + selectedSpeed + " points size = " + points.size() + " speeds size = " + data.size());
                        notifyDataSetChanged();
                        if (listener != null) {
                            listener.onSpeedSelected(selectedPosition, selectedSpeed, points.get(selectedPosition));
                        }
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
