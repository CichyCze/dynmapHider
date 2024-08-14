package eu.cichycze.dynmapHider.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.cichycze.dynmapHider.Main;
import net.ess3.api.events.VanishStatusChangeEvent;

public class EventListenerEssentials implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerVanishCommandEvent(VanishStatusChangeEvent e) {
        if (plugin.hideOnEssentialsVanish())
            plugin.hideOnDynmap(e.getAffected().getBase(), e.getValue());
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        if (plugin.hideOnEssentialsVanish() && e.getPlayer().hasPermission("essentials.silentjoin.vanish"))
            plugin.hideOnDynmap(e.getPlayer(), true);
    }
}
