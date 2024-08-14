package eu.cichycze.dynmapHider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UpdateChecker {
    Main plugin = Main.getInstance();

    public void versionCheckOnPlayerJoin(Player player) {
        getVersion(version -> {
            if (!plugin.getDescription().getVersion().equalsIgnoreCase(version))
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aNew version of dynmapHider is available!\nhttps://www.spigotmc.org/resources/dynmaphider.95497/"));
        });
    }

    public void versionCheckOnEnable() {
        getVersion(version -> {
            if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                plugin.getLogger().info("No new version available.");
                
            } else {
                plugin.getLogger().warning("New version of dynmapHider is available!");
                plugin.getLogger().warning("https://www.spigotmc.org/resources/dynmaphider.95497/");
            }
        });
    }

    private void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=95497&" + System.currentTimeMillis()).openStream();
                Scanner scanner = new Scanner(inputStream)) {

                if (scanner.hasNext()) consumer.accept(scanner.next());
            } catch (IOException e) {
                plugin.getLogger().info("Cannot look for updates: " + e.getMessage());
            }
        });
    }
}
