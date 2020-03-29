package com.ekc.ekctracking.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.models.pojo.CarStatus;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {

    private RealmList<RealmCarStatus> data;
    private Context context;

    public NotificationAdapter(RealmList<RealmCarStatus> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_rec_row_item, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RealmCarStatus carStatus = data.get(position);
        holder.carNoTV.setText(carStatus.getCarNo());
        holder.dateTV.setText(carStatus.getDate());
        holder.timeTV.setText("  -  ".concat(carStatus.getTime()));

        if (carStatus.getStatus().matches(context.getString(R.string.disconnected_english))) {
            holder.statusIc.setImageResource(R.drawable.rounded_ic_yellow);
            holder.statusTV.setText(context.getString(R.string.disconnected));
        } else if (carStatus.getStatus().matches(context.getString(R.string.disabled_english))) {
            holder.statusIc.setImageResource(R.drawable.rounded_ic_blue);
            holder.statusTV.setText(context.getString(R.string.disabled));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notification_row_item_car_no_tv)
        TextView carNoTV;

        @BindView(R.id.notification_row_item_date_tv)
        TextView dateTV;

        @BindView(R.id.notification_row_item_time_tv)
        TextView timeTV;

        @BindView(R.id.notification_row_item_status_ic)
        ImageView statusIc;

        @BindView(R.id.notification_row_item_status_tv)
        TextView statusTV;

        @BindView(R.id.notification_row_item_map_fab)
        FloatingActionButton mapFab;

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
