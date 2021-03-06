package com.grupomedios.desclub.desclubandroid.common.activity;

import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.grupomedios.desclub.desclubandroid.DesclubApplication;

/**
 * Created by jhoncruz on 5/03/15.
 */
public abstract class DesclubGeneralActivity extends BaseActivity {

    private final static String TAG = "DesclubGeneralActivity";

    private MaterialDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        trackScreen(getScreenName());
    }

    public void trackScreen(String screenName) {
        if (screenName != null && !screenName.isEmpty()) {
            Tracker tracker = ((DesclubApplication) getApplication()).getDefaultTracker();
            tracker.setScreenName(screenName);
            tracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }

    public void trackEvent(String eventCategory, String eventName) {
        Tracker tracker = ((DesclubApplication) getApplication()).getDefaultTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(eventCategory)
                .setAction(eventName)
                .build());
    }

    public abstract String getScreenName();
}
