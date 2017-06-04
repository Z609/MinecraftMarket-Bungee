package me.z609.minecraftmarket.bungee.command.commands;

import me.z609.minecraftmarket.bungee.command.MarketCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import me.z609.minecraftmarket.bungee.fetch.CommandTask;
import me.z609.minecraftmarket.bungee.util.Chat;

public class CommandCheck extends MarketCommand {
	
	public CommandCheck() {
		super("check", "Force check for purchases", "", "minecraftmarket.admin");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		CommandTask.check();
		sender.sendMessage(new TextComponent(Chat.get().prefix + getMsg("messages.check")));
	}

}
