package io.illyria.factionsx.manager;

import io.illyria.factionsx.persistence.PersistenceEngine;

public abstract class AbstractManager<T> implements Manager<T> {

    protected PersistenceEngine persistenceEngine = PersistenceEngine.getInstance();

}
