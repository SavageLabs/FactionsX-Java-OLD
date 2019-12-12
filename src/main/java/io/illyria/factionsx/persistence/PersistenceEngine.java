package io.illyria.factionsx.persistence;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.persistence.json.JSON;

public class PersistenceEngine {
    //GETTING FROM CONFIG
    private static PersistenceEngine persistenceEngine = new PersistenceEngine(PersistenceType.JSON);
    private Dispatcher dispatcher;

    private Persistence<IFPlayer> fPlayerPersistence;
    private Persistence<IFaction> factionPersistence;

    private PersistenceEngine(PersistenceType persistenceType){
        switch (persistenceType) {
            case JSON:
                dispatcher = new JSON();
            case MYSQL:
            case MONGODB:
        }
    }

    public static PersistenceEngine getInstance() {
        if (persistenceEngine == null) {
            persistenceEngine = new PersistenceEngine(PersistenceType.JSON);
        }
        return persistenceEngine;
    }

    public void setFPlayerPersistence(Persistence<IFPlayer> fPlayerPersistence) {
        this.fPlayerPersistence = this.fPlayerPersistence == null ? fPlayerPersistence : this.fPlayerPersistence;
    }

    public void setFactionPersistence(Persistence<IFaction> factionPersistence) {
        this.factionPersistence = this.factionPersistence == null ? factionPersistence : this.factionPersistence;
    }

    public Persistence<IFPlayer> getfPlayerPersistence() {
        return fPlayerPersistence;
    }

    public Persistence<IFaction> getFactionPersitence() { return factionPersistence; }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

}
