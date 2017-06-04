package me.z609.minecraftmarket.bungee.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import org.apache.commons.lang.StringUtils;

import java.io.File;

public class Chat {

	private Chat() {
	}

	static Chat instance;


	File langFile;
	Configuration lang;

	public String prefix = "";

	public void SetupDefaultLanguage() {
		reloadLanguage();
		prefix = ChatColor.translateAlternateColorCodes('&', getLanguage().getString("messages.prefix"));
		if (!prefix.endsWith(" ")) prefix += " ";

	}

	public Configuration getLanguage() {
		return Settings.get().getLanguageFile();
	}

	public void reloadLanguage() {
		Settings.get().reloadLanguageConfig();
	}

	public String getMsg(String string) {
		return getLanguage().getString(string);
	}

	public static String center(String str) {
		return StringUtils.stripEnd(StringUtils.center(str, 80), " ");
	}
	

	public static Chat get() {
		if (instance == null) instance = new Chat();
		return instance;
	}

}
