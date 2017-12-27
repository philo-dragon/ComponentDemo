package com.pfl.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pfl.component.R;

/**
 * Created by rocky on 2017/12/27.
 */

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflater.inflate(R.layout.base_layout, null, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
