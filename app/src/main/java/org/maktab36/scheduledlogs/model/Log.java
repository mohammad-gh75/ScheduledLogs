package org.maktab36.scheduledlogs.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "log")
public class Log {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "index")
    private long mIndex;
    @ColumnInfo(name = "currentDate")
    private Date mCurrentDate;
    @ColumnInfo(name = "displayName")
    private String mDisplayName;
    @ColumnInfo(name = "WifiEnabled")
    private boolean mWifiEnabled;

    public long getIndex() {
        return mIndex;
    }

    public void setIndex(long index) {
        mIndex = index;
    }

    public Date getCurrentDate() {
        return mCurrentDate;
    }

    public void setCurrentDate(Date currentDate) {
        mCurrentDate = currentDate;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public boolean isWifiEnabled() {
        return mWifiEnabled;
    }

    public void setWifiEnabled(boolean wifiEnabled) {
        mWifiEnabled = wifiEnabled;
    }

    public Log() {
        mCurrentDate = new Date();
    }

    public Log(String displayName, boolean wifiEnabled) {
        mCurrentDate = new Date();
        mDisplayName = displayName;
        mWifiEnabled = wifiEnabled;
    }

    public String getLogMessage() {
        String wifiState = mWifiEnabled ? "On" : "Off";
        return "Log{" +
                "\nIndex=" + mIndex +
                ",\nCurrentDate=" + mCurrentDate +
                ",\nCurrentTimeStamp=" + mCurrentDate.getTime() +
                ",\nDisplayName='" + mDisplayName + '\'' +
                ",\nWifiState=" + wifiState +
                "}\n";
    }
}
