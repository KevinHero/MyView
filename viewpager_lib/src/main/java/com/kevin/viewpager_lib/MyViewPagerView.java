package com.kevin.viewpager_lib;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyViewPagerView extends LinearLayout {
    private ViewPager vp_image;
    private List<ImageView> list = new ArrayList<ImageView>();
    private TextView tv_desc;
    private LinearLayout ll_point;
    private int selectIndex;

    public MyViewPagerView(Context context) {
        this(context, null);
    }

    public MyViewPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

        initData();

        initEvent();
    }


    //初始化事件
    private void initEvent() {
    vp_image.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            selectIndex=position;
            initPoints();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });
    }


    private void initPoints() {
        ll_point.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            View v = new View(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
            if (i == selectIndex) {
                // 描述信息
                tv_desc.setText("图片" + i + "描述");
                // 红点
                v.setBackgroundResource(R.drawable.red_point);
            } else {
                v.setBackgroundResource(R.drawable.white_point);
            }
            lp.rightMargin = 5;
            lp.leftMargin = 5;

            v.setLayoutParams(lp);

            // 设置布局参数
            ll_point.addView(v);
        }

    }


    //初始化数据
    private void initData() {
       initImage();

        initPoints();
        //给viewPager添加数据,适配器对象
        MyViewPager adapter= new MyViewPager();

        vp_image.setAdapter(adapter);

    }


    class MyViewPager extends  PagerAdapter{
        @Override
        public int getCount() {

            //System.out.println( list.size()+">>>>>>>>>>>>>>");
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // System.out.println("isViewFromObject" + view + ":" + object);
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //  System.out.println("destroyItem"  + ":" + position);
            container.removeView((View) object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view=list.get(position);
            container.addView(view);
            return view;
        }
    }




    //初始化图片
    private void initImage() {

        for (int id = R.drawable.a; id <= R.drawable.aaaaa; id++) {
            //创建一个imageview
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(id);
            iv.setScaleType(ScaleType.FIT_XY);//图片填充控件
            list.add(iv);


        }
    }

    //初始化界面
    private void initView() {
      View view=View.inflate(getContext(),R.layout.activity_viewpager,this);
        //查找控件
        vp_image = (ViewPager)view. findViewById(R.id.vp_image);

        //描述信息
        tv_desc = (TextView) view.findViewById(R.id.tv_desc);

        //点的布局
        ll_point = (LinearLayout) view.findViewById(R.id.ll_point);
    }
}

