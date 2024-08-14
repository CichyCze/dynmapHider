package eu.cichycze.dynmapHider.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.cichycze.dynmapHider.Main;
import eu.cichycze.dynmapHider.UpdateChecker;

public class EventListener implements Listener {
    Main plugin = Main.getInstance();
    UpdateChecker updateChecker = new UpdateChecker();

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        if (plugin.hideOnGameModeSpectator())
            plugin.hideOnDynmap(e.getPlayer(), e.getPlayer().getGameMode() == GameMode.SPECTATOR);

        if (e.getPlayer().isOp() || e.getPlayer().hasPermission("dynmaphider.updatecheck"))
            updateChecker.versionCheckOnPlayerJoin(e.getPlayer());
    }

    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent e) {
        if (plugin.hideOnGameModeSpectator())
            plugin.hideOnDynmap(e.getPlayer(), e.getNewGameMode() == GameMode.SPECTATOR);
    }
}
