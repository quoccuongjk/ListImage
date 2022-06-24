package com.example.fragmentimage;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LinkImageAdapter extends RecyclerView.Adapter<LinkImageAdapter.LinkImageHolder> {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link,parent,false);
        return new LinkImageHolder(view);
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
                .into(holder.imageView);

        holder.txtid.setText(linkImage.getId());
        holder.txtauthor.setText(linkImage.getAuthor());
        holder.txtheight.setText(""+linkImage.getHeight());
        holder.txtwidth.setText(String.valueOf(linkImage.getWidth()));
        holder.txturl.setText(linkImage.getUrl());
        holder.txtdownload_url.setText(linkImage.getDownload_url());
        holder.linearLayout.setOnClickListener(view ->
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

    public class LinkImageHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtauthor,txtwidth,txtheight,txturl,txtdownload_url;
        ImageView imageView;
        LinearLayout linearLayout;
        public LinkImageHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.id);
            txtauthor = itemView.findViewById(R.id.author);
            txtwidth = itemView.findViewById(R.id.width);
            txtheight = itemView.findViewById(R.id.height);
            txturl = itemView.findViewById(R.id.url);
            txtdownload_url = itemView.findViewById(R.id.download_url);
            linearLayout = itemView.findViewById(R.id.linear);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
