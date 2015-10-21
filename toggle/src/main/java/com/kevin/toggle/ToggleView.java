package com.kevin.toggle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by camch on 2015-10-09-.
 */
public class ToggleView extends View {

    private Bitmap backBitmap;
    private Bitmap fontBitmap;
    private Paint mPaint;
    private float downX;
    private boolean isOpen;
    private int status;

    public ToggleView(Context context) {
        this(context, null);
    }

    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initImage();
        mPaint = new Paint();


    }

    private void initImage() {
        //背景开关
        backBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        //滑块
        fontBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button_background);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = backBitmap.getWidth();
        int height = backBitmap.getHeight();


        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(backBitmap, 0, 0, mPaint);

        if (status == MotionEvent.ACTION_MOVE || status == MotionEvent.ACTION_DOWN) {

            if (downX < fontBitmap.getWidth() / 2) {
                canvas.drawBitmap(fontBitmap, 0, 0, mPaint);
            } else {
                if (downX > (backBitmap.getWidth() - fontBitmap.getWidth() / 2)) {
                    //设置边界
                    canvas.drawBitmap(fontBitmap, backBitmap.getWidth() - fontBitmap.getWidth(), 0, mPaint);
                } else {
                    canvas.drawBitmap(fontBitmap, downX - fontBitmap.getWidth() / 2, 0, mPaint);
                }
            }
        } else if (status == MotionEvent.ACTION_UP) {

            if(isOpen)
            {
                //设置边界
                canvas.drawBitmap(fontBitmap, backBitmap.getWidth() - fontBitmap.getWidth(), 0, mPaint);
            }
            else
            {
                canvas.drawBitmap(fontBitmap, 0, 0, mPaint);
            }
        }
    }




    //处理事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                status = MotionEvent.ACTION_DOWN; //记录滑块状态

                break;
            case MotionEvent.ACTION_MOVE:
                downX = event.getX();
                status = MotionEvent.ACTION_MOVE; //记录状态
                break;
            case MotionEvent.ACTION_UP:
                downX = event.getX();
                status = MotionEvent.ACTION_UP; //记录状态
                //如果超过一半就打开,否则关闭
                if (downX > backBitmap.getWidth() / 2 && !isOpen) {
                    isOpen = true; //记录开关的状态

                    //调用
                   if(listener!=null)
                   {
                       listener.onToggleOpen(ToggleView.this,isOpen);
                   }

                } else if (downX < backBitmap.getWidth() / 2 && isOpen) {
                    isOpen = false;

                    //调用
                    if(listener!=null)
                    {
                        listener.onToggleOpen(ToggleView.this,isOpen);
                    }
                }

                break;
        }


        invalidate();//出发ondraw调用
        return true;
    }



    //对外提供接口

    public interface OnToggleOpenListener{
       public void  onToggleOpen(ToggleView tv,boolean isOpen);
    }

    //获取回调

    public OnToggleOpenListener listener;
    public void getOnToggleOpenListener(OnToggleOpenListener listener)
    {
        this.listener=listener;
    }

}
