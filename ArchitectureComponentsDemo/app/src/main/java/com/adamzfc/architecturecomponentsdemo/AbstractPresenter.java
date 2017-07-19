package com.adamzfc.architecturecomponentsdemo;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by adamzfc on 2017/7/19.
 */

public abstract class AbstractPresenter<V> implements BasePresenter<V> {
    private WeakReference<V> view;

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<V>(view);
    }

    public void unbindView() {
        if (this.view != null) {
            this.view.clear();
        }
    }

    public V getView() {
        if (this.view != null) {
            return view.get();
        } else {
            return null;
        }
    }

    public boolean isViewAttached() {
        return this.view != null && this.view.get() != null;
    }

}
