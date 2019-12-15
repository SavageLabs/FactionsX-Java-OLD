package io.illyria.factionsx.command;

import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.core.Permission;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandRequirements {

    private final Permission permission;
    private final boolean asPlayer;
    private final boolean asFactionMember;
    private final boolean withRole;

    public CommandRequirements(Permission permission, boolean asPlayer, boolean asFactionMember, boolean withRole) {
        this.permission = permission;
        this.asPlayer = asPlayer;
        this.asFactionMember = asFactionMember;
        this.withRole = withRole;
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
        if (permission != null && !Permission.hasPermission(player, permission, !informIfNot)) {
          return false;
        }

        if (asFactionMember) {
            if (context.getFPlayer() == null || !context.getFPlayer().hasFaction()) {
                if (informIfNot) context.message(Message.COMMAND_REQUIREMENTS_NOTFACTIONMEMBER.getMessage());
            }
        }




    }


}
