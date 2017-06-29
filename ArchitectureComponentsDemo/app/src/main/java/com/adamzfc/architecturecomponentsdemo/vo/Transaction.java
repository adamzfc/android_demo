package com.adamzfc.architecturecomponentsdemo.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.TypeConverters;

import com.adamzfc.architecturecomponentsdemo.db.DateConverter;

import java.util.Date;

/**
 * Created by adamzfc on 2017/6/28.
 */
@Entity(primaryKeys = {"id"},
    foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "id",
        childColumns = "account_id",
        onUpdate = ForeignKey.CASCADE,
        deferred = true))
@TypeConverters(DateConverter.class)
public class Transaction {
    public final int id;
    public final String description;
    public final Date date;
    private int account_id;

    public Transaction(int id, String description, Date date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }
}
