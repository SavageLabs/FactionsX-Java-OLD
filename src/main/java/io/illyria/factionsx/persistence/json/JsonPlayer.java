package io.illyria.factionsx.persistence.json;

import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.manager.Manager;
import io.illyria.factionsx.persistence.Persistence;

import java.util.Set;

public final class JsonPlayer implements Persistence<IFPlayer> {

    @Override
    public Manager<IFPlayer> getManager() {
        return null;
    }

    @Override
    public Set<IFPlayer> getAll() {
        return null;
    }

    @Override
    public void saveAll() {

    }

}