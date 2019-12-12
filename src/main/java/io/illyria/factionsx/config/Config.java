package io.illyria.factionsx.config;

import java.util.Arrays;
import java.util.List;

public enum Config {

    DEBUG("Debug", false),

    PERMISSION_ADMIN("Permissions.perm-admin", "factionsx.admin"),

    ENABLED_WORLDS("GeneralSettings.enabled-worlds", new String[]{
            "world1",
            "world2"
    }),

    USE_ECONOMY("GeneralSettings.use-economy", true),
    USE_PARTICLES("GeneralSettings.use-particles", true),
    USE_HOLOGRAMS("GeneralSettings.use-holograms", true),
    USE_ANIMATIONS("GeneralSettings.use-animations", true),
    IGNORE_VANISHED("GeneralSettings.ignore-vanished", true);


    String config, message;
    Boolean bool;
    String[] messages;
    Integer number;
    Double dnumber;

    Config(String config, String message) {
        this.config = config;
        this.message = message;
    }

    Config(String config, String[] messages) {
        this.config = config;
        this.messages = messages;
    }

    Config(String config, Boolean bool) {
        this.config = config;
        this.bool = bool;
    }

    Config(String config, Integer number) {
        this.config = config;
        this.number = number;
    }

    Config(String config, Double dnumber) {
        this.config = config;
        this.dnumber = dnumber;
    }

    public boolean getBoolean() {
        return bool;
    }

    public String getConfig() {
        return config;
    }

    public String getString() {
        return message;
    }

    public Double getDouble() {
        return dnumber;
    }

    public Integer getInt() {
        return number;
    }

    public String[] getStrings() {
        return this.messages;
    }

    public List<String> getStringList() {
        return Arrays.asList(this.messages);
    }

    public void setInt(int number) {
        this.number = number;
    }

    public void setDouble(double dnumber) {
        this.dnumber = dnumber;
    }

    public void setStrings(List<String> list) {
        this.messages = list.toArray(new String[0]);
    }

    public void setString(String message) {
        this.message = message;
    }

    public void setBoolean(Boolean bool) {
        this.bool = bool;
    }
}
