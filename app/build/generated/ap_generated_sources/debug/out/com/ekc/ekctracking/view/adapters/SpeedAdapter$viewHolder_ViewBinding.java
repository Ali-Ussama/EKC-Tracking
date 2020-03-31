// Generated code from Butter Knife. Do not modify!
package com.ekc.ekctracking.view.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ekc.ekctracking.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SpeedAdapter$viewHolder_ViewBinding implements Unbinder {
  private SpeedAdapter.viewHolder target;

  @UiThread
  public SpeedAdapter$viewHolder_ViewBinding(SpeedAdapter.viewHolder target, View source) {
    this.target = target;

    target.mSpeedTV = Utils.findRequiredViewAsType(source, R.id.speeds_rec_row_item_tv, "field 'mSpeedTV'", TextView.class);
    target.mSpeedLbl = Utils.findRequiredViewAsType(source, R.id.speeds_rec_row_item_lbl, "field 'mSpeedLbl'", TextView.class);
    target.container = Utils.findRequiredViewAsType(source, R.id.speeds_rec_row_item_container, "field 'container'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SpeedAdapter.viewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSpeedTV = null;
    target.mSpeedLbl = null;
    target.container = null;
  }
}
