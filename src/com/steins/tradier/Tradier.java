package com.steins.tradier;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import android.content.Context;

public class Tradier {

	public static String CONTENT_JSON = "application/json";

	public static String CONTENT_XML = "application/xml";

	public static String CONTENT_JAVASCRIPT = "application/javascript";

	public UserClient user;

	public MarketClient market;

	public WatchlistClient watchlists;

	private Context mContext;

	private String token;

	private String contentType;

	public Tradier(Context mContext, String code, String contentType) {

		this.mContext = mContext;

		this.contentType = contentType;
		
		this.token = code;

		user = new UserClient(mContext, token, contentType);

		market = new MarketClient(mContext, token, contentType);

		watchlists = new WatchlistClient(mContext, token, contentType);

	}

	public Tradier(Context mContext, String token) {

		this(mContext, token, CONTENT_XML);

	}

	public AccountClient getAccount(String id) {

		return new AccountClient(id, mContext, token, contentType);

	}

}
