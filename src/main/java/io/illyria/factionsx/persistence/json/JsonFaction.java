package io.illyria.factionsx.persistence.json;

import com.google.gson.reflect.TypeToken;
import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.manager.Manager;
import io.illyria.factionsx.persistence.Persistence;
import io.illyria.factionsx.utils.DiskUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public final class JsonFaction implements Persistence<IFaction> {

    @Override
    public Manager<IFaction> getManager() {
        return FactionsX.getFactionsX().getFactionManager();
    }

    @Override
    public Set<IFaction> getAll() {
        String dataText = "";

        try {
            dataText = DiskUtil.read(getDataFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ((Json) getDispatcher()).getGson().fromJson(dataText, new TypeToken<Set<IFaction>>(){}.getType());
    }

    @Override
    public void saveAll() {
        Set<IFaction> factionsToSave = new HashSet<>();

        for (IFaction faction : this.getManager().getAll()) {
            factionsToSave.add(faction);
        }

        try {
            DiskUtil.write(getDataFile(), ((Json) getDispatcher()).getGson().toJson(factionsToSave));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public File getDataFile() {
        return new File(BukkitFactionsBootstrap.getInstance().getBootstrapDataFolder().getPath() + "/data/factions.json");
    }

}
