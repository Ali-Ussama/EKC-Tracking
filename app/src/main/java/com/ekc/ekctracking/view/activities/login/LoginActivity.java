package com.ekc.ekctracking.view.activities.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.configs.AppUtils;
import com.ekc.ekctracking.models.pojo.LoginModel;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginViewListener {

    @BindView(R.id.login_inputs_container)
    ConstraintLayout mInputsContainers;

    @BindView(R.id.login_username_edit_text)
    EditText mUsernameET;

    @BindView(R.id.login_password_edit_text)
    EditText mPasswordET;

    @BindView(R.id.login_btn)
    Button loginBtn;

    @BindView(R.id.login_progress_bar)
    ProgressBar mProgressBar;

    private LoginActivity mCurrent;
    private LoginPresenter presenter;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }

        setContentView(R.layout.activity_login);

        try {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
            ButterKnife.bind(this);


            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {

            mCurrent = LoginActivity.this;
            presenter = new LoginPresenter(mCurrent, this);

            loginBtn.setOnClickListener(this);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!presenter.loggedInBefore()) {
                        mInputsContainers.setVisibility(View.VISIBLE);
                        loginBtn.setVisibility(View.VISIBLE);
                    } else {
                        onLogin(true, null);
                    }
                }
            }, 500);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        try {
            if (AppUtils.inNetwork(mCurrent)) {
                if (mUsernameET.getText() == null || mUsernameET.getText().toString().trim().isEmpty()) {
                    mUsernameET.setError(getString(R.string.required));
                } else if (mPasswordET.getText() == null || mPasswordET.getText().toString().trim().isEmpty()) {
                    mPasswordET.setError(getString(R.string.required));
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    username = mUsernameET.getText().toString();
                    password = mPasswordET.getText().toString();

                    LoginModel loginModel = new LoginModel();
                    loginModel.setUsername(username);
                    loginModel.setPassword(password);
                    loginModel.setGrant_type("password");

                    presenter.login(loginModel);
                }
            } else {
                AppUtils.showToast(mCurrent, "No Network Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLogin(boolean status, Throwable t) {
        mProgressBar.setVisibility(View.GONE);
        if (status) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//            }else{
            startActivity(intent);
//            }

        } else {
            if (t != null) {
                t.printStackTrace();
            }
            AppUtils.showToast(mCurrent, "Wrong username/password");
        }
    }
}
