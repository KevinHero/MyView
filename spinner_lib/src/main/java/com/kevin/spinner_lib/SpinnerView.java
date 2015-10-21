package com.kevin.spinner_lib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

public class SpinnerView extends LinearLayout {

    private EditText et_input;
    private ImageView iv_drawer;
    private PopupWindow mPw;
    private ListView mLv;
    List<SpinnerData> list;

    public SpinnerView(Context context) {
        this(context, null);
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initPopupWindow();
        initData();

        initEvent();


    }




    /**
     * 显示和隐藏popupwindow
     */
    private void showPopupWindow() {
        if (mPw != null && mPw.isShowing()) {
            mPw.dismiss();
        } else {
            //获取et的宽度
            et_input.measure(0, 0);
            mPw.setWidth(et_input.getWidth());
            mPw.showAsDropDown(et_input);
        }

    }

    /**
     * 初始化popupwindow
     */
    private void initPopupWindow() {
        //嵌套listView
        mLv = new ListView(getContext());

        //listView的适配器
        myAdapter = new MyAdapter();

        mLv.setAdapter(myAdapter);


        mPw = new PopupWindow(mLv, -2, 200);

        //外部点击关闭
        mPw.setOutsideTouchable(true);
        //设置背景
        mPw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //获取焦点
        mPw.setFocusable(true);

    }

    //适配器
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(list==null)
            {
                return  0;
            }
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_lv, null);
                holder= new ViewHolder();
                convertView.setTag(holder);

                //找控件
                holder.iv_item_delete= (ImageView) convertView.findViewById(R.id.item_iv_delete);
                holder.iv_item_user= (ImageView) convertView.findViewById(R.id.item_iv_user);
                holder.tv_item_input= (TextView) convertView.findViewById(R.id.item_tv_input);


            }
            else
            {
                holder= (ViewHolder) convertView.getTag();
            }

            final SpinnerData spinnerData = list.get(position);

            if (spinnerData.icon_id != -1) {
                //有图片
                holder.iv_item_user.setImageResource(spinnerData.icon_id);
            } else if (spinnerData.icon != null) {
                holder.iv_item_user.setImageDrawable(spinnerData.icon);
            }
            holder.tv_item_input.setText(spinnerData.number);

            //删除
            holder.iv_item_delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    list.remove(spinnerData);
                    //更新界面
                    myAdapter.notifyDataSetChanged();
                }
            });


            return convertView;
        }
    }


    /**
     * 缓存类
     */
    class ViewHolder {

        ImageView iv_item_delete;
        ImageView iv_item_user;
        TextView tv_item_input;
    }
    /**
     *  数据类
     *
     */
    public static class SpinnerData{
        public Drawable icon;
        public int icon_id = -1;
        public String number;

    }


    //初始化界面
    private void initView() {
       View view= View.inflate(getContext(),R.layout.spinner_activity,this);

        //输入框
        et_input = (EditText) findViewById(R.id.et_input);

        //下拉框
        iv_drawer = (ImageView) findViewById(R.id.tv_drawer);
    }

    //初始化数据
    private void initData() {

    }

    //事件
    private void initEvent() {
        iv_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();

            }
        });

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取点击的数据
                SpinnerData spinnerData = list.get(position);
                //显示在et中
                et_input.setText(spinnerData.number);
                //弹出窗体消失
                showPopupWindow();

            }
        });



    }


    //获取文本的值




    // 数据 调用者组织数据
    //设置数据的监听
    public interface OnSetDatasListener{
        public List<SpinnerData> getDatas();
    }

    private OnSetDatasListener mOnSetDatasListener;
    private MyAdapter myAdapter;


    public void setOnSetDatasListener(OnSetDatasListener listener) {
        mOnSetDatasListener = listener;
        list = mOnSetDatasListener.getDatas();
        myAdapter.notifyDataSetChanged();//更新界面
    }


}
