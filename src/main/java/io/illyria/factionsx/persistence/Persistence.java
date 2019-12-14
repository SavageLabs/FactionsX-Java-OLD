package io.illyria.factionsx.persistence;

import java.util.Optional;
import java.util.Set;

public interface Persistence<T> {

    Optional<T> get(String id);

    Set<T> getAll();

    void load();

    void save(final T t);

    void saveAll();

    void delete(final T t);

}
