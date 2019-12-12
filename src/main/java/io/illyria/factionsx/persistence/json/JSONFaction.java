package io.illyria.factionsx.persistence.json;

import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.persistence.Persistence;

import java.util.Optional;
import java.util.Set;

public class JSONFaction implements Persistence<IFaction> {

    @Override
    public Optional<IFaction> get(String id) {
        return Optional.empty();
    }

    @Override
    public Set<IFaction> getAll() {
        return null;
    }

    @Override
    public void load() {

    }

    @Override
    public void save(IFaction iFaction) {

    }

    @Override
    public void saveAll() {

    }

    @Override
    public void delete(IFaction iFaction) {

    }
}
