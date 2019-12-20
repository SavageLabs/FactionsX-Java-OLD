package io.illyria.factionsx.persistence;

import com.google.gson.reflect.TypeToken;
import io.illyria.factionsx.manager.Manager;
import io.illyria.factionsx.persistence.json.Json;
import io.illyria.factionsx.utils.DiskUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public interface Persistence<T> {

    Manager<T> getManager();

    default Dispatcher getDispatcher() {
        return PersistenceEngine.getInstance().getDispatcher();
    }

    default Set<T> getAll() {

        String dataText = "";

        try {
            dataText = DiskUtil.read(getDataFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ((Json) getDispatcher()).getGson().fromJson(dataText, new TypeToken<Set<T>>(){}.getType());

    }

    default void saveAll() {

        Set<T> typeToSave = new HashSet<>();

        for (T t : this.getManager().getAll()) {
            typeToSave.add(t);
        }

        try {
            DiskUtil.write(getDataFile(), ((Json) getDispatcher()).getGson().toJson(typeToSave));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    File getDataFile();

}
