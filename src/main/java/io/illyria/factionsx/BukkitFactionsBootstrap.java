package io.illyria.factionsx;

import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.config.file.ConfigManager;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.utils.ChatUtil;
import io.illyria.factionsx.utils.hooks.PlaceholderAPIHook;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * Here is the bukkit implementation of Factions plugin.
 * Anything related to bukkit goes here.
 */

public class BukkitFactionsBootstrap extends JavaPlugin implements FactionsBootstrap {

    private static BukkitFactionsBootstrap bukkitFactionsBootstrap;

    public static BukkitFactionsBootstrap getInstance() {
        return bukkitFactionsBootstrap;
    }

    private FactionsX factionsX = new FactionsX(this);

    public static PlaceholderAPIHook papiExt;

    @Override
    public void onEnable() {
        bukkitFactionsBootstrap = this;
        initConfig();
        loadConfig();
        Bukkit.getScheduler().runTaskLater(this, () -> {
            // Load hooks with a delay, because plugins sometimes load before us even if
            // they are in the softdepend list, don't know why. This way we're 100% sure.

            // PlaceholderAPI hook - adds placeholders
            if (checkHook("PlaceholderAPI")) {
                papiExt = new PlaceholderAPIHook(this);
                // actually register the placeholders
                papiExt.register();
            }

        }, 2);
        factionsX.enable();
    }

    @Override
    public void onDisable() {
        // Cancel running Tasks, so that it should be PlugMan-safe
        Bukkit.getServer().getScheduler().cancelTasks(this);
        // Unregister PAPI, so that it should be PlugMan-safe
        if (enabledHooks.contains("PlaceholderAPI") && papiExt != null) {
            PlaceholderAPIHook.unreg(papiExt);
            papiExt = null;
        }
        // Set the saved instance to null, saving memory
        bukkitFactionsBootstrap = null;
        // Clearing the enabledHooks list
        enabledHooks.clear();
        factionsX.disable();
    }

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public void unload() {

    }

    private Set<String> enabledHooks = new HashSet<>();

    public Set<String> getEnabledHooks() {
        return enabledHooks;
    }

    private ConfigManager configManager;

    public ConfigManager getConfigManager() {
        return configManager;
    }

    private void initConfig() {
        this.configManager = new ConfigManager(this);
    }

    public void loadConfig() {
        configManager.getFileMap().get("config").init();
        configManager.getFileMap().get("messages").init();
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, bukkitFactionsBootstrap);
        }
    }

    private boolean checkHook(String pluginName) {
        if (Bukkit.getPluginManager().isPluginEnabled(pluginName)) {
            enabledHooks.add(pluginName);
            return true;
        } else if (Config.DEBUG.getBoolean()) {
            ChatUtil.sendConsole(Message.ERROR_HOOK_FAILED.getMessage().replace("%plugin%", pluginName));
        }
        return false;
    }

    @Override
    public String getConfigFolderPath() {
        return getConfig().getCurrentPath();
    }

    public FactionsX getFactionsX() {
        return factionsX;
    }

}
