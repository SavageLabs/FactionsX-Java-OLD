package io.illyria.factionsx.config.file;

import io.illyria.factionsx.internal.FactionsBootstrap;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class CustomYamlFile extends CustomFile {

    protected YamlConfiguration config;
    protected FactionsBootstrap instance;

    public CustomYamlFile(FactionsBootstrap instance, String parent) {
        super(instance, parent, "yml");
        this.instance = instance;
        config = new YamlConfiguration();
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(this.getFile());
    }

    public void saveConfig() {
        try {
            config.save(this.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
