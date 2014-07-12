package com.steins.tradier;

import java.util.HashMap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;

class Client {

	private static String AUTHORIZATION = "Authorization";

	private static String ACCEPT = "Accept";

	protected HashMap<String, String> mHeaders;

	private String token;

	private String contentType;

	private Context mContext;

	private RequestQueue mQueue;

	// Initializes a client session with an access token, and allows user to
	// change the mimetype to user's choice.

	public Client(Context context, String token, String contentType) {

		this.token = token;

		this.contentType = contentType;

		mContext = context;

		createHeaders();

		createQueue();

	}

	private void createHeaders() {

		mHeaders = new HashMap<String, String>();

		mHeaders.put(AUTHORIZATION, "Bearer " + token);

		mHeaders.put(ACCEPT, contentType);

	}

	private void createQueue() {

		mQueue = Volley.newRequestQueue(mContext);

	}

	protected void addToQueue(TradierRequest request) {

		mQueue.add(request);

	}

}
