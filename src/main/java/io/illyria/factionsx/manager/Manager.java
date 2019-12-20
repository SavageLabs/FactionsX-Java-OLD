package io.illyria.factionsx.manager;

import java.util.Set;

public interface Manager<T> {

    Set<T> getAll();

    T getByName(String name);

    T getById(String id);

    void save();

    void load();

}
