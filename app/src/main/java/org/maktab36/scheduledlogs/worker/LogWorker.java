package org.maktab36.scheduledlogs.worker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.maktab36.scheduledlogs.model.Log;
import org.maktab36.scheduledlogs.repository.LogRepository;

import java.util.concurrent.TimeUnit;

public class LogWorker extends Worker {
    public static final String WORK_TAG_LOG_SCHEDULER = "logScheduler";

    public LogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log log = new Log("reza",checkNetworkEnabled());
        LogRepository repository=LogRepository.getInstance(getApplicationContext());
        repository.insertLog(log);

        return Result.success();
    }

    private boolean checkNetworkEnabled() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void ScheduleWork(Context context) {
        PeriodicWorkRequest workRequest =
                new PeriodicWorkRequest.Builder(LogWorker.class, 15, TimeUnit.MINUTES)
                        .build();

        WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                        WORK_TAG_LOG_SCHEDULER,
                        ExistingPeriodicWorkPolicy.KEEP,
                        workRequest);
        android.util.Log.d("LST", "ScheduleWork: ");
    }

    public static void cancelWork(Context context) {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_TAG_LOG_SCHEDULER);
        android.util.Log.d("LST", "cancelWork: ");
    }
}
