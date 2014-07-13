package com.steins.tradier;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OauthLoginActivity extends Activity {

	private String REDIRECT_URI;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
				
		REDIRECT_URI = getIntent().getExtras().getString("redirect_uri");
		
		String client_id = getIntent().getExtras().getString("consumer_key");
		
		WebView webview = new WebView(getApplicationContext());

		webview.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				if (!url.startsWith(REDIRECT_URI))
					return super.shouldOverrideUrlLoading(view, url);

				if (url.indexOf("code") == -1)
					return super.shouldOverrideUrlLoading(view, url);

				String accessToken = Uri.parse(url).getQueryParameter("code");

				Intent returnIntent = new Intent();

				returnIntent.putExtra("code", accessToken);

				setResult(RESULT_OK, returnIntent);

				finish();

				return true;

			}
			
		});

		String url = "https://api.tradier.com/v1/oauth/authorize?scope=read,write,market,trade,stream&state=&client_id="
				+ client_id;

		webview.loadUrl(url);

		setContentView(webview);

	}
}
