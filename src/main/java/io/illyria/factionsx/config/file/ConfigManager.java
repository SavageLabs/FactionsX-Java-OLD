package io.illyria.factionsx.config.file;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.config.file.types.ConfigFile;
import io.illyria.factionsx.config.file.types.MessageFile;
import io.illyria.factionsx.internal.FactionsBootstrap;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static ConfigManager configManager;
    private FactionsBootstrap plugin;

    private static Map<String, CustomFile> fileMap = new HashMap<>();

    private ConfigManager(FactionsBootstrap plugin) {
        this.plugin = plugin;
        addFile(new MessageFile(plugin));
        addFile(new ConfigFile(plugin));
    }

    public static ConfigManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigManager(FactionsX.getFactionsX().getFactionsBootstrap());
        }
        return configManager;
    }

    private void addFile(CustomFile file) {
        fileMap.put(file.getName(), file);
        file.init();
    }

    public Map<String, CustomFile> getFileMap() {
        return fileMap;
    }

}