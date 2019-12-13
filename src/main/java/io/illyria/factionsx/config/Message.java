package io.illyria.factionsx.config;

import io.illyria.factionsx.utils.ChatUtil;

import java.util.List;

public enum Message {

    PREFIX("general.prefix", "&4&lFactionsX &f➤ "),
    PREFIX_DEBUG("general.prefix-debug", "&7[FactionsX] &e<DEBUG> "),
    PREFIX_ERROR("general.prefix-error", "&7[FactionsX] &c<ERROR> "),

    GENERAL_NOPERMISSION("general.no-permission", "&7[&4✕&7] &cYou don't have permissions!"),
    GENERAL_NOPERMISSION_SPECIFIC("general.no-permission-specific", "&7[&4✕&7] &cYou must have the permission &e{perm}&c in order to do this!"),

    COMMAND_PARSING_PLAYERNOTFOUND("command-parsing.player-not-found", "&7[&4✕&7] &cPlayer was not found."),
    COMMAND_PARSING_CANNOTREFERENCEYOURSELF("command-parsing.cannot-reference-your-self","&7[&4✕&7] &cYou cannot reference yourself."),
    COMMAND_PARSING_ARGISNOTINT("command-parsing.arg-is-not-int", "&7[&4✕&7] &cThe specified argument is not an integer."),

    CMD_RELOAD_SUCCESS("command.reload-success", "&7[&a✔&7] &aConfig and Messages Reloaded!"),

    ERROR_HOOK_FAILED("hooks.failed-to-hook", "&7[&4✕&7] &cCould not hook to {plugin}. {plugin} support is disabled"),
    ERROR_ECON_INVALID("hooks.economy-hook-failed", "&7[&4✕&7] &cEconomy support has been disabled! Error while hooking to Vault, or no Economy Service was found!"),
    ERROR_ESSENTIALS_INVALID("hooks.essentials-hook-failed", "&7[&4✕&7] &cEssentials support has been disabled! Error while hooking to Essentials, or you may have an outdated version! &eOnly EssentialsX is supported, you can download it from here -> https://www.spigotmc.org/resources/essentialsx.9089/"),
    ERROR_DYNMAP_INVALID("hooks.dynmap-hook-failed", "&7[&4✕&7] &cDynmap support has been disabled! Error while trying to get DynmapAPI"),

    PAPI_ERROR("placeholder-error", "&c&oerror"),

    TIME_DAYS("time.days", "days"),
    TIME_DAY("time.day", "day"),
    TIME_HOURS("time.hours", "hours"),
    TIME_HOUR("time.hour", "hour"),
    TIME_MINUTES("time.minutes", "minutes"),
    TIME_MINUTE("time.minute", "minute"),
    TIME_SECONDS("time.seconds", "seconds"),
    TIME_SECOND("time.second", "second");

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
