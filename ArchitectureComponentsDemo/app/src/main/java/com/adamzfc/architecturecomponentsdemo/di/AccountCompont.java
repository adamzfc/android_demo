package com.adamzfc.architecturecomponentsdemo.di;

import com.adamzfc.architecturecomponentsdemo.ui.account.AccountFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by adamzfc on 2017/6/29.
 */
@Singleton
@Component(modules = ViewModelModule.class)
public interface AccountCompont {
    void inject(AccountFragment fragment);
}
