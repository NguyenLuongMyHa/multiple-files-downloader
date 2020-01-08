package com.myha.multidownloader.adapters;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.myha.multidownloader.R;
import com.myha.multidownloader.utils.DownloadManager;
import com.myha.multidownloader.utils.DownloadResultUpdateTask;
import com.myha.multidownloader.utils.DownloadTask;

import java.util.List;

public class FilesRecyclerViewAdapter extends
        RecyclerView.Adapter<FilesRecyclerViewAdapter.ViewHolder>
{

    private List<String> fileList;
    private Context context;

    private String DOWNLOAD_DIR = Environment.getExternalStoragePublicDirectory
            (Environment.DIRECTORY_DOWNLOADS).getPath();

    public FilesRecyclerViewAdapter(List<String> list, Context context)
    {
        fileList = list;
        context = context;
    }

    @Override
    public int getItemCount()
    {
        return fileList.size();
    }

    @Override
    public ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcview_item_layout, parent, false);

        ViewHolder viewHolder =
                new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final String url = fileList.get(position);
        holder.fileUrl.setText(url);

        final TextView downloadStatus = holder.downloadStatus;
        final ProgressBar progressBar = holder.progressBar;
        final Button btnDownload = holder.buttonDownload;

        btnDownload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                progressBar.setVisibility(View.VISIBLE);
                btnDownload.setVisibility(View.GONE);
                downloadFile(url, downloadStatus, progressBar, btnDownload);
            }
        });
    }

    private String getFileName(String url)
    {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private void downloadFile(String url, TextView downloadStatus, ProgressBar progressBar, Button btnDownload)
    {
        String localFile = DOWNLOAD_DIR + "/" + getFileName(url);
        DownloadResultUpdateTask drUpdateTask = new DownloadResultUpdateTask(downloadStatus, progressBar, btnDownload);

        DownloadTask downloadTask = new DownloadTask(url, localFile, drUpdateTask);
        DownloadManager.getDownloadManager().runDownloadFile(downloadTask);

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView fileUrl;
        public TextView downloadStatus;
        public ProgressBar progressBar;
        public Button buttonDownload;

        public ViewHolder(View view)
        {
            super(view);
            fileUrl = view.findViewById(R.id.file_url);
            downloadStatus = view.findViewById(R.id.download_file_status);
            progressBar = view.findViewById(R.id.progressBar);
            buttonDownload = view.findViewById(R.id.btn_download);
        }
    }
}