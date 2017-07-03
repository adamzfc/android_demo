package com.adamzfc.architecturecomponentsdemo.vo;

import android.arch.persistence.room.Entity;

/**
 * Created by adamzfc on 2017/6/28.
 */
@Entity(primaryKeys = "id")
public class Account {
    public final int id;
    public final String name;

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
