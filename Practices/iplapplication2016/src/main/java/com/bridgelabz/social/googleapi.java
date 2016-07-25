package com.bridgelabz.social;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.UnsupportedEncodingException;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLConnection;
	import java.net.URLEncoder;

	public class googleapi {
		public static final String GG_APP_ID = "248167875567179";
		public static final String GG_APP_SECRET = "a1699096f76c23caabf7b84d1bdb6596";
		public static final String REDIRECT_URI = "http://localhost:8081/IPL-2016-EXCEPTIONHANDLING/ipl.html";

		static String accessToken = "";

		public String getFBAuthUrl() {
			String googleLoginUrl = "";
			try {
				googleLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
						+ googleapi.GG_APP_ID + "&redirect_uri="
						+ URLEncoder.encode(googleapi.REDIRECT_URI, "UTF-8")
						+ "&scope=email";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return googleLoginUrl;
		}

		public String getFBGraphUrl(String code) {
			String fbGraphUrl = "";
			try {
				fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
						+ "client_id=" + googleapi.GG_APP_ID + "&redirect_uri="
						+ URLEncoder.encode(googleapi.REDIRECT_URI, "UTF-8")
						+ "&client_secret=" + GG_APP_SECRET + "&code=" + code;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return fbGraphUrl;
		}

		public String getAccessToken(String code) {
			if ("".equals(accessToken)) {
				URL fbGraphURL;
				try {
					fbGraphURL = new URL(getFBGraphUrl(code));
				} catch (MalformedURLException e) {
					e.printStackTrace();
					throw new RuntimeException("Invalid code received " + e);
				}
				URLConnection googleapi;
				StringBuffer b = null;
				try {
					googleapi = fbGraphURL.openConnection();
					BufferedReader in;
					in = new BufferedReader(new InputStreamReader(
							googleapi.getInputStream()));
					String inputLine;
					b = new StringBuffer();
					while ((inputLine = in.readLine()) != null)
						b.append(inputLine + "\n");
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException("Unable to connect with Facebook "
							+ e);
				}

				accessToken = b.toString();
				if (accessToken.startsWith("{")) {
					throw new RuntimeException("ERROR: Access Token Invalid: "
							+ accessToken);
				}
			}
			return accessToken;
		}
	}
