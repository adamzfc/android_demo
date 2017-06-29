package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.adamzfc.architecturecomponentsdemo.vo.Account;
import com.adamzfc.architecturecomponentsdemo.vo.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class AccountViewModel extends ViewModel {
    private final LiveData<Resource<List<Account>>> accounts;

    @Inject
    public AccountViewModel(LiveData<Resource<List<Account>>> accounts) {
        this.accounts = accounts;
    }

    public LiveData<Resource<List<Account>>> getAccounts() {
        return this.accounts;
    }
}
