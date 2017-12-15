package com.pfl.component.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.utils.RouteUtils;
import com.pfl.component.R;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = RouteUtils.APP_HOME_FRAGMENT)
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
