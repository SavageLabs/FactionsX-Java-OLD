package io.illyria.factionsx.config.file;

import java.io.File;

public interface ICustomFile {

    ICustomFile init();

    String getName();

    File getFile();

}
