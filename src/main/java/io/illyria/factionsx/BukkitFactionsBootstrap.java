package io.illyria.factionsx;

import io.illyria.factionsx.config.file.ConfigManager;
import io.illyria.factionsx.internal.FactionsBootstrap;
import org.bukkit.Bukkit;
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

    @Override
    public void onEnable() {
        bukkitFactionsBootstrap = this;
        initConfig();
        loadConfig();
        factionsX.enable();
    }

    @Override
    public void onDisable() {
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

    @Override
    public String getConfigFolderPath() {
        return getConfig().getCurrentPath();
    }

    public FactionsX getFactionsX() {
        return factionsX;
    }

}
