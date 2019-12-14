package io.illyria.factionsx.persistence;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.persistence.json.Json;

public final class PersistenceEngine {
    //GETTING FROM CONFIG
    private static PersistenceEngine persistenceEngine;
    private Dispatcher dispatcher;

    private Persistence<IFPlayer> fPlayerPersistence;
    private Persistence<IFaction> factionPersistence;

    private PersistenceEngine(PersistenceType persistenceType){
        switch (persistenceType) {
            case JSON:
                dispatcher = new Json();
            case MYSQL:
            case MONGODB:
        }
        setPersistences();
    }

    public static PersistenceEngine getInstance() {
        if (persistenceEngine == null) {
            persistenceEngine = new PersistenceEngine(PersistenceType.JSON);
        }
        return persistenceEngine;
    }

    public void setPersistences() {
        this.fPlayerPersistence = this.fPlayerPersistence == null ? dispatcher.getfPlayerPersistece() : this.fPlayerPersistence;
        this.factionPersistence = this.factionPersistence == null ? dispatcher.getFactionPersistence() : this.factionPersistence;
    }

    public Persistence<IFPlayer> getfPlayerPersistence() {
        return fPlayerPersistence;
    }

    public Persistence<IFaction> getFactionPersitence() { return factionPersistence; }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

}
