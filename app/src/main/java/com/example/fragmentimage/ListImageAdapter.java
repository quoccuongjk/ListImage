package com.example.fragmentimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentimage.entity.ImageCamera;

import java.util.List;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.ListImageHolder> {
    private List<ImageCamera> imageCameraList;
    public void setData(List<ImageCamera> data){
        this.imageCameraList = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ListImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_camera,parent,false);
        return new ListImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListImageHolder holder, int position) {
        ImageCamera imageCamera = imageCameraList.get(position);
        byte[] image = imageCamera.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        if (imageCameraList != null){
            return imageCameraList.size();
        }
        return 0;
    }

    public class ListImageHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
        public ListImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }
}
