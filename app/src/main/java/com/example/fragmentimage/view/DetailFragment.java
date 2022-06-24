package com.example.fragmentimage.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;


import com.example.fragmentimage.Constant;
import com.example.fragmentimage.LinkImage;
import com.example.fragmentimage.R;
import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image,container,false);
        imageView = view.findViewById(R.id.imageView);
        Bundle bundle = getArguments();
        LinkImage linkImage = (LinkImage) bundle.get(Constant.key);
        String link = linkImage.getDownload_url();
        Picasso.with(getActivity()).load(link).into(imageView);
        return view;
    }
}
