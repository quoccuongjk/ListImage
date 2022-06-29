package com.example.fragmentimage.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentimage.LinkImageAdapter;
import com.example.fragmentimage.ListImageAdapter;
import com.example.fragmentimage.R;
import com.example.fragmentimage.databinding.ActivityImageBinding;
import com.example.fragmentimage.db.ImageCameraDB;
import com.example.fragmentimage.entity.ImageCamera;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {
    private ActivityImageBinding binding;
    private ListImageAdapter listImageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listImageAdapter = new ListImageAdapter();
        listImageAdapter.setData(ImageCameraDB.getInstance(this).imageCameraDAO().getListImage());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ImageActivity.this,2);
        binding.rcvImage.setLayoutManager(gridLayoutManager);
        binding.rcvImage.setAdapter(listImageAdapter);
    }
}
