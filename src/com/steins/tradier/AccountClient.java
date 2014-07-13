package com.steins.tradier;

import com.android.volley.Response;
import com.android.volley.Request.Method;

import android.content.Context;

public class AccountClient extends Client {

	private static final String BASE_URL = "https://api.tradier.com/v1";

	private static String requestUrl;
	
	public OrderClient order;

	public AccountClient(String id, Context context, String token,
			String contentType) {

		super(context, token, contentType);

		requestUrl += BASE_URL + "/accounts/" + id;
		
		order = new OrderClient(id, context, token, contentType);

	}

	public AccountClient(String id, Context mContext, String token) {

		this(id, mContext, token, Tradier.CONTENT_XML);

	}

	private void accountRequest(String requestType,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				Method.GET, requestUrl + requestType, null, mHeaders, null);

		addToQueue(mRequest);

	}

	public void getBalances(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		accountRequest("/balances", listener, errorListener);

	}

	public void getPositions(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		accountRequest("/positions", listener, errorListener);
	}

	public void getHistory(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		accountRequest("/history", listener, errorListener);
	}

	public void getCostBasis(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		accountRequest("/gainloss", listener, errorListener);
	}

	public void getOrders(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		accountRequest("/orders", listener, errorListener);
	}

	public void getSpecificOrder(String orderId,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		accountRequest("/orders/" + orderId, listener, errorListener);

	}

}
