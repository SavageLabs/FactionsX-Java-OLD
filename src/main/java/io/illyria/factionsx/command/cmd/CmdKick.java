package io.illyria.factionsx.command.cmd;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.command.engine.CommandContext;
import io.illyria.factionsx.command.engine.CommandRequirements;
import io.illyria.factionsx.command.engine.FCommand;
import io.illyria.factionsx.command.engine.argument.Argument;
import io.illyria.factionsx.command.engine.argument.PlayerArgumentType;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.entity.IFPlayer;
import org.bukkit.entity.Player;

public class CmdKick extends FCommand {

    public CmdKick() {
        super();
        aliases.add("kick");

        requiredArgs.add(new Argument("player-to-kick", 0, new PlayerArgumentType()));

        this.commandRequirements = new CommandRequirements.Builder().asFactionMember(true).build();
    }

    @Override
    public void perform(CommandContext context) {
        Player player = context.getArgAsPlayer(0, true);
        // If the player is null, they've already been informed in the getArgAsPlayer
        if (player == null) return;
        IFPlayer fPlayer = FactionsX.getFactionsX().getPlayerManager().getFPlayer(player);
        context.getFPlayer().getFaction().kickPlayer(fPlayer);
        context.message(String.format(Message.CMD_KICK_SUCCESS.getMessage(), player.getName()));
    }

    @Override
    public String getHelpInfo() {
        return Message.CMD_KICK_HELP.getMessage();
    }

}