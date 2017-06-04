package me.z609.minecraftmarket.api;

import me.z609.minecraftmarket.bungee.Market;
import me.z609.minecraftmarket.bungee.json.JSONArray;
import me.z609.minecraftmarket.bungee.json.JSONObject;

import me.z609.minecraftmarket.bungee.util.Json;
import me.z609.minecraftmarket.bungee.util.Log;
import me.z609.minecraftmarket.bungee.util.Settings;

public class MinecraftMarketAPI {

	private static String APIKEY;
	private static final String APIURL = "https://www.minecraftmarket.com/api/1.5/";
	private static boolean auth;

	public static void setApikey(String apiKey) {
		APIKEY = apiKey;
		setApi(apiKey);
		Market.getPlugin().reload();
	}
	
	public static void setApi(String apiKey) {
		APIKEY = apiKey;
		Settings.get().getConfig().set("ApiKey", apiKey);
		Settings.get().saveConfig();
		Log.log("Using API-KEY: " + APIKEY);
	}

	public static String getUrl() {
		return APIURL + APIKEY;
	}

	public static String getKey() {
		return APIKEY;
	}

	public static boolean authApikey(String apiKey) {
		if (authAPI(apiKey)) {
			setApikey(apiKey);
			return true;
		}
		return false;
	}

	public static boolean authAPI(String apiKey) {
		String authenticate = "";
		try {
			APIKEY = apiKey;
			authenticate = Json.getJSON(getUrl() + "/auth");
			if (!Json.isJson(authenticate)) {
				Log.log("Server did not authenticate.");
				return false;
			}
			JSONObject json = new JSONObject(authenticate);
			JSONArray jsonresult = json.optJSONArray("result");
			String state = jsonresult.getJSONObject(0).getString("status");
			if (state.equalsIgnoreCase("ok")) {
				Log.response("Auth",  authenticate);
				Log.response("Auth state",  state);
				return true;
			}
			return false;
		} catch (Exception e) {
			Log.log("Server did not authenticate.");
			Log.log(e);
			return false;
		}
	}
}
