package com.steins.tradier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

public class MarketRequest extends Request<String> {

	// The Tradier API url for getting market data

	private static String BASE_URL = "https://api.tradier.com/v1/markets";

	private static String SYMBOLS = "symbols";

	private HashMap<String, String> mHeaders;

	private HashMap<String, String> mParams;

	private String ACCEPT = "Accept";

	private String JSON_MIMETYPE = "application/json";

	// This is passed in by the user, and is called if the request is succesful
	private final Response.Listener<String> mListener;

	public MarketRequest(Response.Listener<String> listener,
			Response.ErrorListener errorListener, String extraURL,
			ArrayList<Stock> stocks, HashMap<String, String> headers,
			HashMap<String, String> extraParameters) {

		super(Method.GET, BASE_URL, errorListener);

		mListener = listener;

		createHeaders(headers);

		createParams(stocks, extraParameters);

	}

	/*
	 * createHeaders simply creates the headers for the http request
	 */
	private void createHeaders(HashMap<String, String> headers) {

		mHeaders = headers;

		mHeaders.put(ACCEPT, JSON_MIMETYPE);

	}

	/*
	 * This gets all the symbols from the array list of stocks and sets the
	 * parameters to the complete string.
	 */
	private void createParams(ArrayList<Stock> stocks,
			HashMap<String, String> parameters) {

		StringBuffer builder = new StringBuffer();

		for (Stock stock : stocks) {

			builder.append(stock.getSymbol());

			builder.append(',');

		}

		builder.deleteCharAt(builder.length() - 1);

		parameters.put(SYMBOLS, builder.toString());

		mParams = parameters;
	}

	/*
	 * getHeaders and getParams overrides the super methods of Request.
	 */

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {

		return mHeaders;
	}

	@Override
	protected Map<String, String> getParams() {

		return mParams;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deliverResponse(String response) {

		mListener.onResponse(response);

	}

}
