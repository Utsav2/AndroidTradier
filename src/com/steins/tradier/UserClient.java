package com.steins.tradier;

import com.android.volley.Request.Method;
import com.android.volley.Response;

import android.content.Context;

class UserClient extends Client {

	private static final String BASE_URL = "https://api.tradier.com/v1/user";

	public UserClient(Context context, String token, String contentType) {

		super(context, token, contentType);

	}

	private void userRequest(String requestType,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				Method.GET, BASE_URL + requestType, null, mHeaders, null);

		addToQueue(mRequest);

	}

	public void getProfile(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		userRequest("/profile", listener, errorListener);

	}

	public void getBalances(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		userRequest("/balances", listener, errorListener);

	}

	public void getPositions(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		userRequest("/positions", listener, errorListener);

	}

	public void getHistory(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		userRequest("/history", listener, errorListener);

	}

	public void getCostBasis(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		userRequest("/gainloss", listener, errorListener);

	}

	public void getOrders(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		userRequest("/orders", listener, errorListener);

	}

}
