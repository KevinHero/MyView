package com.kevin.ukuview;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by camch on 2015-10-09-.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);

    }
}
