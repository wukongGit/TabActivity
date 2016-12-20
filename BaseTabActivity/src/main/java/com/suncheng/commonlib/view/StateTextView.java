package com.suncheng.commonlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.suncheng.commonlib.R;

/**
 * Created by suncheng on 2016/9/30.
 */
public class StateTextView extends TextView {
    private Drawable mBgSelected, mBgUnselected;
    private int mTextSelected, mTextUnselected;
    private String mDirection = "top";
    private boolean mSelected;

    public StateTextView(Context context) {
        super(context);
    }

    public StateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs){
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.StateTextView);
        mBgSelected = t.getDrawable(R.styleable.StateTextView_bg_selected);
        mBgUnselected = t.getDrawable(R.styleable.StateTextView_bg_unselected);
        mTextSelected = t.getColor(R.styleable.StateTextView_color_selected, 0x22a7f0);
        mTextUnselected = t.getColor(R.styleable.StateTextView_color_unselected, 0x999999);
        mDirection = t.getString(R.styleable.StateTextView_direction);
        if(TextUtils.isEmpty(mDirection)) {
            mDirection = "top";
        }
        setSelect(false);
    }

    public void setSelect(boolean select) {
        if(select) {
            setTextColor(mTextSelected);
            if(mDirection.equals("left")) {
                setCompoundDrawablesWithIntrinsicBounds(mBgSelected, null, null, null);
            } else if(mDirection.equals("right")) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, mBgSelected, null);
            } else if(mDirection.equals("bottom")) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, null, mBgSelected);
            } else {
                setCompoundDrawablesWithIntrinsicBounds(null, mBgSelected, null, null);
            }
        } else {
            setTextColor(mTextUnselected);
            if(mDirection.equals("left")) {
                setCompoundDrawablesWithIntrinsicBounds(mBgUnselected, null, null, null);
            } else if(mDirection.equals("right")) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, mBgUnselected, null);
            } else if(mDirection.equals("bottom")) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, null, mBgUnselected);
            } else {
                setCompoundDrawablesWithIntrinsicBounds(null, mBgUnselected, null, null);
            }
        }
        mSelected = select;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setBgSelected(Drawable mBgSelected) {
        this.mBgSelected = mBgSelected;
    }

    public void setBgUnselected(Drawable mBgUnselected) {
        this.mBgUnselected = mBgUnselected;
    }

    public void setTextSelected(int mTextSelected) {
        this.mTextSelected = mTextSelected;
    }

    public void setTextUnselected(int mTextUnselected) {
        this.mTextUnselected = mTextUnselected;
    }

    public void setDirection(String mDirection) {
        this.mDirection = mDirection;
    }
}
