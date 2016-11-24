package com.jsp.base.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jsp.base.R;
import com.jsp.base.base.BaseSwipeBackActivity;
import com.jsp.base.bean.UrlBean;
import com.jsp.base.util.LogUtil;
import com.jsp.base.util.NetUtil;
import com.jsp.base.view.WebViewTitleView;

/**
 * Created by admin on 2016/11/18.
 */
public class WebViewActivity extends BaseSwipeBackActivity {

    public static final String DATA = "WebViewActivity.data";
    private String url;
    private WebView webView;
    private ProgressBar bar;
    private WebViewTitleView header;
    private UrlBean urlBean;

    public static void lauchWebViewActivity(Context context,UrlBean url){
        context.startActivity(new Intent(context,WebViewActivity.class).putExtra(DATA,url));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        urlBean = getIntent().getParcelableExtra(DATA);
        url = urlBean.url;
        bar = (ProgressBar) findViewById(R.id.webview_bar);

        webView = (WebView) findViewById(R.id.webView);

        header = (WebViewTitleView) findViewById(R.id.webview_header);
        if(TextUtils.isEmpty(urlBean.avater)){
            header.setAvaterVisible(View.GONE);
        }else {
            header.setAvaterVisible(View.VISIBLE);
            header.setAvater(urlBean.avater);
        }

        if(TextUtils.isEmpty(urlBean.title)){
            header.setTitleVisible(View.GONE);
        }else {
            header.setTitleVisible(View.VISIBLE);
            header.setTitle(urlBean.title);
        }

        if(!TextUtils.isEmpty(urlBean.lastTitle)){
            header.setLastTitle(urlBean.lastTitle);
        }


        header.setFreshVisible(urlBean.isFreshVisible ? View.VISIBLE:View.GONE);
        header.setShareVisible(urlBean.isShareVisible ? View.VISIBLE : View.GONE);
        header.setTitleListener(new WebViewTitleView.TitleButtonClickListener() {
            @Override
            public void onBackClick(View view) {
                if (webView.canGoBack()) {
                    webView.goBack();//返回上一页面
                } else {
                    finish();
                }
            }

            @Override
            public void onCloseClick(View view) {
                finish();
            }

            @Override
            public void onRefreshClick(View view) {
                webView.loadUrl(url);
            }

            @Override
            public void onShareClick(View view) {
                toast("分享");
            }

            @Override
            public void onTitleClick(View view) {
                toast("Title");
            }
        });

        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //WebView加载web资源
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                WebViewActivity.this.url = url;
                findViewById(R.id.webView_error).setVisibility(NetUtil.getNetType(mContext)==NetUtil.NETTYPE_NO ? View.VISIBLE : View.GONE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (view.canGoBack()) {
                    header.setColseVisible(View.VISIBLE);
                } else {
                    header.setColseVisible(View.GONE);
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    bar.setVisibility(View.GONE);
                } else {
                    if (bar.getVisibility() == View.GONE) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    // 加载中
                    int progress = (int)(newProgress*1.5);
                    bar.setProgress(progress-1);
                    bar.setSecondaryProgress(progress);
                }

            }

        });
    }



    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK) {
            if(webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
              finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
