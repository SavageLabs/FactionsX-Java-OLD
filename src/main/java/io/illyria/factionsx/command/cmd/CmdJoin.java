package io.illyria.factionsx.command.cmd;

import io.illyria.factionsx.command.engine.CommandContext;
import io.illyria.factionsx.command.engine.CommandRequirements;
import io.illyria.factionsx.command.engine.FCommand;
import io.illyria.factionsx.command.engine.argument.Argument;
import io.illyria.factionsx.command.engine.argument.FactionArgumentType;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.core.Permission;
import io.illyria.factionsx.entity.IFaction;

public class CmdJoin extends FCommand {

    public CmdJoin() {
        super();
        aliases.add("join");

        requiredArgs.add(new Argument("faction-tag", 0, new FactionArgumentType()));

        this.commandRequirements = new CommandRequirements.Builder().withPermission(Permission.JOIN)
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        IFaction faction = context.getArgAsFaction(0, true);
        if (faction == null) return;
        if (!faction.getInvites().contains(context.getFPlayer())) context.message(String.format(Message.CMD_JOIN_NOTINVITED.getMessage(), faction.getName()));
        faction.getInvites().remove(context.getFPlayer());
        faction.addPlayer(context.getFPlayer());
        context.getFPlayer().setFaction(faction);
        context.message(Message.CMD_JOIN_SUCCESS.getMessage());
    }

    @Override
    public String getHelpInfo() {
        return null;
    }


}