package com.adamzfc.architecturecomponentsdemo.ui.transaction;

import android.arch.lifecycle.ViewModel;

import com.adamzfc.architecturecomponentsdemo.data.repository.TransactionRepository;

import javax.inject.Inject;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class TransactionViewModel extends ViewModel {
    private static final String TAG = TransactionViewModel.class.getSimpleName();
    private final TransactionRepository repository;

    @Inject
    public TransactionViewModel(TransactionRepository repository) {
        this.repository = repository;
    }
}
