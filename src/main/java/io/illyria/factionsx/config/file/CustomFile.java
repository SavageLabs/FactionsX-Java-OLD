package io.illyria.factionsx.config.file;

import io.illyria.factionsx.internal.FactionsBootstrap;

import java.io.File;
import java.io.IOException;

public abstract class CustomFile implements ICustomFile {

    private File directory;
    public File file;

    private FactionsBootstrap instance;

    public CustomFile(FactionsBootstrap instance, String parent, String fileExtension) {
        this.instance = instance;
        if (!instance.getBootstrapDataFolder().exists()) {
            instance.getBootstrapDataFolder().mkdir();
        }
        if (parent != null) {
            directory = new File(instance.getBootstrapDataFolder(), File.separator + parent);
            if (!directory.exists()) {
                directory.mkdir();
            }
            file = new File(directory, getName() + "."+ fileExtension);
        } else {
            file = new File(getName() + "." + fileExtension);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getFile() {
        return file;
    }

}
