package io.illyria.factionsx.config.file.types;

import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.config.file.CustomFile;
import io.illyria.factionsx.internal.FactionsBootstrap;

public class ConfigFile extends CustomFile {

    private FactionsBootstrap instance;

    public ConfigFile(FactionsBootstrap instance) {
        super(instance, "");
        this.instance = instance;
        for (Config message : Config.values()) {
            if (message.getStrings() != null) {
                for (String string : message.getStrings()) {
                    getConfig().addDefault(message.getConfig(), message.getStrings());
                }
            } else if (message.getString() != null) {
                getConfig().addDefault(message.getConfig(), message.getString());
            } else if (message.getInt() != null) {
                getConfig().addDefault(message.getConfig(), message.getInt());
            } else if (message.getDouble() != null) {
                getConfig().addDefault(message.getConfig(), message.getDouble());
            } else {
                getConfig().addDefault(message.getConfig(), message.getBoolean());
            }
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public ConfigFile init() {
        this.reloadConfig();
        for (Config message : Config.values()) {
            if (message.getStrings() != null) {
                message.setStrings(getConfig().getStringList(message.getConfig()));
            } else if (message.getInt() != null) {
                message.setInt(getConfig().getInt(message.getConfig()));
            } else if (message.getDouble() != null) {
                message.setDouble(getConfig().getDouble(message.getConfig()));
            } else if (message.getString() != null) {
                message.setString(getConfig().getString(message.getConfig()));
            } else {
                message.setBoolean(getConfig().getBoolean(message.getConfig()));
            }
        }
        return this;
    }

    @Override
    public String getName() {
        return "config";
    }
}