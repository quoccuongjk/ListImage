package com.example.fragmentimage.view;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.fragmentimage.Constant;
import com.example.fragmentimage.LinkImage;
import com.example.fragmentimage.databinding.FragmentImageBinding;
import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {
    FragmentImageBinding binding;
    ScaleGestureDetector scaleGestureDetector;
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentImageBinding.inflate(inflater);

        Bundle bundle = getArguments();
        LinkImage linkImage = (LinkImage) bundle.get(Constant.key);
        String link = linkImage.getDownload_url();
        Picasso.with(getActivity()).load(link).into(binding.imageView);
        scaleGestureDetector = new ScaleGestureDetector(getActivity(),new MyGesture());
        binding.imageView.setOnTouchListener((view, motionEvent) -> {
            scaleGestureDetector.onTouchEvent(motionEvent);
            return true;
        });

        return binding.getRoot();
    }
    class MyGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        float scale = 1.0f, scaleStart =0, scaleEnd = 0;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            binding.imageView.setScaleX(scale);
            binding.imageView.setScaleY(scale);
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            scaleStart = scale;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            scaleEnd = scale;
            super.onScaleEnd(detector);
        }
    }
}
