package com.suncheng.commonlib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.suncheng.commonlib.R;

/**
 * Created by suncheng on 2016/12/16.
 */
public class TabLayout extends LinearLayout {
    private StateTextView mStateTextView;
    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mStateTextView = (StateTextView) findViewById(R.id.tab_btn);
    }

    public void setBgSelected(Drawable mBgSelected) {
        mStateTextView.setBgSelected(mBgSelected);
    }

    public void setBgUnselected(Drawable mBgUnselected) {
        mStateTextView.setBgUnselected(mBgUnselected);
    }

    public void setTextSelected(int mTextSelected) {
        mStateTextView.setTextSelected(mTextSelected);
    }

    public void setTextUnselected(int mTextUnselected) {
        mStateTextView.setTextUnselected(mTextUnselected);
    }

    public void setText(String text) {
        mStateTextView.setText(text);
    }

    public void setSelect(boolean select) {
        mStateTextView.setSelect(select);
    }

}
