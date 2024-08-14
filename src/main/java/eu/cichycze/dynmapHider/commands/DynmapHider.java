package eu.cichycze.dynmapHider.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import eu.cichycze.dynmapHider.Main;

public class DynmapHider implements CommandExecutor, TabCompleter {
    Main plugin = Main.getInstance();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            List<String> list = new ArrayList<>();

            if (args.length == 1) {
                list.clear();
                list.add("reload");
            }

            Collections.sort(list);

            return list;
        } else return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender instanceof ConsoleCommandSender || sender.hasPermission("dynmaphider.reload")) {
                    try {
                        plugin.config.load(plugin.configFile);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConfig file was successfully reloaded."));
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4An unexpected error occurred!"));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough permission to use that command!"));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eIf you're not sure how to use dynmapHider's commands, you can use &3/dynmaphider&e."));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Usage:\n&3/dynmaphider reload &f- &aReloads the config file of this plugin"));
        }

        return true;
    }
}
