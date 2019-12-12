package io.illyria.factionsx;

import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.manager.FactionManager;
import io.illyria.factionsx.manager.PlayerManager;
import io.illyria.factionsx.persistence.PersistenceEngine;

/**
 * Main plugin class, all managers will be loaded here,
 * we do not include here any bukkit implementation.
 * Bukkit implementation goes in the implementing Bootstrap class.
 */

public class FactionsX {

    private static FactionsX factionsX;

    private PersistenceEngine persistenceEngine;
    private FactionsBootstrap factionsBootstrap;

    private PlayerManager playerManager;
    private FactionManager factionManager;

    public FactionsX(FactionsBootstrap factionsBootstrap) {
        this.factionsBootstrap = factionsBootstrap;
        factionsX = this;
    }

    public void enable() {
        persistenceEngine = PersistenceEngine.getInstance();
        playerManager = PlayerManager.getInstance();
        factionManager = FactionManager.getInstance();
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
}

