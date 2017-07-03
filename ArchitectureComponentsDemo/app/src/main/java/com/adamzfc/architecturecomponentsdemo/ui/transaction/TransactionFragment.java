package com.adamzfc.architecturecomponentsdemo.ui.transaction;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamzfc.architecturecomponentsdemo.di.Injectable;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class TransactionFragment extends LifecycleFragment implements Injectable {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
