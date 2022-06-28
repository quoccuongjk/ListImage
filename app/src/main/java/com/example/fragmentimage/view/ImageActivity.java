package com.example.fragmentimage.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentimage.LinkImageAdapter;
import com.example.fragmentimage.ListImageAdapter;
import com.example.fragmentimage.R;
import com.example.fragmentimage.db.ImageCameraDB;
import com.example.fragmentimage.entity.ImageCamera;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {
    private RecyclerView rcvView;
    private ListImageAdapter listImageAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rcvView = findViewById(R.id.rcv_image);
        listImageAdapter = new ListImageAdapter();
        listImageAdapter.setData(ImageCameraDB.getInstance(this).imageCameraDAO().getListImage());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ImageActivity.this,2);
        rcvView.setLayoutManager(gridLayoutManager);
        rcvView.setAdapter(listImageAdapter);
    }
}
