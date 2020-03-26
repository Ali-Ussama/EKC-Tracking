// Generated code from Butter Knife. Do not modify!
package com.ekc.ekctracking.view.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ekc.ekctracking.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CarsListAdapter$viewHolder_ViewBinding implements Unbinder {
  private CarsListAdapter.viewHolder target;

  @UiThread
  public CarsListAdapter$viewHolder_ViewBinding(CarsListAdapter.viewHolder target, View source) {
    this.target = target;

    target.mStatusIcon = Utils.findRequiredViewAsType(source, R.id.cars_list_rec_row_item_ic, "field 'mStatusIcon'", ImageView.class);
    target.mCarNo = Utils.findRequiredViewAsType(source, R.id.cars_list_rec_row_item_car_no, "field 'mCarNo'", TextView.class);
    target.mContainer = Utils.findRequiredViewAsType(source, R.id.cars_list_rec_row_item_container, "field 'mContainer'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CarsListAdapter.viewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mStatusIcon = null;
    target.mCarNo = null;
    target.mContainer = null;
  }
}
