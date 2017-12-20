package com.pfl.component.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.pfl.common.utils.RouteUtils;
import com.pfl.component.R;
import com.pfl.component.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private List<Fragment> mList; //ViewPager的数据源
    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private BadgeItem badgeItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        processLogic();
    }

    private void initView() {

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        //设置被选中时隐藏角标
        badgeItem = new BadgeItem()
                .setHideOnSelect(true) //设置被选中时隐藏角标
                .setBackgroundColor(Color.RED)
                .setText("99");
        /**
         * 设置模式
         * 1、BottomNavigationBar.MODE_DEFAULT 默认
         * 如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         *
         * 2、BottomNavigationBar.MODE_FIXED 固定大小
         * 填充模式，未选中的Item会显示文字，没有换挡动画。
         *
         * 3、BottomNavigationBar.MODE_SHIFTING 不固定大小
         * 换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        /**
         * 设置背景的样式
         * 1、BottomNavigationBar.BACKGROUND_STYLE_DEFAULT 默认
         * 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。
         * 如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
         *
         * 2、BottomNavigationBar.BACKGROUND_STYLE_STATIC 导航条的背景色是白色，
         * 加上setBarBackgroundColor（）可以设置成你所需要的任何背景颜色
         * 点击的时候没有水波纹效果
         *
         * 3、BottomNavigationBar.BACKGROUND_STYLE_RIPPLE 导航条的背景色是你设置的处于选中状态的
         * Item的颜色（ActiveColor），也就是setActiveColorResource这个设置的颜色
         * 点击的时候有水波纹效果
         */
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置导航条背景颜色
        //在BACKGROUND_STYLE_STATIC下，表示整个容器的背景色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示选中Item的图标和文本颜色。默认 Color.WHITE
        bottomNavigationBar.setBarBackgroundColor(R.color.black);
        //选中时的颜色,在BACKGROUND_STYLE_STATIC下，表示选中Item的图标和文本颜色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示整个容器的背景色。默认Theme's Primary Color
        //bottomNavigationBar.setActiveColor(R.color.black);
        //未选中时的颜色，表示未选中Item中的图标和文本颜色。默认为 Color.LTGRAY
        //bottomNavigationBar.setInActiveColor("#FFFFFF");


        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "首页").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "音乐").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "电影").setActiveColorResource(R.color.white).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "游戏").setActiveColorResource(R.color.white))
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成

        //如果使用颜色的变化不足以展示一个选项Item的选中与非选中状态，
        // 可以使用BottomNavigationItem.setInActiveIcon()方法来设置。
//        new BottomNavigationItem(R.drawable.ic_home_white_24dp, "公交")//这里表示选中的图片
//                .setInactiveIcon(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));//非选中的图片

        mList = new ArrayList<>();

        mList.add((Fragment) ARouter.getInstance().build(RouteUtils.APP_HOME_FRAGMENT).navigation());
        mList.add((Fragment) ARouter.getInstance().build(RouteUtils.MODULE1_FRAGMENT).navigation());
        mList.add((Fragment) ARouter.getInstance().build(RouteUtils.MODULE2_FRAGMENT).navigation());
        mList.add((Fragment) ARouter.getInstance().build(RouteUtils.MODULE_USER_FRAGMENT).navigation());

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), mList);
        viewPager.setAdapter(mainAdapter); //视图加载适配器


    }

    private void setListener() {

    }

    private void processLogic() {
    }


    @Override
    public void onTabSelected(int position) {//未选中 -> 选中
        //tab被选中
        viewPager.setCurrentItem(position, false);

    }

    @Override
    public void onTabUnselected(int position) {//选中 -> 未选中

    }

    @Override
    public void onTabReselected(int position) {//选中 -> 选中

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //ViewPager滑动
        bottomNavigationBar.selectTab(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
