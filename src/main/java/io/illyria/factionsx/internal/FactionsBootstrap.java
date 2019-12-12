package io.illyria.factionsx.internal;

import java.io.File;

public interface FactionsBootstrap {

    String getVersion();

    String getName();

    void unload();

    File getBootstrapDataFolder();

}
