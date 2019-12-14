package io.illyria.factionsx.persistence;

import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.entity.IFaction;

/**
 * Going to be used for JSON, Mongo, SQL implementation.
 */
public abstract class Dispatcher {

    protected Persistence<IFPlayer> fPlayerPersistece;
    protected Persistence<IFaction> factionPersistence;

    public Persistence<IFaction> getFactionPersistence() {
        return factionPersistence;
    }

    public Persistence<IFPlayer> getfPlayerPersistece() {
        return fPlayerPersistece;
    }
}

