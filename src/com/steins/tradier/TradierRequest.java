package com.steins.tradier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/*This class helps obtain Market data to the developer.
 * It takes a success listener and error listener so the user 
 * can asynchronously perform actions.
 * 
 * It takes the extra Url of the request, for example "quotes" or "options/chains" to make specific requests,
 * an array list of stocks, and a map of parameters, and sends the request, effectively abstracting it to the 
 * user
 * 
 */

class TradierRequest extends Request<String> {

	// The Tradier API url for getting market data

	private static String SYMBOLS = "symbols";

	private HashMap<String, String> mHeaders;

	private HashMap<String, String> mParams;

	private String mContentType;

	// This is passed in by the user, and is called if the request is successful
	private final Response.Listener<String> mListener;

	public TradierRequest(Response.Listener<String> listener,
			Response.ErrorListener errorListener, int method, String url,
			ArrayList<String> stocks, HashMap<String, String> headers,
			HashMap<String, String> extraParameters) {

		super(method, url, errorListener);

		mListener = listener;

		mHeaders = headers;

		createParams(stocks, extraParameters);

	}

	/*
	 * This gets all the symbols from the array list of stocks and sets the
	 * parameters to the complete string.It checks if the components are null as
	 * well, so it can be used for any market request
	 */
	private void createParams(ArrayList<String> stocks,
			HashMap<String, String> parameters) {

		mParams = new HashMap<String, String>();

		if (stocks != null) {

			StringBuffer builder = new StringBuffer();

			for (String stock : stocks) {

				builder.append(stock);

				builder.append(',');

			}

			builder.deleteCharAt(builder.length() - 1);

			mParams.put(SYMBOLS, builder.toString());

		}
		if (parameters != null) {

			for (Map.Entry<String, String> entry : parameters.entrySet()) {

				mParams.put(entry.getKey(), entry.getValue());

			}

		}

	}

	/*
	 * getHeaders and getParams overrides the super methods of Request.
	 */

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {

		if (mHeaders == null)
			return super.getHeaders();

		return mHeaders;
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {

		if (mParams == null)
			return super.getParams();

		return mParams;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {

		return Response.success(new String(response.data), getCacheEntry());

	}

	@Override
	protected void deliverResponse(String response) {

		mListener.onResponse(response);

	}

}
