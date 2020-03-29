// Generated code from Butter Knife. Do not modify!
package com.ekc.ekctracking.view.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ekc.ekctracking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationAdapter$viewHolder_ViewBinding implements Unbinder {
  private NotificationAdapter.viewHolder target;

  @UiThread
  public NotificationAdapter$viewHolder_ViewBinding(NotificationAdapter.viewHolder target,
      View source) {
    this.target = target;

    target.carNoTV = Utils.findRequiredViewAsType(source, R.id.notification_row_item_car_no_tv, "field 'carNoTV'", TextView.class);
    target.dateTV = Utils.findRequiredViewAsType(source, R.id.notification_row_item_date_tv, "field 'dateTV'", TextView.class);
    target.timeTV = Utils.findRequiredViewAsType(source, R.id.notification_row_item_time_tv, "field 'timeTV'", TextView.class);
    target.statusIc = Utils.findRequiredViewAsType(source, R.id.notification_row_item_status_ic, "field 'statusIc'", ImageView.class);
    target.statusTV = Utils.findRequiredViewAsType(source, R.id.notification_row_item_status_tv, "field 'statusTV'", TextView.class);
    target.mapFab = Utils.findRequiredViewAsType(source, R.id.notification_row_item_map_fab, "field 'mapFab'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationAdapter.viewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.carNoTV = null;
    target.dateTV = null;
    target.timeTV = null;
    target.statusIc = null;
    target.statusTV = null;
    target.mapFab = null;
  }
}
