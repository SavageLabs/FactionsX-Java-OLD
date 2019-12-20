package io.illyria.factionsx.config.file;

import io.illyria.factionsx.internal.FactionsBootstrap;

import java.io.File;
import java.io.IOException;

public abstract class CustomFile implements ICustomFile {

    public File file;
    public File configFile;
    private FactionsBootstrap instance;

    public CustomFile(FactionsBootstrap instance, String parent, String fileExtension) {
        this.instance = instance;
        if (!instance.getBootstrapDataFolder().exists()) {
            instance.getBootstrapDataFolder().mkdir();
        }
        if (parent != null) {
            file = new File(instance.getBootstrapDataFolder(), File.separator + parent);
            if (!file.exists()) {
                file.mkdir();
            }
            configFile = new File(file, getName() + "."+ fileExtension);
        } else {
            configFile = new File(getName() + "." + fileExtension);
        }
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getConfigFile() {
        return configFile;
    }

}
