package com.adamzfc.architecturecomponentsdemo.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.adamzfc.architecturecomponentsdemo.di.ApplicationContext;
import com.adamzfc.architecturecomponentsdemo.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by adamzfc on 2017/7/13.
 */
@Singleton
public class AppPreferencesHelper implements PreferencesHelper {
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }
}
