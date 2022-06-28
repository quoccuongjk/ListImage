package com.example.fragmentimage.entity;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImageCamera {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String image;

    public int getId() {
        return id;
    }

    public ImageCamera(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
