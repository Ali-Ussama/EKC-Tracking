package com.ekc.ekctracking.configs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ekc.ekctracking.BuildConfig;
import com.ekc.ekctracking.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    private static final int REQUEST_CODE_PERMISSION = 1;

    public static void setLocale(Context context) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }


    public static void startActivity(Context context, Class<?> activity_class, Bundle data) {
        Intent intent = new Intent(context, activity_class);
        if (data != null)
            intent.putExtras(data);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> activity_class,
                                     Bundle data, String action) {
        Intent intent = new Intent(context, activity_class);
        if (data != null)
            intent.putExtras(data);
        if (!TextUtils.isEmpty(action))
            intent.setAction(action);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Context context, Class<?> activity_class,
                                              Bundle data, int reqCode) {
        Intent intent = new Intent(context, activity_class);
        if (data != null)
            intent.putExtras(data);
        ((FragmentActivity) context).startActivityForResult(intent, reqCode);
    }


    public static void startActivityForResult(FragmentActivity fragment, Class<?> activity_class,
                                              Bundle data, int reqCode) {

        try {
            Intent intent = new Intent(fragment.getApplicationContext(), activity_class);
            if (data != null)
                intent.putExtras(data);
            fragment.startActivityForResult(intent, reqCode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isEmailValid(String email) {

        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    public static boolean inNetwork(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnected();
    }

    public static void showConnectionErrorToast(Context context) {
        showToast(context, context.getString(R.string.str_toast_network_error));
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void addRVDivider(Context inContext, RecyclerView recyclerView) {
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(
                recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable verticalDivider = ContextCompat.getDrawable(inContext,
                R.drawable._vertical_rv_grey_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);
    }

    public static void addRVDividerWithoutLineFooter(Context inContext, RecyclerView recyclerView) {
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(
                ContextCompat.getDrawable(inContext, R.drawable._vertical_rv_grey_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void updateStatusBarColor(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void updateStatusBarColor(Context context, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(context, color));
        }
    }

    public static Toolbar setupToolbar(AppCompatActivity activity, int homeIcon) {
        Toolbar toolbar = ((Activity) activity).findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (homeIcon == -1) {
                activity.getSupportActionBar().setHomeButtonEnabled(false);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            } else if (homeIcon >= 0) {
                activity.getSupportActionBar().setHomeButtonEnabled(true);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                //if (homeIcon >= 1)
                //activity.getSupportActionBar().setHomeAsUpIndicator(homeIcon);
            }
            toolbar.setContentInsetsAbsolute(0, 0);
            toolbar.getContentInsetEnd();
            //toolbar.setPadding(16, 0, 16, 0);
        }
        return toolbar;
    }


    public static void setToolbarTitle(Toolbar toolbar, String title) {
        TextView toolbarTitleTV = toolbar.findViewById(R.id.toolbar);
        if (toolbarTitleTV != null) toolbarTitleTV.setText(title);
    }

    public static void setToolbarHomeIcon(AppCompatActivity activity, int homeIcon) {
        if (activity.getSupportActionBar() != null)
            activity.getSupportActionBar().setHomeAsUpIndicator(homeIcon);
    }

    public static void closeKeyboard(Context context, View view) {
        try {
            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                if (view != null) { // Check if no mView has focus:
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            } catch (Exception e) {
            }
        } catch (Exception ee) {
        }
    }

    public static String[] getVersionInfo(Context context) {
        String[] versionInfo = new String[]{"", ""};
        try {
            //PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            //String versionCode = pInfo.versionCode + "";
            //String versionName = pInfo.versionName.trim();

            versionInfo[0] = BuildConfig.VERSION_CODE + "";
            versionInfo[1] = BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionInfo;
    }

    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context inContext) {
        String deviceId = "";
        try {
            TelephonyManager tManager = (TelephonyManager) inContext.
                    getSystemService(Context.TELEPHONY_SERVICE);
            if (tManager != null) {
                if (ActivityCompat.checkSelfPermission(inContext, Manifest.permission.
                        READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return null;
                }
                deviceId = tManager.getDeviceId(); //IME 1.
            }
        } catch (Exception ex) {
            Log.v("", ex.getMessage());
        }
        return deviceId;
    }


    public static MaterialDialog showProgressDialog(Context context, String message) {
        return new MaterialDialog.Builder(context)
                //.title(getString(R.string.str_dialog_waiting_msg))
                .content(message)
                .cancelable(false)
                .autoDismiss(false)
                .progress(true, 100)
                .show();
    }

    public static void showAlertDialog(Context context, String message) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .autoDismiss(false)
                .cancelable(false)
                //.title(title)
                .content(message)
                .positiveText(R.string.str_ok_lbl)
                .positiveColorRes(R.color.color_red)
                .onPositive((dialog, which) -> dialog.dismiss())
                .build();
        materialDialog.getTitleView().setTextSize(8f);
        if (!materialDialog.isShowing()) materialDialog.show();
    }

    public static MaterialDialog showAlertDialogWithCustomView(Context context, int layoutResID) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .autoDismiss(false)
                .cancelable(false)
                .customView(layoutResID, false)
                .positiveText(R.string.str_ok_lbl)
                .onPositive((dialog, which) -> dialog.dismiss())
                .positiveColorRes(R.color.color_red)
                .build();
        materialDialog.getTitleView().setTextSize(8f);
        if (!materialDialog.isShowing()) materialDialog.show();
        return materialDialog;
    }

    public interface CallBack {
        void OnPositiveClicked(MaterialDialog dlg);

        void OnNegativeClicked(MaterialDialog dlg);
    }

    public static void showConfirmationDialog(Context context, String message, String positiveTXT,
                                              String negativeTXT, CallBack callBack) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .autoDismiss(false)
                .cancelable(false)
                //.title(title)
                .content(message)
                .positiveText(positiveTXT)
                .positiveColorRes(R.color.color_red)
                .negativeText(negativeTXT)
                .negativeColorRes(R.color.color_red)
                .onPositive((dialog, which) -> callBack.OnPositiveClicked(dialog))
                .onNegative((dialog, which) -> {
                    dialog.dismiss();
                    callBack.OnNegativeClicked(dialog);
                })
                .build();
        materialDialog.getTitleView().setTextSize(8f);
        if (!materialDialog.isShowing()) materialDialog.show();
    }

    public static class DividerItemDecorator extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public DividerItemDecorator(Drawable divider) {
            mDivider = divider;
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i <= childCount - 2; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }
    }

    /*Convert image file to Base64 string...*/
    public static String convertImgFileToBase64(File imgFile) {
        String imgBase64 = "";
        try {
            byte[] bytes = new byte[(int) imgFile.length()];
            FileInputStream fis = new FileInputStream(imgFile);
            BufferedInputStream buf = new BufferedInputStream(fis);
            boolean isRead = buf.read(bytes, 0, bytes.length) > 0;
            buf.close();
            if (isRead)
                imgBase64 = Base64.encodeToString(bytes, Base64.DEFAULT);
            //imgBase64 = imgBase64.replaceAll(System.getProperty("line.separator"), "");// to remove\n
        } catch (Exception e) {
            //
        }
        return "data:image/jpg;base64," + imgBase64;
    }

    public static void requestAppPermissions(Context context) {
        String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE
                , android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                , android.Manifest.permission.ACCESS_FINE_LOCATION
                , android.Manifest.permission.READ_PHONE_STATE};
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, REQUEST_CODE_PERMISSION);
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}