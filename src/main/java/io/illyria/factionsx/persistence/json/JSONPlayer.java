package io.illyria.factionsx.persistence.json;

import io.illyria.factionsx.core.FPlayer;
import io.illyria.factionsx.core.MemoryFPlayer;
import io.illyria.factionsx.persistence.Persistence;

import java.util.Optional;
import java.util.Set;

public class JSONPlayer implements Persistence<FPlayer> {

    @Override
    public Optional<FPlayer> get(String id) {
        return Optional.empty();
    }

    @Override
    public Set<FPlayer> getAll() {
        return null;
    }

    @Override
    public void load() {

    }

    @Override
    public void save(FPlayer fPlayer) {

    }

    @Override
    public void saveAll() {

    }

    @Override
    public void delete(FPlayer fPlayer) {

    }
}