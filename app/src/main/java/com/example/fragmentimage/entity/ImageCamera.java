package com.example.fragmentimage.entity;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.File;

@Entity
public class ImageCamera {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String imagePath;

    private String imagePath2;

    public int getId() {
        return id;
    }

    public ImageCamera() {
    }

    public ImageCamera(String image) {
        this.imagePath = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public File getImageFile(Context context) {
        String root = context.getFilesDir().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        File file = new File(myDir, imagePath);
        return file;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }
}
