package io.illyria.factionsx.command.engine;

import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.core.Permission;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandRequirements {

    private Permission permission;
    private boolean asPlayer;
    private boolean asFactionMember;

    public CommandRequirements(Permission permission, boolean asPlayer, boolean asFactionMember) {
        this.permission = permission;
        this.asPlayer = asPlayer;
        this.asFactionMember = asFactionMember;
    }


    public boolean computeRequirements(CommandContext context, boolean informIfNot) {
        if (asPlayer && !context.isPlayer()) {
            if (informIfNot) context.message(Message.COMMAND_REQUIREMENTS_NOTPLAYER.getMessage());
            return false;
        }

        // We have permission checks below; so we can just give console go ahead.
        if (context.getCommandSender() instanceof ConsoleCommandSender) return true;

        // Assume its player since we approved console above.
        Player player = (Player) context.getCommandSender();
        if (permission != null && !Permission.hasPermission(player, permission, true)) {
            context.message(Message.GENERAL_NOPERMISSION_SPECIFIC.getMessage().replace("{perm}", permission.getFullPermissionNode()));
            return false;
        }

        if (asFactionMember) {
            if (context.getFPlayer() == null || !context.getFPlayer().hasFaction()) {
                if (informIfNot) context.message(Message.COMMAND_REQUIREMENTS_NOTFACTIONMEMBER.getMessage());
            }
            return false;
        }
        // They passed the check!
        return true;
    }

    public static class Builder {


        private Permission permission;
        private boolean asPlayer;
        private boolean asFactionMember;

        public Builder withPermission(Permission permission) {
            this.permission = permission;
            return this;
        }

        public Builder asPlayer(boolean asPlayer) {
            this.asPlayer = asPlayer;
            return this;
        }

        public Builder asFactionMember(boolean asFactionMember) {
            this.asFactionMember = asFactionMember;
            return this;
        }

        public CommandRequirements build() {
            return new CommandRequirements(permission, asPlayer, asFactionMember);
        }

    }


}
