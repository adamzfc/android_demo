package com.adamzfc.architecturecomponentsdemo.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.adamzfc.architecturecomponentsdemo.data.vo.Account;
import com.adamzfc.architecturecomponentsdemo.data.vo.Transaction;


/**
 * Created by adamzfc on 2017/6/28.
 */
@Database(entities = {Account.class, Transaction.class}, version = 1)
public abstract class FinancialDb extends RoomDatabase {
    abstract public AccountDao accountDao();
    abstract public TransactionDao transactionDao();
}
