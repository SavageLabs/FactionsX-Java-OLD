package io.illyria.factionsx.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.illyria.factionsx.persistence.Dispatcher;

public final class Json extends Dispatcher {

    private Gson gson;

    public Json() {
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        this.fPlayerPersistece = new JsonPlayer();
        this.factionPersistence = new JsonFaction();
    }

    public Gson getGson() {
        return gson;
    }
}
