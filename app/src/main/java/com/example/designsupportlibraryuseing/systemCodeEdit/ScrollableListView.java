package com.example.designsupportlibraryuseing.systemCodeEdit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by twiceYuan on 6/29/15.
 *
 * 可以在 ScrollView 中使用的 ListView
 */
public class ScrollableListView extends ListView {
    public ScrollableListView(Context context) {
        super(context);
    }

    public ScrollableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
