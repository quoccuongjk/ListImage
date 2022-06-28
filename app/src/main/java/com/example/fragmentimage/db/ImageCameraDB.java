package com.example.fragmentimage.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fragmentimage.dao.ImageCameraDAO;
import com.example.fragmentimage.entity.ImageCamera;

@Database(entities = ImageCamera.class,version = 1)
public abstract class ImageCameraDB extends RoomDatabase {
    private static final String DATABASE_NAME = "image.db";
    private static ImageCameraDB instance;
    public static synchronized ImageCameraDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ImageCameraDB.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract ImageCameraDAO imageCameraDAO();
}
