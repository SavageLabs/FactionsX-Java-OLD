package io.illyria.factionsx.utils.hooks;

import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.internal.FactionsBootstrap;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Econ {

    private static Economy econ = null;

    public static boolean setup(FactionsBootstrap plugin) {
        // Just to be safe, return true if it's already setup.
        if (econ != null) return true;
        // Just to be safe, return false if the plugin is not enabled.
        if (((JavaPlugin) plugin).getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = ((JavaPlugin) plugin).getServer().getServicesManager().getRegistration(Economy.class);
        // No economy provider found, like Essentials, CMI, TNE...
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        // this should never be null, but keep the check for safety.
        return econ != null;
    }

    public static boolean isEnabled() {
        return Config.USE_ECONOMY.getBoolean() && econ != null && econ.isEnabled();
    }

    public static boolean isSetup() {
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
