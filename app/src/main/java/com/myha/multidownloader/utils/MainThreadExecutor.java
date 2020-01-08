package com.myha.multidownloader.utils;

import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import android.os.Handler;

public class MainThreadExecutor implements Executor
{
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable runnable) {
        handler.post(runnable);
    }
}
