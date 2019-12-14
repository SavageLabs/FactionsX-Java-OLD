package io.illyria.factionsx.config;

import java.util.Arrays;
import java.util.List;

public enum Config {

    FACTION_DEFAULT_DESCRIPTION("faction.default.description", "This is your Faction default description"),
    FACTION_DEFAULT_OPEN("faction.default.open", false),
    FACTION_DEFAULT_STARTINGPOWER("faction.default.startingpower", 0.0),
    FACTION_DEFAULT_MAXPOWER("faction.default.maxpower", -1.0),
    FACTION_DEFAULT_MAXMEMBERSIZE("faction.default.maxmembersize", 30),

    DEBUG("Debug", false),

    PERMISSION_ROOT_NAME("Permissions.root-name", "factionsx"),

    PERMISSION_ADMIN("Permissions.perm-admin", "factionsx.admin"),

    ENABLED_WORLDS("GeneralSettings.enabled-worlds", new String[]{
            "world1",
            "world2"
    }),

    USE_ECONOMY("Economy.enabled", true),
    PRICE_HOME_CREATE("Economy.price-home-creation", 0.0),
    PRICE_HOME_TELEPORT("Economy.price-home-teleport", 0.0),

    USE_PARTICLES("GeneralSettings.use-particles", true),
    USE_HOLOGRAMS("GeneralSettings.use-holograms", true),
    USE_ANIMATIONS("GeneralSettings.use-animations", true),

    HOOK_ESSENTIALS("Hooks.essentials-compatibility.enabled", true),
    HOOK_ESSENTIALS_PASSTELEPORT("Hooks.essentials-compatibility.pass-home-teleport-handling", true),
    HOOK_DYNMAP("Hooks.dynmap-compatibility.enabled", true),


    VANISH_IGNORE("Vanish.ignore-vanished-players", true),
    VANISH_CHECK_CANSEE("Vanish.use-cansee-check", false);


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
