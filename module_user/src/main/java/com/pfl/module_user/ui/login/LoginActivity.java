package com.pfl.module_user.ui.login;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.imageloader.glide.ImageConfigImpl;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.component.R;

@Route(path = RouteUtils.MODULE_USER_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity {


    private ImageLoader imageLoader;
    private ImageView mCollectView;

    @Override
    public void componentInject(AppComponent appComponent) {
        imageLoader = appComponent.getImageLoader();
    }

    @Override
    public int getContextView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {

        ImageView imgUser = findViewById(R.id.img_user);
        imageLoader.loadImage(this, ImageConfigImpl.
                builder().url("http://g.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539f09733493187e950342ab095.jpg").
                imageView(imgUser).
                build());

        TitleBar titleBar = findViewById(R.id.title_bar);

        mCollectView = (ImageView) titleBar.addAction(new TitleBar.ImageAction(R.mipmap.collect) {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了收藏");
                mCollectView.setImageResource(R.mipmap.fabu);
            }
        });

        titleBar.addAction(new TitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了发布");
            }
        });

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }

    @Override
    public void finishActivity() {
        super.finishActivity();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
}
