package io.illyria.factionsx.utils.hooks;


import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.config.Message;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private BukkitFactionsBootstrap plugin;

    public PlaceholderAPIHook(BukkitFactionsBootstrap plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "factionsx";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    public static void unreg(PlaceholderExpansion exp) {
        PlaceholderAPI.unregisterExpansion(exp);
    }

    @Override
    public String onRequest(OfflinePlayer p, String s) {
        if (s.equalsIgnoreCase("version")) {
            return getVersion();
        }
        return Message.PAPI_ERROR.getMessage();
    }

}