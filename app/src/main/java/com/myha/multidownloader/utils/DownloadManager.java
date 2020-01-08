package com.myha.multidownloader.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class DownloadManager {
    private final ThreadPoolExecutor downloadThreadPool;
    private final BlockingQueue<Runnable> downaloadWorkQueue;

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 50;

    private static final DownloadManager downloadManager;
    private static final MainThreadExecutor handler;

    static {
        downloadManager = new DownloadManager();
        handler = new MainThreadExecutor();
    }

    private DownloadManager(){
        downaloadWorkQueue = new LinkedBlockingQueue<Runnable>();
        downloadThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, downaloadWorkQueue);
    }

    public static DownloadManager getDownloadManager(){
        return downloadManager;
    }

    public void runDownloadFile(Runnable task){
        downloadThreadPool.execute(task);
    }

    //to runs task on main thread from background thread
    public MainThreadExecutor getMainThreadExecutor(){
        return handler;
    }
}