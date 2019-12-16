package io.illyria.factionsx.config.file.types;

import io.illyria.factionsx.config.file.CustomJsonFile;
import io.illyria.factionsx.internal.FactionsBootstrap;

public class PlayersFile extends CustomJsonFile {

    private FactionsBootstrap instance;

    public PlayersFile(FactionsBootstrap instance) {
        super(instance, "data");
        this.instance = instance;
    }

    @Override
    public Object init() {
        return this;
    }

    @Override
    public String getName() {
        return "players";
    }
}
