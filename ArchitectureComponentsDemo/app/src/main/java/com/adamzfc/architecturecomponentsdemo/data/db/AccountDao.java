package com.adamzfc.architecturecomponentsdemo.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.adamzfc.architecturecomponentsdemo.data.vo.Account;

import java.util.List;

import io.reactivex.Flowable;


/**
 * Created by adamzfc on 2017/6/28.
 */
@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account account);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccounts(List<Account> accounts);

    @Query("SELECT * FROM account WHERE name = :name")
    LiveData<Account> findByName(String name);

    @Query("SELECT * FROM account WHERE id = :id")
    LiveData<Account> findById(String id);

    @Query("SELECT * FROM account")
    LiveData<List<Account>> loadAccounts();

    @Query("SELECT * FROM account")
    Flowable<List<Account>> loadAccountsRx();

}
