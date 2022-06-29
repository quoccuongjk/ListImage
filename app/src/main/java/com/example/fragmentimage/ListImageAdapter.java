package com.example.fragmentimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentimage.entity.ImageCamera;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageHolder> {
    private List<ImageCamera> imageCameraList = new ArrayList<>();

    public void setData(List<ImageCamera> data) {
        this.imageCameraList.clear();
        this.imageCameraList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_camera, parent, false);
        return new ListImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListImageHolder holder, int position) {
        ImageCamera imageCamera = imageCameraList.get(position);
        Context context = holder.imageView.getContext();
        Picasso.with(context)
                .load(imageCamera.getImageFile(context))
                .into(holder.imageView);


        //holder.imageView.setImageBitmap("load bitmap from file");
    }

    @Override
    public int getItemCount() {
        return imageCameraList.size();
    }
}

class ListImageHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public ListImageHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_image);
    }
}
