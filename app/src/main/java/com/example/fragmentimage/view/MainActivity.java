package com.example.fragmentimage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.fragmentimage.Constant;
import com.example.fragmentimage.LinkImage;
import com.example.fragmentimage.R;
import com.example.fragmentimage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getFragmentManager();
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListImageFragment listImageFragment = new ListImageFragment();
        fragmentTransaction.add(R.id.frameLayout, listImageFragment);
        fragmentTransaction.commit();
    }
    public void showImageFragment(LinkImage linkImage){

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.key, linkImage);
        detailFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayout, detailFragment)
                .addToBackStack("fragment2");
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        if (fragmentManager.getBackStackEntryCount()>0){
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}