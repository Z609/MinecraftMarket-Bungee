package me.z609.minecraftmarket.bungee.command.commands;

import me.z609.minecraftmarket.bungee.Market;

import me.z609.minecraftmarket.bungee.command.MarketCommand;
import me.z609.minecraftmarket.bungee.util.Chat;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandVersion extends MarketCommand {

	public CommandVersion() {
		super("version", "Minecraft Market plugin version", "", "minecraftmarket.admin");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		sender.sendMessage(new TextComponent(Chat.get().prefix + " CommandVersion " + Market.getPlugin().getDescription().getVersion()));
	}

}
