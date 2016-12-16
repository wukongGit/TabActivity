package com.suncheng.commonlib;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.suncheng.commonlib.view.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suncheng on 2016/12/16.
 */
public class CommonTabActivity extends AppCompatActivity {
    private LinearLayout mBottom;
    private int mItemCount;
    private List<TabLayout> mTabItems;
    private List<CallBackFragment> mFragments;
    private CallBackFragment mCurrentFragment;
    private int mCurrentSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        mBottom = (LinearLayout) findViewById(R.id.tab_bottom);
        mTabItems = new ArrayList<>();
        initView();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();
            switchFragment(mFragments.get(index));
            mTabItems.get(mCurrentSelected).setSelect(false);
            mTabItems.get(index).setSelect(true);
            mCurrentSelected = index;

        }
    };

    /**
     * init view, must call setViews() to set up view
     * */
    protected void initView() {}
    protected void setViews(List<String> titles, List<CallBackFragment> fragments) {
        setViews(titles, fragments, null, null, 0, 0, 0);
    }
    protected void setViews(List<CallBackFragment> fragments, List<Drawable> selected, List<Drawable> unSelected) {
        setViews(null, fragments, selected, unSelected, 0, 0, 0);
    }
    protected void setViews(List<String> titles, List<CallBackFragment> fragments, List<Drawable> selected, List<Drawable> unSelected) {
        setViews(titles, fragments, selected, unSelected, 0, 0, 0);
    }
    protected void setViews(List<String> titles, List<CallBackFragment> fragments, List<Drawable> selected, List<Drawable> unSelected, int selectedColor, int unSelectedColor, int tabBackground){
        if(fragments == null) {
            throw new RuntimeException("fragments should not be null");
        }
        mFragments = fragments;
        mItemCount = fragments.size();
        if(mItemCount < 1) {
            throw new RuntimeException("fragments should not be null");
        }
        int selectedLength = selected == null ? 0 : selected.size();
        int unSelectedLength = unSelected == null ? 0 : unSelected.size();
        if(selectedLength != unSelectedLength) {
            throw new RuntimeException("selected length and unselected length should be equal");
        }
        if(titles == null && selectedLength == 0) {
            throw new RuntimeException("titles or icons should not both null");
        }
        int titleLength = titles == null ? 0 : titles.size();
        if(Math.max(titleLength, selectedLength) != mItemCount) {
            throw new RuntimeException("tab items and fragments should be equal");
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        for(int i = 0; i < mItemCount; i++) {
            TabLayout tabLayout = (TabLayout) LayoutInflater.from(this).inflate(R.layout.tab_button, null);
            String title;
            if(titles != null) {
                title = titles.get(i);
                tabLayout.setText(title);
            }
            Drawable selectedIcon;
            Drawable unSelectedIcon;
            if(selected != null) {
                selectedIcon = selected.get(i);
                unSelectedIcon = unSelected.get(i);
                tabLayout.setBgSelected(selectedIcon);
                tabLayout.setBgUnselected(unSelectedIcon);
            }
            if(selectedColor != 0) {
                tabLayout.setTextSelected(selectedColor);
            }
            if(unSelectedColor != 0) {
                tabLayout.setTextUnselected(unSelectedColor);
            }
            tabLayout.setSelect(false);
            tabLayout.setTag(i);
            tabLayout.setOnClickListener(mOnClickListener);
            mTabItems.add(tabLayout);
        }
        if(tabBackground != 0) {
            mBottom.setBackgroundResource(tabBackground);
        }
        generateTabs();
        switchFragment(mFragments.get(0));
        mTabItems.get(0).setSelect(true);
    }

    private void generateTabs() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        for(LinearLayout layout : mTabItems) {
            mBottom.addView(layout, lp);
        }
    }

    private boolean switchFragment(CallBackFragment fragment) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.getAdded()) {
                if (null != mCurrentFragment) {
                    transaction.hide(mCurrentFragment);
                }
                transaction.show(fragment);
            } else {
                if (null != mCurrentFragment) {
                    transaction.hide(mCurrentFragment);
                }
                transaction.add(R.id.fragments_layout, fragment);
                fragment.setAdded(true);
                transaction.show(fragment);
            }
            transaction.commit();
            mCurrentFragment = fragment;
            return true;
        } catch (Exception e) {
            fragment.setAdded(false);
            e.printStackTrace();
            return false;
        }
    }
}
