package io.illyria.factionsx.persistence.json;

import com.google.gson.reflect.TypeToken;
import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.manager.Manager;
import io.illyria.factionsx.persistence.Persistence;
import io.illyria.factionsx.utils.DiskUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public final class JsonPlayer implements Persistence<IFPlayer> {

    @Override
    public Manager<IFPlayer> getManager() {
        return FactionsX.getFactionsX().getPlayerManager();
    }

    @Override
    public Set<IFPlayer> getAll() {
        String dataText = "";

        try {
            dataText = DiskUtil.read(getDataFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ((Json) getDispatcher()).getGson().fromJson(dataText, new TypeToken<Set<IFPlayer>>(){}.getType());
    }

    @Override
    public void saveAll() {
        Set<IFPlayer> playersToSave = new HashSet<>();

        for (IFPlayer player : this.getManager().getAll()) {
            playersToSave.add(player);
        }

        try {
            DiskUtil.write(getDataFile(), ((Json) getDispatcher()).getGson().toJson(playersToSave));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public File getDataFile() {
        return new File(BukkitFactionsBootstrap.getInstance().getBootstrapDataFolder().getPath() + "/data/players.json");
    }

}