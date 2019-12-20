package io.illyria.factionsx.persistence.json;

import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.manager.Manager;
import io.illyria.factionsx.persistence.Persistence;

import java.io.File;

public final class JsonFaction implements Persistence<IFaction> {

    @Override
    public Manager<IFaction> getManager() {
        return FactionsX.getFactionsX().getFactionManager();
    }

    @Override
    public File getDataFile() {
        return new File(BukkitFactionsBootstrap.getInstance().getBootstrapDataFolder().getPath() + "/data/factions.json");
    }

}
