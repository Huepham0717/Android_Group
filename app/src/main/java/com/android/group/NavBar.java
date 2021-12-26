package com.android.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class NavBar extends LinearLayout {
    public NavBar(Context context) {
        super(context);
    }

    public NavBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NavBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setEnabled(boolean enabled) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setEnabled(enabled);
        }
    }

    @Override
    public void setVisibility(int visibility) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(visibility);
        }
    }
}
