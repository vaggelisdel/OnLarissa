package com.example.vagge.onlarissa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebPageView extends AppCompatActivity {

    private WebView webView;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_web_page_view );

        getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        webView = (WebView) findViewById ( R.id.websiteload );
        webView.setWebViewClient ( new WebViewClient () );
        webView.loadUrl (getIntent().getStringExtra("URL"));

        WebSettings webSettings = webView.getSettings ();
        webSettings.setJavaScriptEnabled ( true );
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack ()){
            webView.goBack ();
        }else {
            super.onBackPressed ( );
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish ();
        }

        return super.onOptionsItemSelected ( item );
    }
}
