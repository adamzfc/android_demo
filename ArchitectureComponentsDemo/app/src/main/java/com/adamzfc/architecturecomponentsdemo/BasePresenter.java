package com.adamzfc.architecturecomponentsdemo;

import android.support.annotation.NonNull;

/**
 * Created by adamzfc on 2017/7/19.
 */

public interface BasePresenter<V> {
    void bindView(@NonNull V view);

    void unbindView();

    V getView();

    boolean isViewAttached();
}
