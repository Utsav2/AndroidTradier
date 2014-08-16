AndroidTradier
==============

This is an Android wrapper for the Tradier API. It uses Google Volley for network requests.
AndroidTradier handles everything, from logging the user in, to all the requests that could be made to the API. Thus, it could make a developer get started on using the Tradier API very easily.


It is recommended to read about Response class of Google Volley to use this library. 

How to configure
=================

Download Google Volley 

`git clone https://android.googlesource.com/platform/frameworks/volley`

and add it to your workspace. 

Add this project to your workspace by using git clone, or donwloading the zip file, extracting it, and importing it to your workspace.

This project requires Google Volley, so mark it as a dependency. In Eclipse, you would right click, select Properties, select Android in the left hand column, select Volley and click on Add in the lower right corner.

Finally, if you want to use AndroidTradier to help with user login, you have to add this line to the AndroidManifest.xml in your project

```

<activity android:name="com.steins.tradier.OauthLoginActivity" >
</activity>

```
This is because of security reasons, as Android wants you to explicitly mention each Activity your project uses.


How to use
==========

AndroidTradier makes it easy to get data from the Tradier API. 

You can start with OauthLoginActivity which opens a webview to allow the user to login. You just pass an intent with two extras: `redirect_uri` and `consumer_key`, which are self explanatory. 
If the user logs in or signs up, the activity returns with an intent with a parameter called "code" which is the access code.
This code could be received in a method called onActivityResult. For example, 

```
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {

				String authCode = data.getStringExtra("code");

				authorize(authCode);
			}
			if (resultCode == RESULT_CANCELED) {
  
        //show error message or close 

			}
		}
	}
	
```

where authorize is a method that authorizes the user and receives the Oauth token after getting the token.

Then you can use the Authorize class provided in the wrapper to easily to authorize the user. This is an example method:

```
	private void authorize(String authorizationCode) {

		String consumerKey; //This is the consumer key provided by Tradier

		String consumerSecret; // This is the consumer secret provided by Tradier.

		Authorize authorizer = new Authorize(getApplicationContext(),
				authorizationCode, consumerKey, consumerSecret,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						parseAndSaveData(response);

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

						Log.d("Error Response", error.getMessage() + ""); //  The  + "" is used to prevent a null message causing an exception.
					}
				});

	}

```
As mentioned on the Tradier website, this only provides a response in JSON, so String response can easily be parsed into a JSON object.

Once you have received the Access token, you can create a "Tradier" object.

This object takes three parameters: Application Context, access token, and type of encoding you want to receive the data. 

The default is XML. 

This is an example of creating a Tradier object that receives JSON data:

`Tradier client = new Tradier(getApplicationContext(), accessToken, Tradier.CONTENT_JSON);`

This object now makes requests for any kinds of data you would like to receive.

For user data, you would use `client.user.getProfile`, or `client.user.getHistory`, etc. 

For market data, you would use `client.market.search`, `client.market.getIntradayStatus`, etc. 

For watchlists, you would use `client.watchlists` .

For getting an account, you would create an AccountClient and call `client.getAccount(String id)`

For example

```

AccountClient defaultAccount = client.getAccount("5");

```

Each function takes a Response.Listener and an Response.ErrorListener, which acts like a callback for when the call is completed, and can be reused, making code simpler.

For example, see this function I wrote that receives watchlist data.

```

	private void createWatchlistData() {

		Response.Listener<String> listener = new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {

				try {
					createMarketFragment(response);
				} catch (Exception e) {

					Log.e("Exception in watchlist activity", e.getMessage()
							+ "");

				}

			}
		};

		Response.ErrorListener errorListener = new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

				Log.d("Error.Response", error.getMessage() + "");
			}
		};
		
		//This is the call to get a specific watchlist with an ID, a listener, and error listener which were written above

		mClient.watchlists.getSpecificWatchlist(getIntent()
				.getStringExtra("id"), listener, errorListener);

	}
	
```
Structure
=========

Whichever requests require stock symbols, the request accepts an ArrayList<String> of symbols.
Whichever requests require extra parameters, the request accepts a HashMap<String, String> of extraParameters.

For example, this is the method "getHistoricalPricing" from the Tradier API:

```
public void getHistoricalPricing(ArrayList<String> stocks,
		HashMap<String, String> extraParameters,
		Response.Listener<String> listener,
		Response.ErrorListener errorListener)

```

If you have any questions or want help, don't hesitate to open an issue.



