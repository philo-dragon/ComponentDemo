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
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.CommonDatabindingToolbarLayoutBinding;
import com.pfl.module_user.databinding.ModuleUserActivityLoginBinding;
import com.pfl.module_user.utils.AccountPasswordUtil;

@Route(path = RouteUtils.MODULE_USER_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<ModuleUserActivityLoginBinding> {

    private ImageLoader imageLoader;
    private ImageView mCollectView;

    @Override
    public void componentInject(AppComponent appComponent) {
        imageLoader = appComponent.getImageLoader();
    }

    public int getContextView() {
        return R.layout.module_user_activity_login;
    }

    public void initView(View view) {
        imageLoader.loadImage(this, ImageConfigImpl.
                builder().url("http://g.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539f09733493187e950342ab095.jpg").
                imageView(mBinding.imgUser).
                build());

        /*mCollectView = (ImageView) mBinding.inToolbar.titleBar.addAction(new TitleBar.ImageAction(R.mipmap.collect) {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了收藏");
                mCollectView.setImageResource(R.mipmap.fabu);
            }
        });

        mBinding.inToolbar.titleBar.addAction(new TitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了发布");
            }
        });*/

        AccountPasswordUtil.setAccount(mBinding.tvAccount, mBinding.tilAccount);
        AccountPasswordUtil.setPassword(mBinding.tvPassword, mBinding.tilPassword);

        mBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = mBinding.tvAccount.getText().toString().trim();
                String password = mBinding.tvPassword.getText().toString().trim();
                if (AccountPasswordUtil.veriftyAccount(account.toString(), mBinding.tilAccount) &&
                        AccountPasswordUtil.veriftyPassword(password.toString(), mBinding.tilPassword)) {

                    ToastUtils.showShort("登录成功");
                    finish();
                }
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

}
