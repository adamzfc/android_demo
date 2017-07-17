package com.adamzfc.architecturecomponentsdemo.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.adamzfc.architecturecomponentsdemo.AppExecutors;
import com.adamzfc.architecturecomponentsdemo.data.api.ApiResponse;
import com.adamzfc.architecturecomponentsdemo.data.api.FinancialService;
import com.adamzfc.architecturecomponentsdemo.data.db.AccountDao;
import com.adamzfc.architecturecomponentsdemo.data.db.FinancialDb;
import com.adamzfc.architecturecomponentsdemo.data.vo.Account;
import com.adamzfc.architecturecomponentsdemo.data.vo.Resource;
import com.adamzfc.architecturecomponentsdemo.util.RateLimiter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by adamzfc on 2017/6/29.
 */
@Singleton
public class AccountRepository {
    private static final String TAG = AccountRepository.class.getSimpleName();
    private final FinancialDb db;

    private final AccountDao accountDao;

    private final FinancialService financialService;

    private final AppExecutors appExecutors;

    private RateLimiter<String> accountListRateLimit = new RateLimiter<>(1, TimeUnit.SECONDS);

    @Inject
    public AccountRepository(FinancialDb db, AccountDao accountDao,
                             FinancialService financialService, AppExecutors appExecutors) {
        this.db = db;
        this.accountDao = accountDao;
        this.financialService = financialService;
        this.appExecutors = appExecutors;
    }

    public void addAccount(Account account) {
        financialService.addAccount(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    if (res.isSuccess()) {
                        appExecutors.diskIO().execute(() ->
                            accountDao.insert(account)
                        );
                    }
                });
    }

    public LiveData<Resource<List<Account>>> loadAccounts() {
        String loadAccountsKey = "loadAccounts";
        return new NetworkBoundResource<List<Account>, List<Account>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Account> item) {
                accountDao.insertAccounts(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Account> data) {
                return data == null || data.isEmpty()
                        || accountListRateLimit.shouldFetch(loadAccountsKey);
            }

            @NonNull
            @Override
            protected LiveData<List<Account>> loadFromDb() {
                return accountDao.loadAccounts();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Account>>> createCall() {
                return financialService.getAccounts();
            }
        }.asLiveData();
    }

}
