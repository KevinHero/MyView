package com.kevin.slidingpanelayout;

/**
 * Created by camch on 2015-10-18- 0018.
 */
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class ShowFragment extends Fragment {

    WebView webview=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show, container, false);
        webview=(WebView) view.findViewById(R.id.webview);
        return view;
    }



    public WebView getWebView()
    {
        return webview;
    }
}
