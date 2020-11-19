package com.example.labster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager2 mViewPager;
    MyViewPagerAdapter mMyViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewPager = findViewById(R.id.view_pager);
        mMyViewPagerAdapter = new MyViewPagerAdapter(this);

        mViewPager.setAdapter(mMyViewPagerAdapter);

    }


    public class MyViewPagerAdapter extends FragmentStateAdapter {

        public MyViewPagerAdapter(MainActivity ma) {
            super(ma);
        }

        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}