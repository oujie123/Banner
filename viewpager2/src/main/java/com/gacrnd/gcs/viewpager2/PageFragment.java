package com.gacrnd.gcs.viewpager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 同学们：此Fragment就等于 TAB0 TAB1 TAB2 TAB3 那 四个Fragment
 */
public class PageFragment extends Fragment {
    private final static String TAG = PageFragment.class.getSimpleName();

    private static final String COLORS = "colors";
    private static final String POSITION = "position";

    public static PageFragment newInstance(List<Integer> colors, int position) {
        Bundle args = new Bundle();
        args.putSerializable(COLORS, ((ArrayList<Integer>) colors));
        args.putInt(POSITION, position);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private List<Integer> mColors;
    private int mPosition;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColors = (List<Integer>) getArguments().getSerializable(COLORS);
            mPosition = getArguments().getInt(POSITION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout container = view.findViewById(R.id.container);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        container.setBackgroundResource(mColors.get(mPosition));
        tvTitle.setText("Item " + mPosition);
    }
}
