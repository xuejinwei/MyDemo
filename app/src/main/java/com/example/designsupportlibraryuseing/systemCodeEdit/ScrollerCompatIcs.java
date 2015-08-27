package com.example.designsupportlibraryuseing.systemCodeEdit;

import android.widget.OverScroller;

/**
 * Created by 晋伟 on 2015/8/11 0011.
 */
public class ScrollerCompatIcs {
    public static float getCurrVelocity(Object scroller) {
        return ((OverScroller) scroller).getCurrVelocity();
    }
}