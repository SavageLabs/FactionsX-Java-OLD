package io.illyria.factionsx.persistence;

import io.illyria.factionsx.manager.Manager;

import java.util.Optional;
import java.util.Set;

public interface Persistence<T> {

    Manager<T> getManager();

    default Dispatcher getDispatcher() {
        return PersistenceEngine.getInstance().getDispatcher();
    }

    Set<T> getAll();

    void saveAll();

}
