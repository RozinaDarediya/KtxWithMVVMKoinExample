package com.example.ktxexample.local.converter;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Created by Rozina on 2019-09-11.
 */
public class DateConvertersJava {
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
