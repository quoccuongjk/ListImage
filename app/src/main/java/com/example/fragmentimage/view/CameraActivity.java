package com.example.fragmentimage.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentimage.R;
import com.example.fragmentimage.db.ImageCameraDB;
import com.example.fragmentimage.entity.ImageCamera;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import retrofit2.http.Url;

public class CameraActivity extends AppCompatActivity {
    Button button,btnLuu,btnListImage;
    ImageView imageView;
    public static int KEY=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        button = findViewById(R.id.button);
        btnLuu = findViewById(R.id.btn_save_image);
        btnListImage = findViewById(R.id.btn_list_image);
        imageView = findViewById(R.id.imageView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnLuu.setEnabled(false);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,KEY);
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertImage();
                btnLuu.setEnabled(false);
            }
        });
        btnListImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent( CameraActivity.this,ImageActivity.class);
                startActivity(intent1);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == KEY && resultCode ==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            btnLuu.setEnabled(true);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void insertImage(){
        if (imageView != null){
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
            byte[] image = byteArray.toByteArray();
            ImageCamera imageCamera = new ImageCamera(image);
            ImageCameraDB.getInstance(this).imageCameraDAO().insert(imageCamera);
            Toast.makeText(CameraActivity.this,"thanh cong",Toast.LENGTH_SHORT).show();

        }
    }
}
