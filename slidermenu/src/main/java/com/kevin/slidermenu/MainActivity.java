package com.kevin.slidermenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SliderView sv;
    private TextView news;
    private TextView read;
    private TextView local;
    private TextView ties;
    private TextView pics;
    private TextView ugc;
    private TextView vote;
    private TextView focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initEvent();
    }

    //初始化界面
    private void initView() {
        setContentView(R.layout.activity_main);
        sv = (SliderView) findViewById(R.id.sv_test);

        news = (TextView) findViewById(R.id.news);
        read = (TextView) findViewById(R.id.read);
        local = (TextView) findViewById(R.id.local);
        ties = (TextView) findViewById(R.id.ties);
        pics = (TextView) findViewById(R.id.pics);
        ugc = (TextView) findViewById(R.id.ugc);
        vote = (TextView) findViewById(R.id.vote);
        focus = (TextView) findViewById(R.id.focus);
    }

    private void initEvent() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.news:
                        Toast.makeText(MainActivity.this, "news", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.read:
                        Toast.makeText(MainActivity.this, "read", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.local:
                        Toast.makeText(MainActivity.this, "local", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ties:
                        Toast.makeText(MainActivity.this, "ties", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pics:
                        Toast.makeText(MainActivity.this, "pics", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ugc:
                        Toast.makeText(MainActivity.this, "ugc", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.vote:
                        Toast.makeText(MainActivity.this, "vote", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.focus:
                        Toast.makeText(MainActivity.this, "focus", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };

        //绑带控件
        news.setOnClickListener(listener);
        read.setOnClickListener(listener);
        local.setOnClickListener(listener);
        ties.setOnClickListener(listener);
        pics.setOnClickListener(listener);
        ugc.setOnClickListener(listener);
        vote.setOnClickListener(listener);
        focus.setOnClickListener(listener);


    }

    //点击事件
    public void onClick(View view) {
        sv.toggle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

    sv.toggle();
        return true;//super.onTouchEvent(event);
    }
}
