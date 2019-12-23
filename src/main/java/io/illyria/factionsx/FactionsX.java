package io.illyria.factionsx;

import io.illyria.factionsx.config.file.ConfigManager;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.manager.FactionManager;
import io.illyria.factionsx.manager.PlayerManager;
import io.illyria.factionsx.persistence.PersistenceEngine;

/**
 * Main plugin class, all managers will be loaded here,
 * we do not include here any bukkit implementation.
 * Bukkit implementation goes in the implementing Bootstrap class.
 */

public final class FactionsX {

    private static FactionsX factionsX;

    private PersistenceEngine persistenceEngine;
    private FactionsBootstrap factionsBootstrap;

    private PlayerManager playerManager;
    private FactionManager factionManager;
    private ConfigManager configManager;

    public FactionsX(FactionsBootstrap factionsBootstrap) {
        this.factionsBootstrap = factionsBootstrap;
        factionsX = this;
    }

    public void enable() {
        configManager = ConfigManager.getInstance();
        persistenceEngine = PersistenceEngine.getInstance();
        playerManager = new PlayerManager();
        factionManager = new FactionManager();

        factionManager.load();
        playerManager.load();
    }

    public void disable() {
    }

    public FactionsBootstrap getFactionsBootstrap() {
        return factionsBootstrap;
    }

    public PersistenceEngine getPersistenceEngine() {
        return persistenceEngine;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public FactionManager getFactionManager() {
        return factionManager;
    }

    public static FactionsX getFactionsX() {
        return factionsX;
    }

    public ConfigManager getConfigManager() {
        return configManager != null ? configManager : ConfigManager.getInstance();
    }
}

