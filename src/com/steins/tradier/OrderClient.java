package com.steins.tradier;

import java.util.HashMap;

import com.android.volley.Response;
import com.android.volley.Request.Method;

import android.content.Context;

public class OrderClient extends Client {

	private static final String BASE_URL = "https://api.tradier.com/v1";

	protected static String requestUrl;

	public OrderClient(String id, Context context, String token,
			String contentType) {

		super(context, token, contentType);

		requestUrl = BASE_URL + "/accounts/" + id + '/order';
	}

	private void orderRequest(String requestType, int method,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener,
			HashMap<String, String> extraParameters) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				method, requestUrl + requestType, null, mHeaders,
				extraParameters);

		addToQueue(mRequest);

	}

	public void createOrder(Response.Listener<String> listener,
			Response.ErrorListener errorListener,
			HashMap<String, String> extraParameters) {

		orderRequest("", Method.POST, listener, errorListener, extraParameters);

	}

	public void createMultilegOrder(Response.Listener<String> listener,
			Response.ErrorListener errorListener,
			HashMap<String, String> extraParameters) {

		orderRequest("", Method.POST, listener, errorListener, extraParameters);

	}

	public void previewOrder(Response.Listener<String> listener,
			Response.ErrorListener errorListener,
			HashMap<String, String> extraParameters) {

		extraParameters.put("preview", "true");

		orderRequest("", Method.POST, listener, errorListener, extraParameters);

	}

	public void changeOrder(String orderID, Response.Listener<String> listener,
			Response.ErrorListener errorListener,
			HashMap<String, String> extraParameters) {

		orderRequest("/" + orderID, Method.PUT, listener, errorListener,
				extraParameters);

	}

	public void deleteOrder(String orderID, Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		orderRequest("/" + orderID, Method.DELETE, listener, errorListener,
				null);
	}

}
