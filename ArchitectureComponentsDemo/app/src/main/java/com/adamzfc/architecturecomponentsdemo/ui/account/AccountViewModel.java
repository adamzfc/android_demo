package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.adamzfc.architecturecomponentsdemo.repository.AccountRepository;
import com.adamzfc.architecturecomponentsdemo.vo.Account;
import com.adamzfc.architecturecomponentsdemo.vo.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class AccountViewModel extends ViewModel implements AccountPresenter {
    private static final String TAG = AccountViewModel.class.getSimpleName();
    private final LiveData<Resource<List<Account>>> accounts;
    private final AccountRepository repository;

    @Inject
    public AccountViewModel(AccountRepository accountRepository) {
        this.accounts = accountRepository.loadAccounts();
        this.repository = accountRepository;
    }

    public LiveData<Resource<List<Account>>> getAccounts() {
        return this.accounts;
    }

    @Override
    public void addAccount() {
        Log.d(TAG, "addAccount");
//        this.accounts.getValue().data.add(new Account(1, "account_test"));
        int id = (int)(1+Math.random()*100);
        repository.addAccount(new Account(id, "account" + id));
    }
}
