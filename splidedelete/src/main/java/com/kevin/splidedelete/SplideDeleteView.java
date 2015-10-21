package com.kevin.splidedelete;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by camch on 2015-10-10-.
 */
public class SplideDeleteView extends ViewGroup {

    private View contentView;
    private View deleteView;

    public SplideDeleteView(Context context) {
        this(context, null);
    }

    public SplideDeleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取子组件
        contentView = getChildAt(0);
        deleteView = getChildAt(1);

        //获取deleteView的宽度
        LayoutParams layoutParams = deleteView.getLayoutParams();
        int dlete_view_width = layoutParams.width;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //测量子组件
        contentView.measure(widthMeasureSpec,heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
