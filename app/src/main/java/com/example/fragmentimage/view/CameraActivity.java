package com.example.fragmentimage.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentimage.databinding.ActivityCameraBinding;
import com.example.fragmentimage.db.ImageCameraDB;
import com.example.fragmentimage.entity.ImageCamera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {
    public static int KEY_CAPTURE = 1;
    private ActivityCameraBinding binding;
    private Bitmap currentImgFromCamera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.btnSaveImage.setEnabled(false);
        binding.btnCapture.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, KEY_CAPTURE);
        });

        binding.btnSaveImage.setOnClickListener(view -> {
            //insertImage();
            save();
            view.setEnabled(false);
        });
        binding.btnListImage.setOnClickListener(view -> {
            startActivity(new Intent(CameraActivity.this, ImageActivity.class));
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == KEY_CAPTURE && resultCode == RESULT_OK && data != null) {
            currentImgFromCamera = (Bitmap) data.getExtras().get("data");
            binding.imageView.setImageBitmap(currentImgFromCamera);
            binding.btnSaveImage.setEnabled(true);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //    private void insertImage() {
//        if (imageView != null) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
//            Bitmap bitmap = bitmapDrawable.getBitmap();
//            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
//            //
//            String root = Environment.getExternalStorageState().toString();
//
//            byte[] image = byteArray.toByteArray();
//            ImageCamera imageCamera = new ImageCamera(image);
//            ImageCameraDB.getInstance(this).imageCameraDAO().insert(imageCamera);
//            Toast.makeText(CameraActivity.this,"thanh cong",Toast.LENGTH_SHORT).show();
//
//        }
//    }
    @SuppressLint("SimpleDateFormat")
    public void save() {
        if (currentImgFromCamera == null) {
            return;
        }
        // name file = 2022-06-28-17-11-59.jpg

        String root = getFilesDir().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Date currentDate = new Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(currentDate);
        String fname = "cuongfile_" + timeStamp + ".jpg";
        File file = new File(myDir, fname);

        if (file.exists())
            file.delete();

        try {
            FileOutputStream out = new FileOutputStream(file);
            currentImgFromCamera.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("CUONG", "SAVE FILE ERRROR = " + e.getMessage());
            return;
        }

        ImageCamera imageCamera = new ImageCamera(file.getName());
        ImageCameraDB.getInstance(this).imageCameraDAO().insert(imageCamera);
        Toast.makeText(CameraActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
