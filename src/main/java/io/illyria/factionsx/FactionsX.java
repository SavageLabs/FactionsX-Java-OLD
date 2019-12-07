package io.illyria.factionsx;

import io.illyria.factionsx.persistence.PersistenceEngine;

public class FactionsX {

    private static FactionsX INSTANCE;

    private PersistenceEngine persistenceEngine;

    public FactionsX() {
        persistenceEngine = PersistenceEngine.getInstance();
    }

    public PersistenceEngine getPersistenceEngine() {
        return persistenceEngine;
    }

    public static FactionsX getInstance() {
        return INSTANCE;
    }

}
