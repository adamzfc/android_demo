package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LiveData;

import com.adamzfc.architecturecomponentsdemo.AbstractPresenter;
import com.adamzfc.architecturecomponentsdemo.data.repository.AccountRepository;
import com.adamzfc.architecturecomponentsdemo.data.vo.Account;
import com.adamzfc.architecturecomponentsdemo.data.vo.Resource;

import java.util.List;

/**
 * Created by adamzfc on 2017/7/3.
 */

public class AccountPresenter extends AbstractPresenter<AccountContract.View> implements AccountContract.Presenter {

    private final AccountRepository mRepository;

    public AccountPresenter(AccountRepository repository) {
        mRepository = repository;
    }

    @Override
    public void addAccount() {
        List a = null;
        a.get(0);
        if (isViewAttached()) {
            getView().testSth();
        }
    }

    @Override
    public LiveData<Resource<List<Account>>> getAccounts() {
        return null;
    }
}
