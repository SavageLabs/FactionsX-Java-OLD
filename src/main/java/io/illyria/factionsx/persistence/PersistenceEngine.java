package io.illyria.factionsx.persistence;

import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.persistence.json.Json;
import io.illyria.factionsx.utils.ChatUtil;

public final class PersistenceEngine {
    //GETTING FROM CONFIG
    private static PersistenceEngine persistenceEngine;
    private Dispatcher dispatcher;

    private Persistence<IFPlayer> fPlayerPersistence;
    private Persistence<IFaction> factionPersistence;

    private PersistenceEngine(PersistenceType persistenceType) {
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
            PersistenceType persistenceType;
            try {
                persistenceType = PersistenceType.valueOf(Config.BACKEND_TYPE.getString());
            } catch (IllegalArgumentException exception) {
                ChatUtil.error(Message.ERROR_BACKEND_INVALID.getMessage().replace("{type}", Config.BACKEND_TYPE.getString()));
                persistenceType = PersistenceType.JSON;
            }
            persistenceEngine = new PersistenceEngine(persistenceType);
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

    public Persistence<IFaction> getFactionPersitence() {
        return factionPersistence;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

}
