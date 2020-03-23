// Generated code from Butter Knife. Do not modify!
package com.ekc.ekctracking.view.activities.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ekc.ekctracking.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.mInputsContainers = Utils.findRequiredViewAsType(source, R.id.login_inputs_container, "field 'mInputsContainers'", ConstraintLayout.class);
    target.mUsernameET = Utils.findRequiredViewAsType(source, R.id.login_username_edit_text, "field 'mUsernameET'", EditText.class);
    target.mPasswordET = Utils.findRequiredViewAsType(source, R.id.login_password_edit_text, "field 'mPasswordET'", EditText.class);
    target.loginBtn = Utils.findRequiredViewAsType(source, R.id.login_btn, "field 'loginBtn'", Button.class);
    target.mProgressBar = Utils.findRequiredViewAsType(source, R.id.login_progress_bar, "field 'mProgressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mInputsContainers = null;
    target.mUsernameET = null;
    target.mPasswordET = null;
    target.loginBtn = null;
    target.mProgressBar = null;
  }
}
