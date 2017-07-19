package com.adamzfc.architecturecomponentsdemo.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.apache.commons.lang3.Validate;

/**
 * Created by adamzfc on 2017/7/19.
 */

public class ActivityUtil {
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        Validate.notNull(fragmentManager);
        Validate.notNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}
