package me.z609.minecraftmarket.bungee.fetch;

import me.z609.minecraftmarket.bungee.json.JSONArray;
import me.z609.minecraftmarket.bungee.json.JSONException;
import me.z609.minecraftmarket.bungee.json.JSONObject;

import me.z609.minecraftmarket.api.MinecraftMarketAPI;
import me.z609.minecraftmarket.bungee.Market;
import me.z609.minecraftmarket.bungee.util.Json;
import me.z609.minecraftmarket.bungee.util.Log;

public class FetchCommand {

	Market plugin = Market.getPlugin();

	public void fetchPending() {
        String pending = "";
		try {
			pending = Json.getJSON(MinecraftMarketAPI.getUrl() + "/pending");
			Log.response("Pending", pending);

			if (Json.isJson(pending)) {
				JSONArray pendingArray = new JSONObject(pending).optJSONArray("result");
				for (int i = 0; i < pendingArray.length(); i++) {

					String username = pendingArray.getJSONObject(i).getString("username");
					JSONArray commands = pendingArray.getJSONObject(i).getJSONArray("commands");

					for (int c = 0; c < commands.length(); c++) {
						String command = commands.getJSONObject(c).getString("fetch");
						int id = commands.getJSONObject(c).getInt("id");
						int delay = commands.getJSONObject(c).getInt("delay");
						int slots = commands.getJSONObject(c).getInt("slots");
						boolean online = forBoolean(commands.getJSONObject(c).getInt("online"));
						new PendingCommands(id, command, username, slots, delay, online);
					}
				}
			}
			
			String expiry = Json.getJSON(MinecraftMarketAPI.getUrl() + "/expiry");
			Log.response("Expiry", expiry);

			if (Json.isJson(expiry)) {
				JSONArray expiryArray = new JSONObject(expiry).optJSONArray("result");
				for (int i = 0; i < expiryArray.length(); i++) {

					String username = expiryArray.getJSONObject(i).getString("username");
					JSONArray commands = expiryArray.getJSONObject(i).getJSONArray("commands");

					for (int c = 0; c < commands.length(); c++) {
						String command = commands.getJSONObject(c).getString("fetch");
						int id = commands.getJSONObject(c).getInt("id");
						int delay = commands.getJSONObject(c).getInt("delay");
						int slots = commands.getJSONObject(c).getInt("slots");
						boolean online = forBoolean(commands.getJSONObject(c).getInt("online"));
						new PendingCommands(id, command, username, slots, delay, online);
					}
				}
			}
		} catch (JSONException e) {
			Log.log(e);
		}
	}

	private boolean forBoolean(int num) {
		if (num == 1) {
			return false;
		}
		return true;
	}
}
