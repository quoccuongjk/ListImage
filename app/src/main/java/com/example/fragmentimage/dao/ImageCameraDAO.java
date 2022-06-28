package com.example.fragmentimage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fragmentimage.entity.ImageCamera;

import java.util.List;

@Dao
public interface ImageCameraDAO {
    @Insert
   void insert (ImageCamera imageCamera);
    @Delete
   void delete (ImageCamera imageCamera);
    @Update
    void  update(ImageCamera imageCamera);
    @Query("SELECT * FROM ImageCamera")
    List<ImageCamera> getListImage();
}
