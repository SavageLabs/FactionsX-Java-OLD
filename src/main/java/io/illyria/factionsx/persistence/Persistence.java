package io.illyria.factionsx.persistence;

import java.util.Optional;
import java.util.Set;

public interface Persistence<T> {

    Optional<T> get(String id);

    Set<T> getAll();

    void load();

    void save(T t);

    void saveAll();

    void delete(T t);

}
