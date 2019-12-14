package io.illyria.factionsx.core;

import io.illyria.factionsx.config.Config;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public enum Permission {
    EXAMPLE("example", "just an example placeholder", PermissionDefault.FALSE);


    private final String node;
    private final String description;
    private final PermissionDefault permissionDefault;

    private Permission(String node, String description, PermissionDefault permissionDefault) {

        this.node = node;
        this.description = description;
        this.permissionDefault = permissionDefault;
    }


    public String getFullPermissionNode() {
        return Config.PERMISSION_ROOT_NAME + "." + this.node;
    }

    public static void registerAllPermissions(PluginManager pluginManager) {
        Arrays.stream(Permission.values()).forEach(
                permission ->
                        pluginManager.addPermission(new org.bukkit.permissions.Permission(permission.getFullPermissionNode(), permission.description, permission.permissionDefault))
        );
    }

    public static boolean hasPermission(Permissible permissible, Permission permission) {
        return permissible.hasPermission(permission.getFullPermissionNode());
    }

    public static boolean hasPermission(Permissible permissible, String permission) {
        return permissible.hasPermission(permission);
    }

    public static int getMaxPermission(Permissible permissible, Permission permission) {
        if (permissible.isOp()) return -1;

        String fullBaseNode = permission.getFullPermissionNode();

        // Atomic cuz values need to be final to be accessed from lambda so they need to be effectively final.
        final AtomicInteger max = new AtomicInteger();

        permissible.getEffectivePermissions().stream()
                .map(PermissionAttachmentInfo::getPermission)
                .map(String::toLowerCase)
                .filter(perm -> perm.startsWith(fullBaseNode))
                .map(perm -> perm.replace(fullBaseNode + ".", ""))
                .forEach(value -> {

                    // If the value is *, then its basically infinity
                    if (value.equalsIgnoreCase("*")) {
                        max.set(-1);
                        return;
                    }

                    // Other foreach set it to -1.
                    if (max.get() == -1) return;

                    try {
                        // get int from name.
                        int amount = Integer.parseInt(value);

                        // check if its bigger than one we found.
                        if (amount > max.get()) {
                            max.set(amount);
                        }
                    } catch (NumberFormatException ex) {
                        // ignore :D
                    }
                });
        return max.get();
    }


}
