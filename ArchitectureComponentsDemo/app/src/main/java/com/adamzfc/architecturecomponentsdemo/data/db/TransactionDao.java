package com.adamzfc.architecturecomponentsdemo.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.adamzfc.architecturecomponentsdemo.data.vo.Transaction;


/**
 * Created by adamzfc on 2017/6/28.
 */
@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction transaction);

//    @Query("SELECT * FROM transaction WHERE id = :id")
//    LiveData<Transaction> findById(int id);
}
