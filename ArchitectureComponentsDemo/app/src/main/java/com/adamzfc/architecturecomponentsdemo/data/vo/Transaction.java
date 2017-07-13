package com.adamzfc.architecturecomponentsdemo.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;

import com.adamzfc.architecturecomponentsdemo.data.db.DateConverter;

import java.util.Date;

/**
 * Created by adamzfc on 2017/6/28.
 */
@Entity(primaryKeys = {"id"},
        foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "id",
        childColumns = "account_id",
        onUpdate = ForeignKey.CASCADE,
        deferred = true),
        indices = {@Index("account_id")})
@TypeConverters(DateConverter.class)
public class Transaction {
    public final int id;
    public final String description;
    public final Date date;
    public String account_id;

    public Transaction(int id, String description, Date date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }
}
