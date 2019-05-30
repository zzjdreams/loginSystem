package com.example.pc.qqlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {

    private Button poke;
    private Intent intent;
    private WebView browser;
    private static final String TAG = "Main2Activity";
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent intent2 = getIntent();
        username = intent2.getStringExtra("username");
        Toast.makeText(Main2Activity.this, "username-->"+username, Toast.LENGTH_SHORT).show();

        poke=(Button)findViewById(R.id.poke);
        poke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(Main2Activity.this,PokeActivity.class);
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });
        WebView browser = (WebView) findViewById(R.id.Toweb);
        browser.loadUrl("http://www.scholat.com/Phomepage.html");

        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true); //启用javascript
        webSettings.setAppCacheEnabled(true);   //启用appCache
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);

        //设置可自由缩放网页、JS生效
        //webSettings.setSupportZoom(true);
        //webSettings.setBuiltInZoomControls(true);

        // 如果页面中链接，如果希望点击链接继续在当前browser中响应，
        // 而不是新开Android的系统browser中响应该链接，必须覆盖webview的WebViewClient对象
        browser.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });

    }

    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart()");

    }
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume()");
    }
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop()");
    }
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }
}
