package com.myha.multidownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.myha.multidownloader.adapters.FilesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvFile = findViewById(R.id.rv_files);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        rvFile.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                rvFile.getContext(), recyclerLayoutManager.getOrientation());
        rvFile.addItemDecoration(dividerItemDecoration);

        FilesRecyclerViewAdapter recyclerViewAdapter = new FilesRecyclerViewAdapter(
                getFilesList(), this);
        rvFile.setAdapter(recyclerViewAdapter);
        writeStoragePermission();
    }

    private void writeStoragePermission()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                 Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
    }

    private List<String> getFilesList()
    {
        List<String> files = new ArrayList<String>();
        files.add("https://wwww.argospetinsurance.co.uk/assets/uploads/2017/10/pexels-photo-416160-1024x683.jpeg");
        files.add("http://www.petsworld.in/blog/wp-content/uploads/2014/09/cute-adorable-kitten.jpg");
        files.add("http://www.petsworld.in/blog/wp-content/uploads/2014/09/lovely-white-cat-image.jpg");
        files.add("https://www.cats.org.uk/media/2197/financial-assistance.jpg");
        files.add("http://cdn130.picsart.com/294072879056201.jpg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/02/s-Kitty-yoga.%281%29.jpg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/02/s-When-theyre-little-damn-if-they-arent-cute.jpg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/02/s-When-my-Angora-cat-gets-cold..jpg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/01/s-Kitty-caboodle..jpeg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/01/s-Mornings-are-rough..jpeg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/01/s-Gasp-for-me.jpg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/01/s-hi-love.jpg");
        files.add("http://www.cutestpaw.com/wp-content/uploads/2016/01/s-Blackwood.jpg");
        return files;
    }
}
