package com.pfl.module2.mvp.module2;

import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.exception.ApiException;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by rocky on 2018/1/2.
 */

public class Module2Persenter {

    private RetrofitService service;
    private Module2View view;

    @Inject
    public Module2Persenter(RetrofitService service, Module2View view) {
        this.service = service;
        this.view = view;
    }


    public void requestData() {
        service.getToken("client_credentials", "282307895618", "b9c6c8f954dbbf7274910585a95efce1")
                .compose(RxSchedulers.<HttpResponse<AccessToken>>compose())
                .subscribe(new Observer<HttpResponse<AccessToken>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResponse<AccessToken> accessTokenHttpResponse) {

                        if (accessTokenHttpResponse.isSuccess()) {
                            view.onSuccess(accessTokenHttpResponse.getData().getAccess_token());
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
}
