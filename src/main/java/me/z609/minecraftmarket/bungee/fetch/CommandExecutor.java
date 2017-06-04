package me.z609.minecraftmarket.bungee.fetch;

import java.util.List;
import java.util.concurrent.TimeUnit;

import me.z609.minecraftmarket.api.MinecraftMarketAPI;
import me.z609.minecraftmarket.bungee.Market;
import me.z609.minecraftmarket.bungee.json.JSONException;
import me.z609.minecraftmarket.bungee.util.Json;
import me.z609.minecraftmarket.bungee.util.Log;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import com.google.common.collect.Lists;

public class CommandExecutor implements Runnable {

	private String username;
	private ProxiedPlayer player;
	private String command;
	private int delay;
	private int slots;
	private boolean online;
	private int id;

	private static List<Integer> executed = Lists.newArrayList();

	public CommandExecutor(int id, String username, String command, int slots, int delay, boolean online) {
		this.id = id;
		this.username = username;
		this.command = command;
		this.delay = delay;
		this.online = online;
		this.slots = slots;

		player = Market.getServer().getPlayer(username);
	}

	public boolean isPlayerOnline() {
		return (online && Market.getServer().getPlayer(username) != null);
	}

	public void executed() {
		try {
			Json.getJSON(Json.getJSON(MinecraftMarketAPI.getUrl() + "/executed/" + id));
			executed.add(id);
		} catch (JSONException e) {
			Log.log(e);
		}
	}

	public static void executeAllPending() {
		for (PendingCommands pc : PendingCommands.getAll()) {
			CommandExecutor executor = new CommandExecutor(pc.getId(), pc.getUsername(), pc.getCommand(), pc.getSlots(), pc.getDelay(), pc.isOnline());
			executor.execute();
		}
	}

	public static void clean() {
		for (int id : executed) {
			PendingCommands.remove(id);
		}
	}

	public void execute() {
		if (!isPlayerOnline()) {
			Log.debug("Setting \"/" + command + "\" as pending. Player must be online to execute this fetch.");
			this.setPending();
			return;
		}

		executed();
		Log.debug("Command \"/" + command + "\" will be executed in " + delay + " seconds");
		Market.getServer().getScheduler().schedule(Market.getPlugin(), this, delay, TimeUnit.SECONDS);
	}

	public void setPending() {
		new PendingCommands(id, command, username, slots, delay, online);
	}

	@Override
	public void run() {
		Log.log("Executing \"/" + command + "\" on behalf of " + username);
		Market.getServer().getPluginManager().dispatchCommand(Market.getServer().getConsole(), command);
	}

}
