package com.adamzfc.architecturecomponentsdemo.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.adamzfc.architecturecomponentsdemo.ui.account.AccountViewModel;
import com.adamzfc.architecturecomponentsdemo.ui.transaction.TransactionViewModel;
import com.adamzfc.architecturecomponentsdemo.util.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel.class)
    abstract ViewModel bindAccountViewModel(AccountViewModel accountViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel.class)
    abstract ViewModel bindTransactionViewModel(TransactionViewModel transactionViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
