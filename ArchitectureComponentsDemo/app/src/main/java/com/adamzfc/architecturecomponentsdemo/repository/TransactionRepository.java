package com.adamzfc.architecturecomponentsdemo.repository;

import com.adamzfc.architecturecomponentsdemo.AppExecutors;
import com.adamzfc.architecturecomponentsdemo.api.FinancialService;
import com.adamzfc.architecturecomponentsdemo.db.FinancialDb;
import com.adamzfc.architecturecomponentsdemo.db.TransactionDao;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by adamzfc on 2017/6/29.
 */
@Singleton
public class TransactionRepository {
    private static final String TAG = TransactionRepository.class.getSimpleName();
    private final FinancialDb db;

    private final TransactionDao transactionDao;

    private final FinancialService financialService;

    private final AppExecutors appExecutors;

    @Inject
    public TransactionRepository(FinancialDb db, TransactionDao transactionDao,
                                 FinancialService financialService, AppExecutors appExecutors) {
        this.db = db;
        this.transactionDao = transactionDao;
        this.financialService = financialService;
        this.appExecutors = appExecutors;
    }
}
