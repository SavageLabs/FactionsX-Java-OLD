package io.illyria.factionsx;

import io.illyria.factionsx.command.FBaseCommand;
import io.illyria.factionsx.core.Permission;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.utils.ChatUtil;
import io.illyria.factionsx.utils.hooks.HookManager;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Here is the bukkit implementation of Factions plugin.
 * Anything related to bukkit goes here.
 */

public final class BukkitFactionsBootstrap extends JavaPlugin implements FactionsBootstrap {

    private static BukkitFactionsBootstrap bukkitFactionsBootstrap;
    private FactionsX factionsX = new FactionsX(this);
    private FBaseCommand factionsBaseCommand;

    private HookManager hookManager;

    @Override
    public void onEnable() {
        bukkitFactionsBootstrap = this;
        printLogo();
        // Suggest using Paper for better performance
        PaperLib.suggestPaper(this);
        factionsX.enable();
        // Load hooks
        hookManager = HookManager.getInstance();
        hookManager.loadHooks();
        // Load configs
        loadConfig();
        // Register permissions
        Permission.registerAllPermissions(this.getServer().getPluginManager());
        // Register the command.
        this.factionsBaseCommand = new FBaseCommand();
        PluginCommand factionsxCommand = this.getCommand("factionsx");
        if (factionsxCommand != null) factionsxCommand.setExecutor(factionsBaseCommand);
        else ChatUtil.sendConsole("Something went wrong, the `factionsx` command could not be found in the plugin.yml.");
    }

    @Override
    public void onDisable() {
        // Cancel running Tasks, this should happen anyways
        Bukkit.getServer().getScheduler().cancelTasks(this);
        // Unregister hooks
        hookManager.unregisterHooks();
        // Set the saved instance to null, saving memory
        bukkitFactionsBootstrap = null;
        factionsX.disable();
    }

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public void unload() {

    }

    public void loadConfig() {
        factionsX.getConfigManager().getFileMap().get("config").init();
        factionsX.getConfigManager().getFileMap().get("messages").init();
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, bukkitFactionsBootstrap);
        }
    }

    // Tell IntelliJ to not format this, by enabling formatter markers in comments (Pref-> Editor-> Code Style)
    // Made this way for easy editing/char replacing, using equal size chars for all consoles compatibility.
    //@formatter:off
    private void printLogo() {
        ChatUtil.sendConsole(("\n"+
            "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬛⬛⬛\n" +
            "⬜⬛⬛⬛⬛⬛⬜⬜⬜⬜⬛⬜⬜⬛⬛⬛⬛⬜⬛⬛⬛⬛⬛⬜⬛⬛⬛⬜⬜⬛⬛⬛⬜⬛⬜⬜⬛⬛⬜⬜⬛⬛⬛⬜⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬜\n" +
            "⬜⬛⬛⬜⬜⬛⬜⬜⬜⬛⬛⬜⬜⬛⬜⬛⬛⬜⬜⬜⬛⬜⬛⬜⬜⬛⬜⬜⬛⬛⬜⬛⬜⬛⬛⬜⬜⬛⬜⬛⬛⬜⬜⬜⬜⬜⬜⬛⬛⬜⬛⬛⬜⬜⬜\n" +
            "⬜⬛⬜⬜⬜⬜⬜⬜⬛⬜⬛⬜⬜⬛⬜⬜⬛⬜⬜⬜⬛⬜⬜⬜⬜⬛⬜⬜⬛⬜⬜⬛⬜⬛⬛⬛⬜⬛⬜⬛⬛⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜\n" +
            "⬜⬛⬛⬛⬛⬜⬜⬛⬛⬛⬛⬜⬜⬛⬜⬜⬜⬜⬜⬜⬛⬜⬜⬜⬜⬛⬜⬜⬛⬜⬜⬛⬜⬛⬜⬛⬛⬛⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜\n" +
            "⬜⬛⬜⬜⬜⬜⬛⬛⬜⬜⬛⬜⬜⬛⬜⬜⬛⬜⬜⬜⬛⬜⬜⬜⬜⬛⬜⬜⬛⬜⬛⬛⬜⬛⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬜⬜⬜⬛⬛⬜⬛⬛⬜⬜⬜\n" +
            "⬜⬛⬜⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬛⬛⬛⬛⬜⬜⬜⬛⬜⬜⬜⬛⬛⬛⬜⬛⬛⬛⬜⬜⬛⬛⬜⬜⬛⬜⬛⬛⬛⬜⬜⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬜\n" +
            "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬛⬛⬛\n"
        ).replace("⬜","&0▉").replace("⬛","&f▉"));
        ChatUtil.sendConsole("&f➜ &eMade with &4❤ &eby the&f illyria.io Team\n");
    }
    //@formatter:on

    @Override
    public File getBootstrapDataFolder() {
        return this.getDataFolder();
    }

    public FactionsX getFactionsX() {
        return factionsX;
    }

    public static BukkitFactionsBootstrap getInstance() {
        return bukkitFactionsBootstrap;
    }

}
