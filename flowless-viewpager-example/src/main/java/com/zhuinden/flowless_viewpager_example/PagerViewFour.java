package com.zhuinden.flowless_viewpager_example;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import flowless.preset.FlowLifecycles;

/**
 * Created by Zhuinden on 2016.07.17..
 */
public class PagerViewFour extends RelativeLayout implements FlowLifecycles.ViewLifecycleListener {
    private static final String TAG = "PagerViewFour";

    public PagerViewFour(Context context) {
        super(context);
    }

    public PagerViewFour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PagerViewFour(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public PagerViewFour(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void onViewRestored() {
        Log.i(TAG, "View restored in [" + TAG + "]");
    }

    @Override
    public void onViewDestroyed(boolean removedByFlow) {
        Log.i(TAG, "View destroyed in [" + TAG + "]");
    }
}
