# [MinecraftMarket](https://www.minecraftmarket.co.uk)-Bungee
Fork of BuckinghamIO's MinecraftMarket Spigot plugin adapted for BungeeCord servers.

## What's this for? ##
I've gotten a lot of questions about this implementation of the original Minecraft Market plugin. This implementation is specifically designed for BungeeCord, and not anything else. It will fail to run on Bukkit servers.

The majority of the questions stem from the actual _need_ of having such an implementation. This implementation was specifically designed for dynamic and/or large networks where creating a lot of servers on the Minecraft Market panel is neither feasible nor possible. This implementation was originally going to be made private for [ToxicPVP](https://www.toxicpvp.net), a Minecraft network in which I develop; however, I have seen time and time again requests for support for BungeeCord and an implementation of the Minecraft Market plugin for it. Seeing as this flavor of the plugin doesn't offer any improvements over the original and doesn't have any extra features, I have decided to make it public as it doesn't really matter anyway.

Again, this implementation is designed for huge networks, not small BungeeCord systems. 1-4 servers are usually perfectly fine for the vanilla Minecraft Market plugin.

Seeing as this is a BungeeCord plugin, note that giving out items is not possible directly from BungeeCord; have BungeeCord commands that store data into a database which can be accessed by plugins on your Spigot servers so that items can be given out. This implementation was (for the most part) designed to give out global ranks and statistics. Please adjust your expectations accordingly.

## Limitations and Removals ##
BungeeCord is a proxy and NOT a Minecraft server. Thererfore, it does not load worlds, handle blocks, or open inventories. As such, it was unfortunately required for me to remove certain aspects of the Minecraft Market plugin in order for this to work properly.
- Removed custom /shop inventory
- Removed signs
- Removed auto-updater

### Note of non-affiliation ###
Neither me nor any of the organizations or companies I work for represent affiliation or partnership with any of the aforementioned companies:
- Mojang, AB
- Microsoft
- Minecraft Market

This is an open-source fork of [BuckinghamIO's MinecraftMarket GitHub Repo](https://github.com/BuckinghamIO/MinecraftMarket-Plugin).

Any questions, comments, concerns, bug reports, etc., regarding the Minecraft Market service should not be directed to me and rather directed to a representative of Minecraft Market.
