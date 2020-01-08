package com.myha.multidownloader.utils;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadResultUpdateTask implements Runnable
{
    private TextView tvStatus;
    private String backgroundMsg;
    private ProgressBar progressBar;
    private Button btnDownload;

    private Boolean isDownloaded;

    public DownloadResultUpdateTask(TextView tvStatus, ProgressBar progressBar, Button btnDownload)
    {
        this.tvStatus = tvStatus;
        this.progressBar = progressBar;
        this.btnDownload = btnDownload;
    }

    public void setBackgroundMsg(String bmsg)
    {
        backgroundMsg = bmsg;
    }

    public void isDownloaded(boolean isDownloaded)
    {
        this.isDownloaded = isDownloaded;

    }

    @Override
    public void run()
    {
        tvStatus.setText(backgroundMsg);
        if (isDownloaded)
        {
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            btnDownload.setVisibility(View.VISIBLE);
        }

    }
}
