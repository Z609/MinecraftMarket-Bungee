package me.z609.minecraftmarket.bungee.command.commands;

import me.z609.minecraftmarket.bungee.Market;
import me.z609.minecraftmarket.bungee.command.MarketCommand;
import me.z609.minecraftmarket.bungee.util.Chat;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandReload extends MarketCommand {

	public CommandReload() {
		super("reload", "CommandReload MinecraftMarket plugin and settings", "", "minecraftmarket.admin");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Market.getPlugin().reload();
		sender.sendMessage(new TextComponent(Chat.get().prefix + getMsg("messages.reload")));
	}

}
