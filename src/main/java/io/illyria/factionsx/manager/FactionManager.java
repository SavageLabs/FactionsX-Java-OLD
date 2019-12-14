package io.illyria.factionsx.manager;

import io.illyria.factionsx.entity.Faction;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.persistence.Persistence;
import io.illyria.factionsx.persistence.PersistenceEngine;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class FactionManager {

    private static FactionManager factionManager;

    private Persistence<IFaction> factionPersistence = PersistenceEngine.getInstance().getFactionPersitence();
    private Set<IFaction> factions;

    private FactionManager() {
        factions = new HashSet<>();
    }

    public static FactionManager getInstance() {
        if (factionManager == null) {
            factionManager = new FactionManager();
        }
        return factionManager;
    }

    public Set<IFaction> getFactions() {
        return factions;
    }

    public IFaction getFactionByName(String name) {
        return this.getFactions().stream().parallel().filter(faction -> faction.getName().equals(name)).findFirst().orElse(null);
    }

    public IFaction getFactionById(String id) {
        return this.getFactions().stream().parallel().filter(faction -> faction.getId().equals(id)).findFirst().orElse(null);
    }

    public void loadFactions() {
       factions = factionPersistence.getAll();
    }

    public void saveFactions() {
        factionPersistence.saveAll();
    }

    public void saveFaction(IFaction faction) {
        factionPersistence.save(faction);
    }

    public void createFaction(String factionName, String ownerName) {
        IFaction faction = new Faction(generateFactionId(), factionName, ownerName);
        factions.add(faction);
    }

    private String generateFactionId() {
        return UUID.randomUUID().toString();
    }

}
