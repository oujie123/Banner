package com.gacrnd.gcs.resolvelazyload.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gacrnd.gcs.resolvelazyload.FragmentDelegater;


/**
 * BaseFragment
 *  TODO 第一版
 */
public abstract class LazyFragment1 extends Fragment {

    FragmentDelegater mFragmentDelegater;
    private View rootView = null;
    private boolean isViewCreated = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        E("onCreateView: ");
        if (rootView == null) {
            //让子类完成初始化布局   getLayoutRes()获得布局id
            rootView = inflater.inflate(getLayoutRes(),container, false);
        }
        // TODO 解决奔溃1.1   原因：在populate中   适配器的instantiateItem中会先调用setUserVisibleHint(false)
        isViewCreated  = true;
        // 初始化控件 findvxxx   子类完成
        initView(rootView);
        return rootView;
    }

    // TODO 判断 Fragment 是否可见 【第一版 1.1】
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        E("setUserVisibleHint");

        if (isViewCreated) {
            if (isVisibleToUser) {
                dispatchUserVisibleHint(true);
            } else {
                dispatchUserVisibleHint(false);
            }
        }
    }

    // TODO 分发 可见 不可见 的动作 【第一版 1.2】
    private void dispatchUserVisibleHint(boolean visibleState) {
        if (visibleState) {
            // 加载数据
            onFragmentLoad();
        } else {
            // 停止一切操作
            onFragmentLoadStop();
        }
    }

    /*********************以下为公共代码*****************************************/
    // 让子类完成，初始化布局，初始化控件
    protected abstract void initView(View rootView);
    protected abstract int getLayoutRes();

    // -->>>停止网络数据请求
    //此处没有用抽象的方法是可以让子类也成为普通的Fragment
    public void onFragmentLoadStop() {
        E("onFragmentLoadStop");
    }

    // -->>>加载网络数据请求
    public void onFragmentLoad() {
        E("onFragmentLoad");
    }

    @Override
    public void onResume() {
        super.onResume();
        E("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        E("onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        E("onDestroyView");
    }

    // 工具相关而已
    public void setFragmentDelegater(FragmentDelegater fragmentDelegater) {
        mFragmentDelegater = fragmentDelegater;
    }
    private void E(String  string) {
        if (mFragmentDelegater != null) {
            mFragmentDelegater.dumpLifeCycle(string);
        }
    }
}
