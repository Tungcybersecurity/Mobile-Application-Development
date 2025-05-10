package com.example.demowebview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

    }


    public void backButton (View view) {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
    }


    public void reloadButton (View view) {
        webView.reload();
    }


    public void goBack(View view) {
        String url = editText.getText().toString();
        webView.loadUrl(url);
    }


    public void fwdButton (View view) {
        if (webView.canGoForward()) {
            webView.goForward();
        }

    }

}