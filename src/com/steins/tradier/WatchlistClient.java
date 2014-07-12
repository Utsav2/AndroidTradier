package com.steins.tradier;

import java.util.ArrayList;

import com.android.volley.Response;
import com.android.volley.Request.Method;

import android.content.Context;

public class WatchlistClient extends Client {

	private static final String BASE_URL = "https://api.tradier.com/v1/watchlists";

	public WatchlistClient(Context mContext, String token, String contentType) {

		super(mContext, token, contentType);
	}

	private void watchlistRequest(String requestType, int method,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest(requestType, null, method, listener, errorListener);

	}

	private void watchlistRequest(String requestType, ArrayList<String> stocks,
			int method, Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				method, BASE_URL + requestType, stocks, mHeaders, null);

		addToQueue(mRequest);

	}

	public void getAllWatchlists(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest("", Method.GET, listener, errorListener);

	}

	public void getSpecificWatchlist(String id,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest("/" + id, Method.GET, listener, errorListener);

	}

	public void createWatchlist(String name, ArrayList<String> symbols,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest("", symbols, Method.POST, listener, errorListener);

	}

	public void updateWatchlist(String id, String name,
			ArrayList<String> symbols, Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest("/" + id, symbols, Method.PUT, listener, errorListener);

	}

	public void deleteWatchlist(String id, Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest("/" + id, Method.DELETE, listener, errorListener);

	}

	public void addSymbols(String id, ArrayList<String> symbols,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest("/" + id + "/symbols", symbols, Method.POST, listener,
				errorListener);

	}

	public void deleteSymbol(String id, String symbol,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		watchlistRequest("/" + id + "/symbols/" + symbol, Method.DELETE,
				listener, errorListener);

	}

}
