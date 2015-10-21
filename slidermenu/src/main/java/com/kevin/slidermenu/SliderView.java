package com.kevin.slidermenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by camch on 2015-10-10-.
 */
public class SliderView extends ViewGroup {

    private int menu_width;
    private View contentView;
    private View menuView;
    private float mDownX;
    private boolean isOpenMenu;
    private Scroller mScroller;

    public SliderView(Context context) {
        this(context, null);
    }

    public SliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }



    //获取子组件
    @Override
    protected void onFinishInflate() {

        //布局解析完成回调
        contentView = getChildAt(0);

        menuView = getChildAt(1);


        //获取menu组件的宽度
        // LayoutParams content_layoutParams = contentView.getLayoutParams();
        LayoutParams menu_layoutParams = menuView.getLayoutParams();

        menu_width = menu_layoutParams.width;

        super.onFinishInflate();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //c测量子组件

        contentView.measure(widthMeasureSpec, heightMeasureSpec);

        //menu,
        //获取menu的精确宽度
        int menuWidthMeasureSpec = MeasureSpec.makeMeasureSpec(menu_width,
                MeasureSpec.EXACTLY);
        menuView.measure(menuWidthMeasureSpec, heightMeasureSpec);

        //两个参数都不带模式
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));

    }

    //拜访子组件
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        contentView.layout(0,//位置 x
                0, //位置y
                contentView.getMeasuredWidth(),//界面宽度
                contentView.getMeasuredHeight()); //界面高度


        //摆放左侧菜单

        menuView.layout(-menuView.getMeasuredWidth(), //位置是从左侧一个宽度
                0,
                0, //起始看不到界面
                menuView.getMeasuredHeight());
    }

    //处理触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下

                mDownX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE://移动

                moveEvent(event);

                break;
            case MotionEvent.ACTION_UP://松开
                //当前屏幕的位置的x坐标
                int scrollX = getScrollX();

                //如果
                if (scrollX < -menuView.getMeasuredWidth() / 2) {
                    //显示菜单
                    //scrollTo(-menuView.getMeasuredWidth(), 0);
                    isOpenMenu = true;
                } else {
                    //不显示menu
                    //scrollTo(0, 0);
                    isOpenMenu = false;
                }

                //显示动画
                myAnimation();
                break;
        }
        return true;
    }


    //初始化动画
    private void myAnimation() {
        int startX = getScrollX();//获取当前屏幕的位置
        int endX = 0;
        if (isOpenMenu) {
            endX = -menuView.getMeasuredWidth();
        } else {
            endX = 0;
        }
        int dx = endX - startX;
        int duration = Math.abs(dx) * 5;
        if (duration > 500) {
            duration = 500;
        }
        mScroller.startScroll(startX, 0, dx, 0, duration);
        invalidate();
    }




    //开关menu的功能
    public void toggle() {
        isOpenMenu = !isOpenMenu;
        myAnimation();
    }


    //让动画动起来


    @Override
    public void computeScroll() {

        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            //让屏幕不停的移动到currX
            scrollTo(currX, 0);
            invalidate();
        }
    }

    //移动的功能实现
    private void moveEvent(MotionEvent event) {
        //移动的位置
        float moveX = event.getX();

        //移动的距离
        int dx = Math.round(mDownX - moveX);

        //当前屏幕的位置的X坐标值
        int scrollX = getScrollX();

        //边界
        if (scrollX + dx < -menuView.getMeasuredWidth()) { //如果超过左边界,就设置为menu的宽度
            scrollTo(-menuView.getMeasuredWidth(), 0);
        } else if (scrollX + dx > 0) { //如果超过右边界就设置为0
            scrollTo(0, 0);
        } else {
            scrollBy(dx, 0);
        }

        //改变起始位置
        mDownX = moveX;
    }
}
