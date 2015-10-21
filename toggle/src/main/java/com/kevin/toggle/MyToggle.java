package com.kevin.toggle;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class MyToggle extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle);
        ToggleView tv= (ToggleView) findViewById(R.id.tv_open);
        tv.getOnToggleOpenListener(new ToggleView.OnToggleOpenListener() {
            @Override
            public void onToggleOpen(ToggleView tv, boolean isOpen) {
                if(isOpen)
                {
                    Toast.makeText(MyToggle.this,"opened",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MyToggle.this,"closed",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
