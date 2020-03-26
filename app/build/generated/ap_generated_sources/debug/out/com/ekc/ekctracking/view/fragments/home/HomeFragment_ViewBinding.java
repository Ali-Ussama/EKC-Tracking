// Generated code from Butter Knife. Do not modify!
package com.ekc.ekctracking.view.fragments.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ekc.ekctracking.R;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.rootViewAnimator = Utils.findRequiredViewAsType(source, R.id.home_fragment_view_animator, "field 'rootViewAnimator'", ViewAnimator.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", MapView.class);
    target.mLocationFab = Utils.findRequiredViewAsType(source, R.id.home_frag_location_fab, "field 'mLocationFab'", FloatingActionButton.class);
    target.carDetailsToolbar = Utils.findRequiredView(source, R.id.home_bottom_sheet_car_details_toolbar, "field 'carDetailsToolbar'");
    target.mBottomSheetLayout = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'mBottomSheetLayout'", LinearLayout.class);
    target.mBottomSheetViewAnimator = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_view_animator, "field 'mBottomSheetViewAnimator'", ViewAnimator.class);
    target.mCarDetailsContainer = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_container, "field 'mCarDetailsContainer'", ConstraintLayout.class);
    target.mBottomSheetCloseIcon = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_close_ic, "field 'mBottomSheetCloseIcon'", ImageView.class);
    target.mBottomSheetCollapseExpandIcon = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_expand_collapse_ic, "field 'mBottomSheetCollapseExpandIcon'", ImageView.class);
    target.mBottomSheetCarDetailsTitleTV = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_title_tv, "field 'mBottomSheetCarDetailsTitleTV'", TextView.class);
    target.mCarNoTV = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_car_no_value, "field 'mCarNoTV'", TextView.class);
    target.mCarDateTV = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_car_date_value, "field 'mCarDateTV'", TextView.class);
    target.mCarTimeTV = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_car_time_value, "field 'mCarTimeTV'", TextView.class);
    target.mCarAddressTV = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_car_address_value, "field 'mCarAddressTV'", TextView.class);
    target.mCarStatusIcon = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_gps_unit_ic, "field 'mCarStatusIcon'", ImageView.class);
    target.mCarGPSUnitNumberTV = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_gps_unit_value, "field 'mCarGPSUnitNumberTV'", TextView.class);
    target.mCarSpeedTV = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_speed_value, "field 'mCarSpeedTV'", TextView.class);
    target.mMovingReportBottomSheetAction = Utils.findRequiredViewAsType(source, R.id.home_bottom_sheet_car_details_period_report_container, "field 'mMovingReportBottomSheetAction'", ConstraintLayout.class);
    target.mHomeCarsListContainer = Utils.findRequiredViewAsType(source, R.id.home_cars_list_container, "field 'mHomeCarsListContainer'", ConstraintLayout.class);
    target.mHomeCarsListRV = Utils.findRequiredViewAsType(source, R.id.home_cars_list, "field 'mHomeCarsListRV'", RecyclerView.class);
    target.movingFilterCarTitle = Utils.findRequiredViewAsType(source, R.id.moving_report_filter_car_no_tv, "field 'movingFilterCarTitle'", TextView.class);
    target.movingFilterCloseIV = Utils.findRequiredViewAsType(source, R.id.moving_report_filter_close_ic, "field 'movingFilterCloseIV'", ImageView.class);
    target.movingFilterSaveIV = Utils.findRequiredViewAsType(source, R.id.moving_report_filter_save_ic, "field 'movingFilterSaveIV'", ImageView.class);
    target.movingFilterTitleIV = Utils.findRequiredViewAsType(source, R.id.moving_report_filter_title_tv, "field 'movingFilterTitleIV'", TextView.class);
    target.movingFilterDateET = Utils.findRequiredViewAsType(source, R.id.moving_report_filter_date_et, "field 'movingFilterDateET'", TextInputEditText.class);
    target.movingFilterTimeFromET = Utils.findRequiredViewAsType(source, R.id.moving_report_filter_time_from_et, "field 'movingFilterTimeFromET'", TextInputEditText.class);
    target.movingFilterTimeToET = Utils.findRequiredViewAsType(source, R.id.moving_report_filter_time_to_et, "field 'movingFilterTimeToET'", TextInputEditText.class);
    target.mNavDrawerIc = Utils.findRequiredViewAsType(source, R.id.home_nav_drawer_ic, "field 'mNavDrawerIc'", ImageView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.home_toolbar, "field 'toolbar'", Toolbar.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.search_view, "field 'searchView'", MaterialSearchView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rootViewAnimator = null;
    target.mapView = null;
    target.mLocationFab = null;
    target.carDetailsToolbar = null;
    target.mBottomSheetLayout = null;
    target.mBottomSheetViewAnimator = null;
    target.mCarDetailsContainer = null;
    target.mBottomSheetCloseIcon = null;
    target.mBottomSheetCollapseExpandIcon = null;
    target.mBottomSheetCarDetailsTitleTV = null;
    target.mCarNoTV = null;
    target.mCarDateTV = null;
    target.mCarTimeTV = null;
    target.mCarAddressTV = null;
    target.mCarStatusIcon = null;
    target.mCarGPSUnitNumberTV = null;
    target.mCarSpeedTV = null;
    target.mMovingReportBottomSheetAction = null;
    target.mHomeCarsListContainer = null;
    target.mHomeCarsListRV = null;
    target.movingFilterCarTitle = null;
    target.movingFilterCloseIV = null;
    target.movingFilterSaveIV = null;
    target.movingFilterTitleIV = null;
    target.movingFilterDateET = null;
    target.movingFilterTimeFromET = null;
    target.movingFilterTimeToET = null;
    target.mNavDrawerIc = null;
    target.toolbar = null;
    target.searchView = null;
  }
}
