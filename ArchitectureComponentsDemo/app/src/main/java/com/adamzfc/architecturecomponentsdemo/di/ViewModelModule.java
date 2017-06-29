package com.adamzfc.architecturecomponentsdemo.di;

import android.arch.lifecycle.ViewModel;

import com.adamzfc.architecturecomponentsdemo.ui.account.AccountViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel.class)
    abstract ViewModel bindAccountViewModel(AccountViewModel repoViewModel);

//    @Binds
//    abstract ViewModelProvider.Factory bindViewModelFactory(GithubViewModelFactory factory);
}
