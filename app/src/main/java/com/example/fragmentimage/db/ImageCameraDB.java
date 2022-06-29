package com.example.fragmentimage.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fragmentimage.dao.ImageCameraDAO;
import com.example.fragmentimage.entity.ImageCamera;

@Database(entities = ImageCamera.class, version = 2)
public abstract class ImageCameraDB extends RoomDatabase {
    private static final String DATABASE_NAME = "image.db";
    private static ImageCameraDB instance;

    public static synchronized ImageCameraDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ImageCameraDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return instance;
    }

    public abstract ImageCameraDAO imageCameraDAO();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "ALTER TABLE ImageCamera ADD COLUMN imagePath2 TEXT");
        }
    };
}

