package org.maktab36.scheduledlogs.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.maktab36.scheduledlogs.model.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Log.class},version =1,exportSchema = false)
@TypeConverters({RoomConverters.class})
public abstract class LogRoomDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "log_scheduler.db";
    public static ExecutorService mDatabaseExecutor= Executors.newFixedThreadPool(4);
    public abstract LogDao getLogDao();

    public static LogRoomDataBase getDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                LogRoomDataBase.class,
                LogRoomDataBase.DATABASE_NAME).build();
    }
}
