package com.example.fragmentimage.entity;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImageCamera {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private byte[] image;

    public int getId() {
        return id;
    }

    public ImageCamera(byte[] image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
