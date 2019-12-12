package io.illyria.factionsx.internal;

public interface FactionsBootstrap {

    String getVersion();

    String getName();

    void unload();

    String getConfigFolderPath();

}
