package io.illyria.factionsx.config.file;

import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.config.file.types.ConfigFile;
import io.illyria.factionsx.config.file.types.MessageFile;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private BukkitFactionsBootstrap plugin;
    private static Map<String, CustomFile> fileMap = new HashMap<>();

    public ConfigManager(BukkitFactionsBootstrap plugin) {
        this.plugin = plugin;
        addFile(new MessageFile(plugin));
        addFile(new ConfigFile(plugin));
    }

    private void addFile(CustomFile file) {
        fileMap.put(file.getName(), file);
        file.init();
    }

    public static Map<String, CustomFile> getFileMap() {
        return fileMap;
    }

}