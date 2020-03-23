package com.ekc.ekctracking.view.activities.mainActivity;

import android.util.Log;
import android.view.MotionEvent;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.MapView;

public class MapSingleTapListener extends DefaultMapViewOnTouchListener {

    private static final String TAG = "MapSingleTapListener";
    private MapView mapView;
    private MainActivity context;
    private SingleTapListener listener;

    public MapSingleTapListener(MainActivity context, MapView mapView, SingleTapListener listener) {
        super(context, mapView);
        try {

            this.mapView = mapView;
            this.context = context;
            this.listener = listener;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        try {
            Point mapPoint = mapView.screenToLocation(new android.graphics.Point((int) e.getX(), (int) e.getY()));
            Log.i(TAG, String.format("User tapped on the map at (%.3f,%.3f)", mapPoint.getX(), mapPoint.getY()));

            if (listener != null) {
                listener.onSingleTap(mapPoint);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onRotate(MotionEvent event, double rotationAngle) {
        try {
//            context.mMatrix.reset();
//            context.mMatrix.postRotate(-(float) rotationAngle, context.mBitmap.getHeight() / 2, context.mBitmap.getWidth() / 2);
//            context.mCompass.setImageMatrix(context.mMatrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onRotate(event, rotationAngle);
    }
}
