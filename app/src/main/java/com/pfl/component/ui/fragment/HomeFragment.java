package com.pfl.component.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = RouteUtils.APP_HOME_FRAGMENT)
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment tv_textview
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = view.findViewById(R.id.tv_textview);
        UserInfo userInfo = ModuleUserRouteService.getUserInfo();
        if (null != userInfo) {
            textView.setText(userInfo.getName() + " , " + userInfo.getMobileNum());
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatusBarUtil.darkMode(getActivity(), true);
            }
        });

        StatusBarUtil.setPadding(getActivity(), view.findViewById(R.id.toolbar));

        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });


        return view;
    }

}
