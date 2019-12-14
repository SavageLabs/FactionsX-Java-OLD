package io.illyria.factionsx;

import io.illyria.factionsx.core.Permission;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.utils.hooks.HookManager;
import io.illyria.factionsx.utils.hooks.PlaceholderAPIHook;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
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

    private HookManager hookManager;

    @Override
    public void onEnable() {
        bukkitFactionsBootstrap = this;
        // Suggest using Paper for better performance
        PaperLib.suggestPaper(this);
        hookManager = HookManager.getInstance();
        hookManager.loadHooks();
        factionsX.enable();
        loadConfig();
        Permission.registerAllPermissions(this.getServer().getPluginManager());
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
