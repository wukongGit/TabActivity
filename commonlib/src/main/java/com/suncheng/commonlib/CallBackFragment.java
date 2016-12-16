package com.suncheng.commonlib;

import android.support.v4.app.Fragment;

/**
 * Created by suncheng on 2016/5/23.
 */
public class CallBackFragment extends Fragment {

    private boolean mIsAdded;

    public CallBackFragment() {
        mIsAdded = false;
    }

    public void setAdded(boolean added) {
        mIsAdded = added;
    }

    public boolean getAdded() {
        return mIsAdded;
    }
}
