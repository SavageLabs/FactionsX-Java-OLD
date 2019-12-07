package io.illyria.factionsx.persistence;
import io.illyria.factionsx.FPlayer;
import io.illyria.factionsx.persistence.json.JSON;

public class PersistenceEngine {
    //GETTING FROM CONFIG
    private static PersistenceEngine persistenceEngine = new PersistenceEngine(PersistenceType.JSON);

    private Dispatcher dispatcher;

    private Persistence<FPlayer> fPlayerPersistence;

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

    public void setFPlayerPersistence(Persistence<FPlayer> fPlayerPersistence) {
        this.fPlayerPersistence = this.fPlayerPersistence == null ? fPlayerPersistence : this.fPlayerPersistence;
    }

    protected Persistence<FPlayer> getfPlayerPersistence() {
        return fPlayerPersistence;
    }

    protected Dispatcher getDispatcher() {
        return dispatcher;
    }

}
