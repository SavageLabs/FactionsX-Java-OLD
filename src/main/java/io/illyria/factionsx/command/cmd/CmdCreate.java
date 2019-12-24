package io.illyria.factionsx.command.cmd;

import io.illyria.factionsx.command.engine.CommandContext;
import io.illyria.factionsx.command.engine.CommandRequirements;
import io.illyria.factionsx.command.engine.FCommand;
import io.illyria.factionsx.command.engine.argument.Argument;
import io.illyria.factionsx.command.engine.argument.StringArgumentType;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.core.Permission;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.utils.ChatUtil;
import org.bukkit.Bukkit;

public class CmdCreate extends FCommand {

    public CmdCreate() {
        super();
        aliases.add("create");

        requiredArgs.add(new Argument("name", 0, new StringArgumentType()));

        commandRequirements = new CommandRequirements.Builder().withPermission(Permission.CREATE).asPlayer(true).build();
    }


    @Override
    public void perform(CommandContext context) {
        IFaction faction = context.getFactionManager().createFaction(context.getArgs().get(0), context.getPlayer().getUniqueId().toString());
        context.message(Message.CMD_CREATE_SUCCESS.getMessage());
        Bukkit.broadcastMessage(ChatUtil.color(String.format(Message.CMD_CREATE_ANNOUNCEMENT.getMessage(), context.getPlayer().getName(), faction.getName())));

    }

    @Override
    public String getHelpInfo() {
        return Message.CMD_CREATE_HELP.getMessage();
    }
}
