package io.illyria.factionsx.persistence.json;

import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.persistence.Persistence;

import java.util.Optional;
import java.util.Set;

public class JSONPlayer implements Persistence<IFPlayer> {

    @Override
    public Optional<IFPlayer> get(String id) {
        return Optional.empty();
    }

    @Override
    public Set<IFPlayer> getAll() {
        return null;
    }

    @Override
    public void load() {

    }

    @Override
    public void save(IFPlayer fPlayer) {

    }

    @Override
    public void saveAll() {

    }

    @Override
    public void delete(IFPlayer fPlayer) {

    }
}