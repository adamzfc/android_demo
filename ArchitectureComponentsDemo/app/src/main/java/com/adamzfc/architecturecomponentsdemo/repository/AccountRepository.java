package com.adamzfc.architecturecomponentsdemo.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.adamzfc.architecturecomponentsdemo.AppExecutors;
import com.adamzfc.architecturecomponentsdemo.api.ApiResponse;
import com.adamzfc.architecturecomponentsdemo.api.FinancialService;
import com.adamzfc.architecturecomponentsdemo.db.AccountDao;
import com.adamzfc.architecturecomponentsdemo.db.FinancialDb;
import com.adamzfc.architecturecomponentsdemo.util.RateLimiter;
import com.adamzfc.architecturecomponentsdemo.vo.Account;
import com.adamzfc.architecturecomponentsdemo.vo.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by adamzfc on 2017/6/29.
 */
@Singleton
public class AccountRepository {
    private final FinancialDb db;

    private final AccountDao accountDao;

    private final FinancialService financialService;

    private final AppExecutors appExecutors;

    private RateLimiter<String> accountListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    public AccountRepository(FinancialDb db, AccountDao accountDao,
                             FinancialService financialService, AppExecutors appExecutors) {
        this.db = db;
        this.accountDao = accountDao;
        this.financialService = financialService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Account>>> loadAccounts() {
        return new NetworkBoundResource<List<Account>, List<Account>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Account> item) {
                accountDao.insertAccounts(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Account> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Account>> loadFromDb() {
                return accountDao.loadAccounts();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Account>>> createCall() {
                return null;
            }
        }.asLiveData();
    }
}
