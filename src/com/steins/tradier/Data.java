package com.steins.tradier;

import android.content.Context;

public class Data {

	public static String CONTENT_JSON = "application/json";

	public static String CONTENT_XML = "application/xml";

	public static String CONTENT_JAVASCRIPT = "application/javascript";

	public UserClient user;

	public MarketClient market;
	
	public WatchlistClient watchlists;

	private Context mContext;

	private String token;

	private String contentType;

	public Data(Context mContext, String token, String contentType) {

		this.mContext = mContext;

		this.token = token;

		this.contentType = contentType;

		user = new UserClient(mContext, token, contentType);

		market = new MarketClient(mContext, token, contentType);
		
		watchlists = new WatchlistClient(mContext, token, contentType);

	}

	public Data(Context mContext, String token) {

		this(mContext, token, CONTENT_XML);

	}

	public AccountClient getAccount(String id) {

		return new AccountClient(id, mContext, token, contentType);

	}
}
