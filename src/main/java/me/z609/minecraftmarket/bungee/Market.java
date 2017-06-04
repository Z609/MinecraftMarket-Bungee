package me.z609.minecraftmarket.bungee;

import me.z609.api.BungeeConfigurations;
import me.z609.minecraftmarket.api.MinecraftMarketAPI;
import me.z609.minecraftmarket.bungee.fetch.CommandTask;
import me.z609.minecraftmarket.bungee.command.CommandManager;
import me.z609.minecraftmarket.bungee.util.Chat;
import me.z609.minecraftmarket.bungee.util.Log;
import me.z609.minecraftmarket.bungee.util.Settings;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Market extends Plugin {

    private static long interval;
    private static String shopCommand;
    private static Market plugin;
    private static CommandTask commandTask;
    private static ProxyServer server;
    private static File configFile;
    private static Configuration config;

	@Override
	public void onDisable() {
		stopTasks();
	}

	@Override
	public void onEnable() {
	    server = BungeeCord.getInstance();
        plugin = this;

        try {
            configFile = new File(getDataFolder(), "config.yml");
            config = BungeeConfigurations.getConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
			ExceptionListener el = new ExceptionListener() {
				@Override
				public void exceptionThrown(Exception e) {
					server.broadcast(new TextComponent(e.getMessage()));
				}
			};
			registerCommands();
			saveDefaultSettings();
			reload();
			startTasks();
		} catch (Exception e) {
			Log.log(e);
		}
	}

	public static void saveConfig(){
        try {
            BungeeConfigurations.saveConfiguration(config, configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void reload() {
		try {
		    config = BungeeConfigurations.getConfiguration(configFile);
            Settings.get().reloadLanguageConfig();
            
			loadConfigOptions();
			// The below method is apparently called more than once.
			//authApi();
		} catch (Exception e) {
			Log.log(e);
		}
	}

	public static Configuration getConfig(){
	    return config;
    }

	private static void loadConfigOptions() {
		Chat.get().SetupDefaultLanguage();
        Configuration config = getConfig();
        MinecraftMarketAPI.setApi(config.getString("ApiKey", "API key here"));
		Market.interval = Math.max(config.getLong("Interval", 90L), 10L);
        Market.shopCommand = config.getString("Shop-Command", "/shop");
        Log.setDebugging(config.getBoolean("Debug", false));
    }

	private static boolean authApi() {
		if (MinecraftMarketAPI.authAPI(MinecraftMarketAPI.getKey())) {
			plugin.getLogger().info("Using API Key: " + MinecraftMarketAPI.getKey());
			return true;
		} else {
            plugin.getLogger().warning("Invalid API Key! Use \"/MM APIKEY <APIKEY>\" to setup your APIKEY");
			return false;
		}
	}

	private static void runCommandChecker() {
		commandTask = new CommandTask();
		server.getScheduler().schedule(plugin, () -> server.getScheduler().runAsync(plugin, commandTask), 30L, (long) interval, TimeUnit.SECONDS);
	}

	private static void startTasks() {
		runCommandChecker();
	}

	private static void registerCommands() {
	    server.getPluginManager().registerCommand(plugin, new CommandManager());
	}

	private static void saveDefaultSettings() {
		Settings.get().loadSettings();
	}

	private static void stopTasks() {
		try {
			server.getScheduler().cancel(plugin);
		} catch (Exception e) {
			Log.log(e);
		}
	}

    public static Market getPlugin() {
        return plugin;
    }

    public static String getShopCommand() {
        return shopCommand;
    }

    public static ProxyServer getServer() {
        return server;
    }

    public static long getInterval() {
        return interval;
    }

    public static CommandTask getCommandTask() {
        return commandTask;
    }

    public static File getConfigFile() {
        return configFile;
    }
}
