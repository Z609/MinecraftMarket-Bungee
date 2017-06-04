package me.z609.minecraftmarket.bungee.command.commands;

import me.z609.minecraftmarket.api.MinecraftMarketAPI;
import me.z609.minecraftmarket.bungee.command.MarketCommand;
import me.z609.minecraftmarket.bungee.util.Chat;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandApikey extends MarketCommand {

	public CommandApikey() {
		super("apikey", "Change CommandApikey", "<APIKEY>", "minecraftmarket.admin");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		String Apikey = args[0];
		if (Apikey.matches("[0-9a-f]+") && Apikey.length() == 32) {
			if (MinecraftMarketAPI.authApikey(args[0])) {
				sender.sendMessage(new TextComponent(Chat.get().prefix + "Server authenticated with Minecraft Market."));
				return;
			}
		}
		sender.sendMessage(new TextComponent(Chat.get().prefix + "Server did not authenticate, please check API-KEY."));
	}

}
