package com.example.fragmentimage.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentimage.ApiService;
import com.example.fragmentimage.IClickItem;
import com.example.fragmentimage.LinkImage;
import com.example.fragmentimage.LinkImageAdapter;
import com.example.fragmentimage.R;
import com.example.fragmentimage.databinding.FragmentLinkBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListImageFragment extends Fragment {
    private RecyclerView rcvLink;
    private LinkImageAdapter linkImageAdapter;
    private MainActivity mMainActivity;
    private IClickItem callback = new IClickItem() {
        @Override
        public void onClickItem(LinkImage linkImage) {
            mMainActivity.showImageFragment(linkImage);
        }

        @Override
        public void onClickItem2() {

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_link,container,false);
        mMainActivity = (MainActivity) getActivity();
        FragmentLinkBinding binding = FragmentLinkBinding.inflate(inflater);
        //rcvLink = view.findViewById(R.id.rcv_link);
        linkImageAdapter = new LinkImageAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        binding.rcvLink.addItemDecoration(itemDecoration);
        binding.rcvLink.setLayoutManager(gridLayoutManager);
        binding.rcvLink.setAdapter(linkImageAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiService.apiService.convertLink().enqueue(new Callback<ArrayList<LinkImage>>() {
            @Override
            public void onResponse(Call<ArrayList<LinkImage>> call, Response<ArrayList<LinkImage>> response) {
                ArrayList<LinkImage> linkImages = response.body();
                // todo update list to UI
                onLoadDone(linkImages);
            }

            @Override
            public void onFailure(Call<ArrayList<LinkImage>> call, Throwable t) {
                // to do
            }
        });
    }


    private void onLoadDone(ArrayList<LinkImage> linkImages) {
        linkImageAdapter.setData(getActivity(), linkImages, callback);
    }
}
