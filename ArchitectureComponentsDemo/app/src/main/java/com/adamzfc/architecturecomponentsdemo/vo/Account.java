package com.adamzfc.architecturecomponentsdemo.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;

import java.util.UUID;

/**
 * Created by adamzfc on 2017/6/28.
 */
@Entity(primaryKeys = "id", indices = {@Index("id")})
public class Account {
    public final String id;
    public final String name;

    @Ignore
    public Account(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
