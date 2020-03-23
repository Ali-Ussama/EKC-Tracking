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
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeOptionsAdapter$viewHolder_ViewBinding implements Unbinder {
  private HomeOptionsAdapter.viewHolder target;

  @UiThread
  public HomeOptionsAdapter$viewHolder_ViewBinding(HomeOptionsAdapter.viewHolder target,
      View source) {
    this.target = target;

    target.mImageView = Utils.findRequiredViewAsType(source, R.id.home_options_rec_row_item_iv, "field 'mImageView'", ImageView.class);
    target.mTextView = Utils.findRequiredViewAsType(source, R.id.home_options_rec_row_item_tv, "field 'mTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeOptionsAdapter.viewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mImageView = null;
    target.mTextView = null;
  }
}
