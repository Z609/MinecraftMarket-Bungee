package me.z609.minecraftmarket.bungee.util;

import me.z609.api.BungeeConfigurations;
import me.z609.minecraftmarket.bungee.Market;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

public class Settings {

	private static Settings instance;
	private File languageFile;
	private Configuration language;

	public void loadSettings() {
		languageFile = new File(Market.getPlugin().getDataFolder(), "language.yml");
		BungeeConfigurations.createDefaultConfig(languageFile, "language.yml");

		reloadConfig();
		reloadLanguageConfig();
	}

	public void saveConfig() {
		Market.saveConfig();
		saveLanguageFile();
	}

	public void saveLanguageFile() {
		try {
			BungeeConfigurations.saveConfiguration(language, languageFile);
		} catch (IOException e) {
		}
	}

	public Configuration getConfig() {
		return Market.getConfig();
	}

	public Configuration getLanguageFile() {
		return language;
	}

	public void reloadConfig() {
		Market.reload();
	}

	public void reloadLanguageConfig() {
		try {
			language = BungeeConfigurations.getConfiguration(languageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Settings get() {
		if (instance == null) instance = new Settings();
		return instance;
	}

}
