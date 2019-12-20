package io.illyria.factionsx.persistence.json;

import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.manager.Manager;
import io.illyria.factionsx.persistence.Persistence;

import java.io.File;

public final class JsonPlayer implements Persistence<IFPlayer> {

    @Override
    public Manager<IFPlayer> getManager() {
        return FactionsX.getFactionsX().getPlayerManager();
    }

    @Override
    public File getDataFile() {
        return new File(BukkitFactionsBootstrap.getInstance().getBootstrapDataFolder().getPath() + "/data/players.json");
    }

}