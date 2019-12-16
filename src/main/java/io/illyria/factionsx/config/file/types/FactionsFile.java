package io.illyria.factionsx.config.file.types;

import io.illyria.factionsx.config.file.CustomJsonFile;
import io.illyria.factionsx.internal.FactionsBootstrap;

public class FactionsFile extends CustomJsonFile {

    private FactionsBootstrap instance;

    public FactionsFile(FactionsBootstrap instance) {
        super(instance, "data");
        this.instance = instance;
    }

    @Override
    public Object init() {
        return this;
    }

    @Override
    public String getName() {
        return "factions";
    }
}
