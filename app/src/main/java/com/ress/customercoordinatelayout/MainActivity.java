package com.ress.customercoordinatelayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private ViewPager mVp;
    private String[] mTitles;
    private List<Fragment> mFragments;
    private TabLayout mSpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();

    }

    private void initEvent() {

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mSpi.setupWithViewPager(mVp);


    }

    private void initData() {
        mTitles = new String[]{"first", "second", "third"};

        mFragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ContentFragment e = new ContentFragment();
             mFragments.add(e);
        }
    }


    private void initView() {
        mSpi = (TabLayout) findViewById(R.id.spi);
        mVp = (ViewPager) findViewById(R.id.vp);

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }


}

