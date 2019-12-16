package io.illyria.factionsx.config.file;

import io.illyria.factionsx.internal.FactionsBootstrap;

import java.io.File;

public abstract class CustomJsonFile extends CustomFile {

    private FactionsBootstrap instance;

    public CustomJsonFile(FactionsBootstrap instance, String parent) {
        super(instance, parent, "json");
        this.instance = instance;
    }

    public File getConfigFile() {
        return configFile;
    }

}
