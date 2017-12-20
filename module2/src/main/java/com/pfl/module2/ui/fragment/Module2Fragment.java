package com.pfl.module2.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.exception.ApiException;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.RouteUtils;
import com.pfl.component.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = RouteUtils.MODULE2_FRAGMENT)
public class Module2Fragment extends Fragment {


    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment textView
        View view = inflater.inflate(R.layout.fragment_module2, container, false);
        textView = view.findViewById(R.id.textView);

        requestData();
        return view;
    }

    private void requestData() {

        RetrofitFactory.getInstance().getService().getToken("", "", "")
                .compose(RxSchedulers.<HttpResponse<AccessToken>>compose())
                .subscribe(new Observer<HttpResponse<AccessToken>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResponse<AccessToken> accessTokenHttpResponse) {

                        if (accessTokenHttpResponse.isSuccess()) {
                            textView.setText(accessTokenHttpResponse.getData().getAccessToken());
                        } else {
                            onError(new ApiException(accessTokenHttpResponse.getCode(), accessTokenHttpResponse.getMsg()));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
