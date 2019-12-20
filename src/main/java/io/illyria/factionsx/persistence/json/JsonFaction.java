package io.illyria.factionsx.persistence.json;

import com.google.gson.reflect.TypeToken;
import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.manager.Manager;
import io.illyria.factionsx.persistence.Persistence;
import io.illyria.factionsx.utils.DiskUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class JsonFaction implements Persistence<IFaction> {

    @Override
    public Manager<IFaction> getManager() {
        return FactionsX.getFactionsX().getFactionManager();
    }

    @Override
    public Set<IFaction> getAll() {

        String dataText = "";

        try {
            //TODO: Change this
            dataText = DiskUtil.read(new File("./data/factions.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ((Json) getDispatcher()).getGson().fromJson(dataText, new TypeToken<Set<IFaction>>(){}.getType());

    }

    @Override
    public void saveAll() {

        Set<IFaction> factionsToSave = new HashSet<>();

        for (IFaction faction : getManager().getAll()) {
            factionsToSave.add(faction);
        }

        try {
            //TODO: Change this.
            DiskUtil.write(new File("./data/factions.json"), ((Json) getDispatcher()).getGson().toJson(factionsToSave));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
