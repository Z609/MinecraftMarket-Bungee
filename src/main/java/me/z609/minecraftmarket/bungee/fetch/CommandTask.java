package me.z609.minecraftmarket.bungee.fetch;

import me.z609.minecraftmarket.bungee.Market;

public class CommandTask implements Runnable {

	@Override
	public void run() {
        PendingCommands.removeAll();
        FetchCommand fetchCommand = new FetchCommand();
		fetchCommand.fetchPending();
		CommandExecutor.executeAllPending();
		CommandExecutor.clean();
	}

	public static void check() {
		Market.getServer().getScheduler().runAsync(Market.getPlugin(), new CommandTask());
	}
}