package io.illyria.factionsx.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.persistence.Dispatcher;
import io.illyria.factionsx.persistence.PersistenceEngine;

public final class Json implements Dispatcher {

    private PersistenceEngine persistenceEngine = FactionsX.getFactionsX().getPersistenceEngine();
    private Gson gson;

    public Json() {
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        setPersistence();
    }

    @Override
    public void setPersistence() {
        persistenceEngine.setFPlayerPersistence(new JsonPlayer());
        persistenceEngine.setFactionPersistence(new JsonFaction());
    }

    public Gson getGson() {
        return gson;
    }
}
