// Generated code from Butter Knife. Do not modify!
package com.ekc.ekctracking.view.activities.notification;

import android.view.View;
import android.widget.ViewAnimator;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ekc.ekctracking.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationActivity_ViewBinding implements Unbinder {
  private NotificationActivity target;

  @UiThread
  public NotificationActivity_ViewBinding(NotificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NotificationActivity_ViewBinding(NotificationActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.notification_activity_rec_view, "field 'mRecyclerView'", RecyclerView.class);
    target.viewAnimator = Utils.findRequiredViewAsType(source, R.id.notification_view_animator, "field 'viewAnimator'", ViewAnimator.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.notification_activity_toolbar, "field 'toolbar'", Toolbar.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.notification_activity_search_view, "field 'searchView'", MaterialSearchView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.viewAnimator = null;
    target.toolbar = null;
    target.searchView = null;
  }
}
