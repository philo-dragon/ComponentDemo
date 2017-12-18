package com.pfl.common.http;

import android.content.Context;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.exception.NoNetworkException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> compose() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                               /* if (!Utils.isNetworkConnected()) {
                                    Toast.makeText(context, R.string.toast_network_error, Toast.LENGTH_SHORT).show();
                                }*/
                                // 没有网络
                                throw new NoNetworkException();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private void login(Context context, String userId, String password) {
        Observable<HttpResponse<UserInfo>> observable = RetrofitFactory.getInstance().login(userId, password);
        observable.
                compose(RxSchedulers.compose()).
                subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
    }

}
