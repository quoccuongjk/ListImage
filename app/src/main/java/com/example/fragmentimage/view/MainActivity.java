package com.example.fragmentimage.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


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
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_photograph:
                Intent intent = new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent);
                break;
            case  R.id.menu_read_file:
                // todo: lam ve chuc nang doc file
                break;
            case R.id.menu_picture:
                Intent intent1 = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent1);

        }
        return super.onOptionsItemSelected(item);
    }
}