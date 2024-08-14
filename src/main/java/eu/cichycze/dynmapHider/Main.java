package eu.cichycze.dynmapHider;

import java.io.File;
import java.io.IOException;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;

import com.tchristofferson.configupdater.ConfigUpdater;

import eu.cichycze.dynmapHider.commands.DynmapHider;
import eu.cichycze.dynmapHider.listeners.EventListener;
import eu.cichycze.dynmapHider.listeners.EventListenerEssentials;

public class Main extends JavaPlugin {
    private static Main instance;

    DynmapAPI dynmapAPI;

    public File configFile;
    public FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;

        createConfig();

        registerCommands();
        registerEvents();

        UpdateChecker updateChecker = new UpdateChecker();
        updateChecker.versionCheckOnEnable();

        int pluginId = 12501;
        Metrics metrics = new Metrics(this, pluginId);

        Plugin dynmap = Bukkit.getPluginManager().getPlugin("dynmap");
        dynmapAPI = (DynmapAPI) dynmap;
    }

    public static Main getInstance() {
        return instance;
    }

    private void createConfig() {
        this.configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) saveResource("config.yml", false);

        try {
            ConfigUpdater.update(instance, "config.yml", configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    private void registerCommands() {
        PluginCommand dynmapHider = this.getCommand("dynmaphider");
        dynmapHider.setExecutor(new DynmapHider());
        dynmapHider.setTabCompleter(new DynmapHider());
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginManager().registerEvents(new EventListenerEssentials(), this);
    }

    public boolean essentialsPresent() {
        return Bukkit.getServer().getPluginManager().getPlugin("Essentials") != null;
    }

    public boolean hideOnEssentialsVanish() {
        return this.getConfig().getBoolean("hideOnEssentialsVanish");
    }

    public boolean hideOnGameModeSpectator() {
        return this.getConfig().getBoolean("hideOnGameModeSpectator");
    }

    public void hideOnDynmap(Player p, boolean hide) {
        this.dynmapAPI.setPlayerVisiblity(p, !hide);
    }
}