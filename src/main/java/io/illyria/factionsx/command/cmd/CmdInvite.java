package io.illyria.factionsx.command.cmd;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.command.engine.CommandContext;
import io.illyria.factionsx.command.engine.CommandRequirements;
import io.illyria.factionsx.command.engine.FCommand;
import io.illyria.factionsx.command.engine.argument.Argument;
import io.illyria.factionsx.command.engine.argument.PlayerArgumentType;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.core.Permission;
import io.illyria.factionsx.entity.IFPlayer;
import org.bukkit.entity.Player;

public class CmdInvite extends FCommand {

    public CmdInvite() {
        super();
        aliases.add("invite");

        requiredArgs.add(new Argument("player-to-invite", 0, new PlayerArgumentType()));
        

        this.commandRequirements = new CommandRequirements.Builder().withPermission(Permission.INVITE).asFactionMember(true)
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        Player player = context.getArgAsPlayer(0, true);
        // If the player is null, they've already been informed in the getArgAsPlayer
        if (player == null) return;
        IFPlayer fPlayer = FactionsX.getFactionsX().getPlayerManager().getFPlayer(context.getPlayer());
        context.getFPlayer().getFaction().invitePlayer(fPlayer);
        context.message(Message.CMD_INVITE_SUCCESS.getMessage());
    }

    @Override
    public String getHelpInfo() {
        return Message.CMD_INVITE_HELP.getMessage();
    }


}