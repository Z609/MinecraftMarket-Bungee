package me.z609.minecraftmarket.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by albert on 6/4/17.
 */
public class MarketBukkitNotAvailable extends JavaPlugin {

    @Override
    public void onEnable(){
        throw new RuntimeException("This MinecraftMarket plugin is solely intended for use on BungeeCord software. You cannot use this on a Spigot server.");
    }

}
