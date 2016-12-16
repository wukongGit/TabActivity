package com.suncheng.tabsample;

import android.graphics.drawable.Drawable;

import com.suncheng.commonlib.CallBackFragment;
import com.suncheng.commonlib.CommonTabActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends CommonTabActivity {
    private List<String> mTitles = Arrays.asList("tan", "ssd", "hbu");
    private List<Drawable> mSelected = new ArrayList<>();
    private List<Drawable> mUnSelected = new ArrayList<>();
    private List<CallBackFragment> mFragments = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        mFragments.add(HomeFragment.newInstance("heeoihoihoi"));
        mFragments.add(HomeFragment.newInstance("gyuwgialu"));
        mFragments.add(HomeFragment.newInstance("yguagwued"));
        mSelected.add(getResources().getDrawable(R.mipmap.tab_home_icon_selected));
        mSelected.add(getResources().getDrawable(R.mipmap.tab_message_icon_selected));
        mSelected.add(getResources().getDrawable(R.mipmap.tab_my_icon_selected));
        mUnSelected.add(getResources().getDrawable(R.mipmap.tab_home_icon_unselected));
        mUnSelected.add(getResources().getDrawable(R.mipmap.tab_message_icon_unselected));
        mUnSelected.add(getResources().getDrawable(R.mipmap.tab_my_icon_unselected));
        //setViews(mTitles, mFragments, mSelected, mUnSelected);
        setViews(mTitles, mFragments, mSelected, mUnSelected, 0xffFF4081, 0xffffffff, R.color.colorPrimary);
    }
}
