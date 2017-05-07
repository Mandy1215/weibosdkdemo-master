package com.sina.weibo.sdk.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

/**
 * Created by Steve on 2017/4/15.
 */

public class MainTestActivity extends AppCompatActivity {

    private Controller mTabController;
    private HomePageFragment mHomePageFragment;
    private MePageFragment mMePageFragment;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_main_test);
        initView();
        setOnClickListeners();
    }

    private void initView() {
        mHomePageFragment = HomePageFragment.newInstance();
        mMePageFragment = MePageFragment.newInstance();
        fragments = new Fragment[]{mHomePageFragment, mMePageFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomePageFragment, mHomePageFragment.getClass().getName())
                .add(R.id.fragment_container, mMePageFragment, mMePageFragment.getClass().getName()).commit(); // 添加显示第一个fragment
        PagerBottomTabLayout mBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.bottom_tabstrip);
        //构建导航栏,得到Controller进行后续控制
        mTabController = mBottomTabLayout.builder()
                .addTabItem(R.drawable.ic_logo, "首页", getResources().getColor(R.color.colorPrimary))
                .addTabItem(R.drawable.ic_login_button_blue_normal, "我", getResources().getColor(R.color.colorAccent))
                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .setDefaultColor(getResources().getColor(R.color.colorPrimary))
                .build();
    }

    private void setOnClickListeners() {

        mTabController.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {

                FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
                trx.hide(mHomePageFragment).hide(mMePageFragment);
                if (!fragments[index].isAdded()) {
                    trx.add(R.id.fragment_container, fragments[index]);
                }
                trx.show(fragments[index]).commitAllowingStateLoss();

            }

            @Override
            public void onRepeatClick(int index, Object tag) {
            }
        });
    }
}
