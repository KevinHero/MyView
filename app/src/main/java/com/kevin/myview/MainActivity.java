package com.kevin.myview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.kevin.spinner_lib.SpinnerView;
import com.kevin.toggle_lib.ToggleView;

public class MainActivity extends AppCompatActivity {

    private ImageSwitcher ivsw;
    int index=0;

    int image[]=new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,
            R.drawable.f,R.drawable.g};
    private ViewPager vpimage;
    private TextView tvdesc;
    private LinearLayout llpoint;
    private ImageView iv_small,iv_smaller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        this.llpoint = (LinearLayout) findViewById(R.id.ll_point);
        this.tvdesc = (TextView) findViewById(R.id.tv_desc);
        this.vpimage = (ViewPager) findViewById(R.id.vp_image);
        SpinnerView sv= (SpinnerView) findViewById(R.id.sv_test);
        ivsw = (ImageSwitcher) findViewById(R.id.ivsw);
        iv_small = (ImageView) findViewById(R.id.iv_small);
        iv_smaller = (ImageView) findViewById(R.id.iv_smaller);

        ivsw.setFactory(new ImageViewFactory(this));


        ToggleView tv= (ToggleView) findViewById(R.id.tv_toggle);
        tv.getOnToggleOpenListener(new ToggleView.OnToggleOpenListener() {
            @Override
            public void onToggleOpen(ToggleView tv, boolean isOpen) {
                if (isOpen) {
                    Toast.makeText(MainActivity.this, "opened", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "closed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



          initImageView();





    }

    private void initImageView() {

        ivsw.setBackgroundResource(image[index]);
        iv_small.setImageResource(image[index]);
        //iv_smaller.setImageResource(image[index+1]);

    }

    class ImageViewFactory implements ViewSwitcher.ViewFactory{

private Context context;
        public ImageViewFactory(Context context) {
            this.context=context;
        }


        @Override
        public View makeView() {
            ImageView iv= new ImageView(context);
            iv.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            return iv;
        }
    }

    //上一张
    public  void up(View view)
    {
        if(index==0)
        {
            index=image.length-1;
        }else
        {
            index--;
        }
        initImageView();


    }

    //下一张
    public   void down(View view)
    {
        if(index==image.length-1)
        {
            index=0;
        }else
        {
            index++;
        }
       initImageView();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
