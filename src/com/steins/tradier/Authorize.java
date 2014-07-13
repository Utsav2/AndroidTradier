package com.steins.tradier;

import java.util.HashMap;

import android.content.Context;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class Authorize {

	private static final String BASE_URL = "https://api.tradier.com/v1";

	private static final String AUTHORIZATION_URL = "/oauth/accesstoken";

	private RequestQueue mQueue;

	public Authorize(Context context, String authorizationCode,
			String consumerKey, String consumerSecret,
			final Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		createQueue(context);

		HashMap<String, String> mHeaders = new HashMap<String, String>();

		HashMap<String, String> mBody = new HashMap<String, String>();
		
		mHeaders.put("Authorization", "Basic " + encodeBasic(consumerKey, consumerSecret));

		mBody.put("grant_type", "authorization_code");

		mBody.put("code", authorizationCode);

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				Method.POST, BASE_URL + AUTHORIZATION_URL, null, mHeaders,
				mBody);

		addToQueue(mRequest);

	}

	private void createQueue(Context context) {

		mQueue = Volley.newRequestQueue(context);

	}

	protected void addToQueue(Request<String> mRequest) {

		mQueue.add(mRequest);

	}

	private String encodeBasic(String consumerKey, String consumerSecret) {
		
		String credentials = consumerKey + ":" + consumerSecret;

		return Base64.encodeToString(
				credentials.getBytes(), Base64.NO_WRAP);


	}
}
