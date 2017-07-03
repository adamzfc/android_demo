package com.adamzfc.architecturecomponentsdemo.di;


import com.adamzfc.architecturecomponentsdemo.ui.account.AccountFragment;
import com.adamzfc.architecturecomponentsdemo.ui.transaction.TransactionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract AccountFragment contributeAccountFragment();

    @ContributesAndroidInjector
    abstract TransactionFragment contributeTransactionFragment();
}
