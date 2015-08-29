package com.example.designsupportlibraryuseing.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.designsupportlibraryuseing.R;
import com.example.designsupportlibraryuseing.common.base.BaseActivity;
import com.example.designsupportlibraryuseing.http.OkHttpClientManager;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OkHttpActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_okhttp)
    Button btn_okhttp;
    @Bind(R.id.iv_img)
    ImageView iv_img;
    @Bind(R.id.tv_textview)
    TextView tv_textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

        btn_okhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClientManager.getDisplayImageDelegate().displayImage(iv_img, "http://images.csdn.net/20150817/1.jpg");

                String url = "http://m.baidu.com";
                // 创建 OKHTTP 对象
                OkHttpClient mOkHttpClient = new OkHttpClient();
                mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
                mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
                mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                // 创建request
                Request mRequest = new Request.Builder().url(url).build();
                // new call
                Call mCall = mOkHttpClient.newCall(mRequest);

                mCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        String strResponse = response.body().string();
                        runOnUiThread(() -> tv_textview.setText(strResponse));

                    }
                });
            }
        });

    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ok_http, menu);
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
