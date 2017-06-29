package com.adamzfc.architecturecomponentsdemo.db;

import android.accounts.Account;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


/**
 * Created by adamzfc on 2017/6/28.
 */
@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account account);

    @Query("SELECT * FROM account WHERE name = :name")
    LiveData<Account> findByName(String name);

    @Query("SELECT * FROM account WHERE id = :id")
    LiveData<Account> findById(int id);

}
