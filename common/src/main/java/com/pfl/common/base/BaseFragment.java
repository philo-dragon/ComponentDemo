package com.pfl.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.App;
import com.pfl.component.R;

/**
 * Created by rocky on 2017/12/27.
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mContext;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.base_layout, container, false);

        if (isNeedToolBar()) {
            ViewStub titleStub = view.findViewById(R.id.titleStub);
            titleStub.inflate();
        }
        ViewStub viewStub = view.findViewById(R.id.viewStub);
        viewStub.setLayoutResource(getContextView());
        viewStub.inflate();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        componentInject(App.getInstance(BaseApplication.class).getAppComponent());
        initView(view);
        initEvent();
    }

    /**
     * 依赖注入的入口
     */
    protected void componentInject(AppComponent appComponent) {
    }

    /**
     * layoutId
     *
     * @return
     */
    protected abstract int getContextView();

    /**
     * 是否需要ToolBar
     *
     * @return
     */
    protected abstract boolean isNeedToolBar();

    /**
     * 初始化view
     */
    protected abstract void initView(View view);

    /**
     * 初始化event
     */
    protected abstract void initEvent();
}
