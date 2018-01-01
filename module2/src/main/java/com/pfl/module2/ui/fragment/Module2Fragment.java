package com.pfl.module2.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.base.BaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.exception.ApiException;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;
import com.pfl.module2.di.module2.DaggerModule2Component;
import com.pfl.module2.di.module2.Module2Module;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = RouteUtils.MODULE2_FRAGMENT)
public class Module2Fragment extends BaseFragment {

    private TextView textView;

    @Inject
    RetrofitService service;

    private void requestData() {

        service.getToken("client_credentials", "282307895618", "b9c6c8f954dbbf7274910585a95efce1")
                .compose(RxSchedulers.<HttpResponse<AccessToken>>compose())
                .subscribe(new Observer<HttpResponse<AccessToken>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResponse<AccessToken> accessTokenHttpResponse) {

                        if (accessTokenHttpResponse.isSuccess()) {
                            textView.setText(accessTokenHttpResponse.getData().getAccess_token());
                        } else {
                            onError(new ApiException(accessTokenHttpResponse.getCode(), accessTokenHttpResponse.getMsg()));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerModule2Component.builder().appComponent(appComponent)
                .module2Module(new Module2Module())
                .build()
                .inject(this);
    }

    @Override
    protected int getContextView() {
        return R.layout.fragment_module2;
    }

    @Override
    protected boolean isNeedToolBar() {
        return false;
    }

    @Override
    protected void initView(View view) {
        textView = view.findViewById(R.id.textView);
        requestData();
    }

    @Override
    protected void initEvent() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatusBarUtil.darkMode(mContext, false);
            }
        });
    }
}
