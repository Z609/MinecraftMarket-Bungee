package me.z609.minecraftmarket.bungee.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.z609.minecraftmarket.bungee.Market;
import me.z609.minecraftmarket.bungee.command.commands.CommandApikey;
import me.z609.minecraftmarket.bungee.command.commands.CommandCheck;
import me.z609.minecraftmarket.bungee.command.commands.CommandReload;
import me.z609.minecraftmarket.bungee.command.commands.CommandVersion;
import me.z609.minecraftmarket.bungee.util.Chat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import com.google.common.collect.Lists;

public class CommandManager extends Command {

	private List<MarketCommand> commands = Lists.newArrayList();

	public CommandManager() {
		super("mm");
		commands.add(new CommandCheck());
		commands.add(new CommandApikey());
		commands.add(new CommandReload());
		commands.add(new CommandVersion());
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sendHelp(sender);
			return;
		}
		if (args[0].equalsIgnoreCase("help")) {
			sendHelp(sender);
			return;
		}
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
		a.remove(0);

		for (MarketCommand mc : commands) {
			if (mc.getCommand().equalsIgnoreCase(args[0])) {
				if (!sender.hasPermission(mc.getPermission())) {
					sendMSG(sender, ChatColor.DARK_RED + getMsg("messages.no-permissions"));
					return;
				}
				try {
					mc.run(sender, a.toArray(new String[a.size()]));
					return;
				} catch (Exception ex) {
					sender.sendMessage(new TextComponent(Chat.get().prefix + ChatColor.RED + " Error: " + ex.getMessage()));
					ex.printStackTrace();
					return;
				}
			}

		}
		sendMSG(sender, ChatColor.RED + "Unknown command. Use \"/mm help\" to view a list of available commands.");
	}

	private void sendMSG(CommandSender sender, String msg) {
		sender.sendMessage(new TextComponent(msg));
	}

	private void sendHelp(CommandSender sender) {
		sendMSG(sender, ChatColor.WHITE + "---------------- " + ChatColor.DARK_GREEN + "MinecraftMarket Help " + ChatColor.WHITE + "-----------------");
		for (MarketCommand mc : commands) {
			if (mc.getPermission() != "" || sender.hasPermission(mc.getPermission())) {
				sendMSG(sender, ChatColor.GOLD + "/MM " + mc.getCommand() + " " + mc.getArgs() + " - " + ChatColor.WHITE + mc.getDescription());

			}
		}
		sendMSG(sender, ChatColor.WHITE + "----------------------------------------------------");
	}

	private String getMsg(String string) {
		return Chat.get().getLanguage().getString(string);
	}
}
