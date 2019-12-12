package io.illyria.factionsx.config.file;

import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.internal.FactionsBootstrap;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class CustomFile implements ICustomFile {

    private YamlConfiguration config;
    private File file;
    private File configFile;

    public CustomFile(FactionsBootstrap instance, String parent) {
        if (!instance.getBootstrapDataFolder().exists()) {
            instance.getBootstrapDataFolder().mkdir();
        }
        if (parent != null) {
            file = new File(instance.getBootstrapDataFolder(), File.separator + parent);
            if (!file.exists()) {
                file.mkdir();
            }
            configFile = new File(file, getName() + ".yml");
        } else {
            configFile = new File(getName() + ".yml");
        }
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public YamlConfiguration getConfig() {
        return config;
    }


}
