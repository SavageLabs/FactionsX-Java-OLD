package io.illyria.factionsx.utils.hooks;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class HookManager {

    private static HookManager hookManager;
    private FactionsBootstrap plugin;

    private Set<String> enabledHooks;

    private HookManager(FactionsBootstrap plugin) {
        enabledHooks = new HashSet<>();
        this.plugin = plugin;
    }

    public static HookManager getInstance() {
        if (hookManager == null) {
            hookManager = new HookManager(FactionsX.getFactionsX().getFactionsBootstrap());
        }
        return hookManager;
    }

    public void loadHooks() {
        Bukkit.getScheduler().runTaskLater((JavaPlugin) plugin, () -> {
            // Load hooks with a delay, because plugins sometimes load before us even if
            // they are in the softdepend list, don't know why. This way we're 100% sure.

            // PlaceholderAPI hook - adds placeholders
            if (checkHook("PlaceholderAPI")) {
                new PlaceholderAPIHook(plugin).register();
            }

            // Economy Hook (Vault), try to hook even if the Econ is disabled in config
            // so that if the user enables it after the plugin is loaded, it will work
            // without restarting the server.
            if (checkHook("Vault")) {
                if (!Econ.setup(plugin)) {
                    enabledHooks.remove("Vault");
                    if (Config.USE_ECONOMY.getBoolean()) {
                        ChatUtil.error(Message.ERROR_ECON_INVALID.getMessage());
                    } else {
                        ChatUtil.debug(Message.ERROR_ECON_INVALID.getMessage());
                    }
                }
            }

            // Essentials Hook, try to hook even if the Econ is disabled in config
            // so that if the user enables it after the plugin is loaded, it will work
            // without restarting the server.
            if (checkHook("Essentials")) {
                if (!EssentialsHook.setup(plugin)) {
                    enabledHooks.remove("Essentials");
                    if (Config.HOOK_ESSENTIALS.getBoolean()) {
                        ChatUtil.error(Message.ERROR_ESSENTIALS_INVALID.getMessage());
                    } else {
                        ChatUtil.debug(Message.ERROR_ESSENTIALS_INVALID.getMessage());
                    }
                }
            }

            if (!enabledHooks.isEmpty())
                ChatUtil.sendConsole(Message.PREFIX.getMessage() + "&e" + Bukkit.getName() + " Hooked to: &f" + enabledHooks.toString().replaceAll("\\[\\]", ""));

        }, 2);
    }

    private boolean checkHook(String pluginName) {
        if (Bukkit.getPluginManager().isPluginEnabled(pluginName)) {
            enabledHooks.add(pluginName);
            return true;
        } else {
            ChatUtil.debug(Message.ERROR_HOOK_FAILED.getMessage().replace("{plugin}", pluginName));
        }
        return false;
    }

    public Set<String> getEnabledHooks() {
        return enabledHooks;
    }

}
