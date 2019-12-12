package io.illyria.factionsx;

import io.illyria.factionsx.internal.FactionsBootstrap;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Here is the bukkit implementation of Factions plugin.
 * Anything related to bukkit goes here.
 */

public class BukkitFactionsBootstrap extends JavaPlugin implements FactionsBootstrap {

    private static BukkitFactionsBootstrap bukkitFactionsBootstrap;

    private FactionsX factionsX = new FactionsX(this);

    @Override
    public void onEnable() {
        bukkitFactionsBootstrap = this;
        factionsX.enable();
    }

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

    @Override
    public String getConfigFolderPath() {
        return getConfig().getCurrentPath();
    }

    public FactionsX getFactionsX() {
        return factionsX;
    }

}
