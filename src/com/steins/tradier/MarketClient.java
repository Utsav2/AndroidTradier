package com.steins.tradier;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.Request.Method;

public class MarketClient extends Client {

	private static final String BASE_URL = "https://api.tradier.com/v1/markets";

	// Initializes a client session with an access token. The content type
	// default is XML


	// Initializes a client session with an access token, and allows developer
	// to
	// change the mimetype to user's choice.

	public MarketClient(Context context, String token, String contentType) {

		super(context, token, contentType);

	}

	/*
	 * private helper functions to help with creating a request for market data.
	 */

	private void marketRequest(String requestType,
			Response.ErrorListener errorListener,
			Response.Listener<String> listener) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				Method.GET, BASE_URL + requestType, null, mHeaders, null);

		addToQueue(mRequest);

	}

	private void marketRequest(HashMap<String, String> parameters,
			String requestType, Response.ErrorListener errorListener,
			Response.Listener<String> listener) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				Method.GET, BASE_URL + requestType, null, mHeaders, parameters);

		addToQueue(mRequest);

	}

	private void marketRequest(ArrayList<String> stocks, String requestType,
			Response.ErrorListener errorListener,
			Response.Listener<String> listener) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				Method.GET, BASE_URL + requestType, stocks, mHeaders, null);

		addToQueue(mRequest);

	}

	private void marketRequest(ArrayList<String> stocks,
			HashMap<String, String> extraParameters, String requestType,
			Response.ErrorListener errorListener,
			Response.Listener<String> listener) {

		TradierRequest mRequest = new TradierRequest(listener, errorListener,
				Method.GET, BASE_URL + requestType, stocks, mHeaders,
				extraParameters);

		addToQueue(mRequest);

	}

	/*
	 * check https://developer.tradier.com/documentation to understand the
	 * details
	 */

	public void getQuote(ArrayList<String> stocks,

	Response.Listener<String> listener, Response.ErrorListener errorListener) {

		marketRequest(stocks, "/quotes", errorListener, listener);

	}

	public void getTimeAndSales(ArrayList<String> stocks,
			HashMap<String, String> extraParameters,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		marketRequest(stocks, extraParameters, "/timesales", errorListener,
				listener);

	}

	public void getOptionChain(ArrayList<String> stocks,
			HashMap<String, String> extraParameters,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		marketRequest(stocks, extraParameters, "/options/chains",
				errorListener, listener);

	}

	public void getOptionStrike(ArrayList<String> stocks,
			HashMap<String, String> extraParameters,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		marketRequest(stocks, extraParameters, "/options/strikes",
				errorListener, listener);

	}

	public void getOptionExpiration(ArrayList<String> stocks,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		marketRequest(stocks, "/options/expirations", errorListener, listener);

	}

	public void getHistoricalPricing(ArrayList<String> stocks,
			HashMap<String, String> extraParameters,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		marketRequest(stocks, "/history", errorListener, listener);

	}

	public void getIntradayStatus(Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		marketRequest("/clock", errorListener, listener);

	}

	public void getMarketCalendar(HashMap<String, String> parameters,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		marketRequest(parameters, "/calendar", errorListener, listener);

	}

	public void search(String searchTerm, Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		HashMap<String, String> parameters = new HashMap<String, String>();

		parameters.put("q", searchTerm);

		marketRequest(parameters, "/search", errorListener, listener);

	}

	public void lookup(String lookupTerm, Response.Listener<String> listener,
			Response.ErrorListener errorListener) {

		HashMap<String, String> parameters = new HashMap<String, String>();

		parameters.put("q", lookupTerm);

		marketRequest(parameters, "/lookup", errorListener, listener);

	}

}
