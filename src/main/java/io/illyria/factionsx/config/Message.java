package io.illyria.factionsx.config;

import io.illyria.factionsx.utils.ChatUtil;

import java.util.List;

public enum Message {

    PREFIX("General.prefix", "&4&lFactionsX &f➤ "),
    PREFIX_DEBUG("General.prefix-debug", "&7[FactionsX] &e<DEBUG> "),
    PREFIX_ERROR("General.prefix-error", "&7[FactionsX] &c<ERROR> "),

    GENERAL_NOPERMISSION("General.no-permission", "&7[&4✕&7] &cYou don't have permissions!"),
    GENERAL_NOPERMISSION_SPECIFIC("General.no-permission-specific", "&7[&4✕&7] &cYou must have the permission &e{perm}&c in order to do this!"),

    COMMAND_PARSING_PLAYERNOTFOUND("Command-parsing.player-not-found", "&7[&4✕&7] &cPlayer was not found."),
    COMMAND_PARSING_CANNOTREFERENCEYOURSELF("Command-parsing.cannot-reference-yourself","&7[&4✕&7] &cYou cannot reference yourself."),
    COMMAND_PARSING_ARGISNOTINT("Command-parsing.arg-is-not-int", "&7[&4✕&7] &cThe specified argument is not an integer."),

    CMD_RELOAD_SUCCESS("Command.reload-success", "&7[&a✔&7] &aConfig and Messages Reloaded!"),

    ERROR_BACKEND_INVALID("Database.invalid-type-selected", "&7[&4✕&7] &cTHAT DATABASE TYPE DOES NOT EXIST! &eSetting Default Database type... &cCHECK &eCONFIG.YML&c FILE."),

    ERROR_HOOK_FAILED("Hooks.generic-hook-failed", "&7[&4✕&7] &cCould not hook to {plugin}. {plugin} support is disabled"),
    ERROR_ECON_INVALID("Hooks.economy-hook-failed", "&7[&4✕&7] &cEconomy support has been disabled! Error while hooking to Vault, or no Economy Service was found!"),
    ERROR_ESSENTIALS_INVALID("Hooks.essentials-hook-failed", "&7[&4✕&7] &cEssentials support has been disabled! Error while hooking to Essentials, or you may have an outdated version! &eOnly EssentialsX is supported, you can download it from here -> https://www.spigotmc.org/resources/essentialsx.9089/"),
    ERROR_DYNMAP_INVALID("Hooks.dynmap-hook-failed", "&7[&4✕&7] &cDynmap support has been disabled! Error while trying to get DynmapAPI"),
    ERROR_PAPI_INVALID("Hooks.placeholder-generic-error", "&c&oError"),

    TIME_DAYS("Time.days", "days"),
    TIME_DAY("Time.day", "day"),
    TIME_HOURS("Time.hours", "hours"),
    TIME_HOUR("Time.hour", "hour"),
    TIME_MINUTES("Time.minutes", "minutes"),
    TIME_MINUTE("Time.minute", "minute"),
    TIME_SECONDS("Time.seconds", "seconds"),
    TIME_SECOND("Time.second", "second");

    String config, message;
    String[] messages;

    Message(String config, String message) {
        this.config = config;
        this.message = message;
    }

    Message(String config, String[] messages) {
        this.config = config;
        this.messages = messages;
    }

    public String getConfig() {
        return config;
    }

    public String getMessage() {
        return message;
    }

    public String[] getMessages() {
        return this.messages;
    }

    public void setMessages(List<String> list) {
        this.messages = list.toArray(new String[0]);
    }

    public void setMessage(String message) {
        this.message = ChatUtil.color(message);
    }

}
