package me.z609.minecraftmarket.bungee.fetch;

import java.util.List;

import com.google.common.collect.Lists;
import me.z609.minecraftmarket.bungee.Market;
import me.z609.minecraftmarket.bungee.util.Log;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PendingCommands {

	private static List<PendingCommands> pending = Lists.newArrayList();
	private int id;
	private String command;
	private String username;
	private ProxiedPlayer player;
	private int slots;
	private int delay;
	private boolean online;

	public PendingCommands(int id, String command, String username, int slots, int delay, boolean online) {
		this.id = id;
		this.command = command;
		this.username = username;
		this.player = Market.getServer().getPlayer(username);
		this.slots = slots;
		this.online = online;
		this.delay = delay;

		if (!containsPending(id)) {
			pending.add(this);
		}
	}

	public int getId() {
		return id;
	}

	public boolean isOnline() {
		return online;
	}

	public String getUsername() {
		return username;
	}

	public int getDelay() {
		return delay;
	}

	public String getCommand() {
		return command;
	}

	public int getSlots() {
		return slots;
	}

	public static List<PendingCommands> getAll() {
		return pending;
	}

	public static void remove(int id) {
		try {
			pending.remove(getById(id));
		} catch (Exception e) {
			Log.log(e);
		}
	}

	public static PendingCommands getById(int id) {
		for (PendingCommands pc : getAll()) {
			if (pc.getId() == id) {
				return pc;
			}
		}
		return null;
	}

    public static void removeAll() {
        pending.clear();
    }

	public static boolean containsPending(int id) {
		return getById(id) != null;
	}

}
