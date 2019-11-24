package com.cartfawri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
    WebView wb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        wb=(WebView)findViewById(R.id.web);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wb.getSettings().setPluginsEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("http://cardfawri.com");
    }

    @Override
    public void onBackPressed() {
        if (wb.canGoBack()){
            wb.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
