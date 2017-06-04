package me.z609.minecraftmarket.bungee.command;

import me.z609.minecraftmarket.bungee.util.Chat;
import net.md_5.bungee.api.CommandSender;

public abstract class MarketCommand {

	private String command;
	private String description;
	private String args;
	private String permission;

	public MarketCommand(String command, String description, String args, String permission) {
		this.command = command;
		this.description = description;
		this.args = args;
		this.permission = permission;
	}

	public String getCommand() {
		return command;
	}

	public String getDescription() {
		return description;
	}

	public String getArgs() {
		return args;
	}

	public String getPermission() {
		return permission;
	}

	public abstract void run(CommandSender sender, String[] args);

	public String getMsg(String string) {
		return Chat.get().getLanguage().getString(string);
	}
}
