package com.example.fragmentimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentimage.databinding.ItemLinkBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LinkImageAdapter extends RecyclerView.Adapter<LinkImageHolder> {
    private List<LinkImage> linkImages;
    private IClickItem iClickItem;
    private Context mContext;
    public void setData(Context context, List<LinkImage> list, IClickItem iClickItem){
        this.mContext = context;
        this.linkImages = list;
        this.iClickItem = iClickItem;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LinkImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLinkBinding itemBinding = ItemLinkBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new LinkImageHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkImageHolder holder, int position) {
        final LinkImage linkImage = linkImages.get(position);
        if (linkImage ==null){
            return;
        }
        Picasso.with(this.mContext)
                .load(linkImage.getDownload_url())
                .resize(300, 300).centerCrop()
                .into(holder.itemBinding.image);

        holder.itemBinding.imageId.setText(linkImage.getId());
        holder.itemBinding.author.setText(linkImage.getAuthor());
        holder.itemBinding.height.setText(""+linkImage.getHeight());
        holder.itemBinding.width.setText(String.valueOf(linkImage.getWidth()));
        holder.itemBinding.imgUrl.setText(linkImage.getUrl());
        holder.itemBinding.downloadUrl.setText(linkImage.getDownload_url());
        holder.itemBinding.getRoot().setOnClickListener(view ->
                iClickItem.onClickItem(linkImage)
        );
    }

    @Override
    public int getItemCount() {
        if (linkImages == null){
            return 0;
        }
        return linkImages.size();
    }
}

class LinkImageHolder extends RecyclerView.ViewHolder {
    ItemLinkBinding itemBinding;

    public LinkImageHolder(@NonNull ItemLinkBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
    }
}
