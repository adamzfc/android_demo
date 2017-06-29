package com.adamzfc.architecturecomponentsdemo.db;

import android.accounts.Account;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.adamzfc.architecturecomponentsdemo.vo.Transaction;


/**
 * Created by adamzfc on 2017/6/28.
 */
@Database(entities = {Account.class, Transaction.class}, version = 1)
public abstract class FinancialDb extends RoomDatabase {
    abstract public AccountDao accountDao();
    abstract public TransactionDao transactionDao();
}
