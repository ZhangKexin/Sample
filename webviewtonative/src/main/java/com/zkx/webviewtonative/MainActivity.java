package com.zkx.webviewtonative;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "DEBUG" + MainActivity.class.getSimpleName();

    private static final String FILE_NAME = "file:///android_asset/web_intent.html";

    @Bind(R.id.main_wv_web)
    WebView mWvWeb;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebIntentActivity.class));
            }
        });

        WebSettings webSettings = mWvWeb.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWvWeb.loadUrl(FILE_NAME);
    }

    @Override
    public void onBackPressed() {
        if (mWvWeb.canGoBack()) {
            mWvWeb.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_open_in_browser:
                String[] as = FILE_NAME.split("/");
                openUrlInBrowser(as[as.length - 1]);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openUrlInBrowser(String url) {
        Uri uri;
        if (url.endsWith(".html")) {
            uri = Uri.fromFile(createFileFromInputStream(url));
        } else {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            uri = Uri.parse(url);
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }

    private File createFileFromInputStream(String url) {
        try {
            InputStream inputStream = getAssets().open(url);
            File file = new File(Environment.getExternalStorageDirectory().getPath(), url);
            OutputStream outputStream = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
