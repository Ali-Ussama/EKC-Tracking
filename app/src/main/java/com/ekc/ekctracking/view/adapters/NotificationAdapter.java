package com.ekc.ekctracking.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;
import com.ekc.ekctracking.view.activities.notification.NotificationPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {

    private RealmList<RealmCarStatus> data;
    private Context context;
    private NotificationPresenter presenter;

    public NotificationAdapter(RealmList<RealmCarStatus> data, Context context, NotificationPresenter presenter) {
        this.data = data;
        this.context = context;
        this.presenter = presenter;
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
        if (carStatus != null) {
            holder.carNoTV.setText(carStatus.getCarNo());
            holder.dateTV.setText(carStatus.getDate());
            holder.timeTV.setText(carStatus.getTime().concat("  -  "));

            if (carStatus.getStatus().matches(context.getString(R.string.disconnected_english))) {
                holder.statusIc.setImageResource(R.drawable.rounded_ic_yellow);
                holder.statusTV.setText(context.getString(R.string.disconnected));
            } else if (carStatus.getStatus().matches(context.getString(R.string.disabled_english))) {
                holder.statusIc.setImageResource(R.drawable.rounded_ic_blue);
                holder.statusTV.setText(context.getString(R.string.disabled));
            }
        }
    }

    public void dataChanged(RealmList<RealmCarStatus> cars) {
        data = cars;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

        @BindView(R.id.car_config_check_car_container)
        ConstraintLayout mCarCheckContainer;

        @BindView(R.id.car_config_check_gprs_container)
        ConstraintLayout mCarGPRSContainer;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            try {
                ButterKnife.bind(this, itemView);

                mCarCheckContainer.setOnClickListener(this);
                mCarGPRSContainer.setOnClickListener(this);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View view) {
            if (mCarCheckContainer.equals(view)) {
                if (presenter != null){
                    presenter.onCarCheckAction(data.get(getAdapterPosition()));
                }
            } else if (mCarGPRSContainer.equals(view)) {
                if (presenter != null){
                    presenter.onCarGPRSAction(data.get(getAdapterPosition()));
                }
            }
        }
    }
}
