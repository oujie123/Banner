package com.gacrnd.gcs.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author Jack_Ou  created on 2020/9/14.
 */
public class MyAdapter extends PagerAdapter {

    private List<Integer> items;
    private Context mContext;

    public MyAdapter(Context context, List<Integer> items) {
        this.items = items;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(items.get(position));
        container.addView(imageView);
        return imageView;
    }
}
