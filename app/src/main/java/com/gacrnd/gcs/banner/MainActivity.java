package com.gacrnd.gcs.banner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    MyAdapter adapter;
    List<Integer> list;
    List<ImageView> dots = new ArrayList<>();
    LinearLayout layout;
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            handler.sendEmptyMessageDelayed(1, 2000);
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            setDotsImgs();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.vp);
        layout = findViewById(R.id.layout);
        initData();
        handler.sendEmptyMessage(1);
        adapter = new MyAdapter(this, list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    viewPager.setCurrentItem(list.size() - 2, false);
                } else if (position == list.size() - 1) {
                    viewPager.setCurrentItem(1, false);
                }
                setDotsImgs();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(R.drawable.ad3);
        list.add(R.drawable.ad1);
        list.add(R.drawable.ad2);
        list.add(R.drawable.ad3);
        list.add(R.drawable.ad1);

        for (int i = 0; i < list.size() - 2; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            params.leftMargin = 5;
            params.rightMargin = 5;
            dots.add(imageView);
            //添加到布局
            layout.addView(imageView);
        }
    }

    public void setDotsImgs() {
        for (int i = 0; i < dots.size(); i++) {
            if (i == viewPager.getCurrentItem() - 1) {
                dots.get(i).setBackgroundResource(R.drawable.ic_fiber_manual_record_black_24dp);
            } else {
                dots.get(i).setBackgroundResource(R.drawable.ic_fiber_manual_record_grey_24dp);
            }
        }
    }
}
