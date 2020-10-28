package org.maktab36.scheduledlogs.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class RoomConverters {
    @TypeConverter
    public static Date fromTimestamp(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
