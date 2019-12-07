package io.illyria.factionsx.persistence.json;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.persistence.Dispatcher;
import io.illyria.factionsx.persistence.PersistenceEngine;

public class JSON implements Dispatcher {

    private PersistenceEngine persistenceEngine = FactionsX.getInstance().getPersistenceEngine();

    @Override
    public void setPersistence() {
        persistenceEngine.setFPlayerPersistence(new JSONPlayer());
    }
}
