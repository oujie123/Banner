package com.gacrnd.gcs.viewpager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


/**
 * 同学们：这是水平方向滑动的Activity
 */
public class HorizontalScrollingActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, HorizontalScrollingActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrolling);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        viewPager2.setAdapter(viewPagerAdapter);
    }
}
