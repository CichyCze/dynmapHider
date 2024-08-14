# dynmapHider
Looking for a plugin that hides you from dynmap every time you are in spectator gamemode or vanished via EssentialsX plugin? You're lucky my friend! I have such a plugin here for ya!
### What is dynmapHider?
As I already mentioned, dynmapHider is a simple plugin that hides you from dynmap during spectator gamemode and/or, if present, EssentialsX vanish.
### Requirements
- 1.10.2 and above Bukkit/Spigot/Paper based server
- [dynmap](https://www.spigotmc.org/resources/dynmap%C2%AE.274/) plugin
### Optional
- [EssentialsX](https://essentialsx.net/downloads.html) plugin
### bStats Metrics
This plugin collects anonymous server statistics through [bStats](https://bstats.org/plugin/bukkit/dynmapHider/12501). If you want to disable these metrics, go to `plugins/bStats/config.yml`.
### Commands
- `/dynmaphider` - Shows a list of all available commands
- `/dynmaphider reload` - Reloads the plugin's config file
### Permissions
- `dynmaphider.reload` - Allows you to use the `/dynmaphider reload` command
- `dynmaphider.updatecheck` - Sends you a message upon joining when there's a new version available (Opped players get these messages even without this permission)
### Config
- `hideOnEssentialsVanish` (default: `true`) - If true and EssentialsX plugin is present, players with EsseantialsX vanish will be hidden from dynmap
- `hideOnGameModeSpectator` (default: `true`) - If true, players in spectator gamemode will be hidden from dynmap
### Some issue?
If so, feel free to contact me on Discord - [@cichycze](https://discordapp.com/users/386841344171048960) - where I'm available most of the time, or open an issue in this repository.