package com.adamzfc.architecturecomponentsdemo.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
