package io.illyria.factionsx.command.engine;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.entity.FPlayer;
import io.illyria.factionsx.entity.Faction;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.manager.FactionManager;
import io.illyria.factionsx.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandContext {

    private CommandSender commandSender;
    private List<String> args;
    private String aliasUsed;
    private Player player;
    private IFPlayer fPlayer;
    private IFaction faction;

    public CommandContext(CommandSender commandSender, List<String> args, String aliasUsed) {
        this.commandSender = commandSender;
        this.args = args;
        this.aliasUsed = aliasUsed;
        if (commandSender instanceof Player) { this.player = (Player) commandSender; }
        if (commandSender instanceof FPlayer && this.player != null) { this.fPlayer = FactionsX.getFactionsX().getPlayerManager().getFPlayer(player); }
        if (commandSender instanceof FPlayer && this.player != null) { this.faction = this.fPlayer.getFaction(); }
    }

    public boolean isBypassing() {
        return this.fPlayer.isBypassing();
    }

    public Player getArgAsPlayer(int index, boolean informIfNot) {
        // Using match player in case of typos etc.
        List<Player> players = Bukkit.matchPlayer(args.get(index));
        if (players.size() == 0 && informIfNot) { this.message(Message.COMMAND_PARSING_PLAYERNOTFOUND.getMessage()); }
        return players.get(0);
    }

    public IFPlayer getArgAsFPlayer(int index, boolean cannotReferenceYourSelf, boolean informIfNot) {
        Player player = getArgAsPlayer(index, informIfNot);
        if (cannotReferenceYourSelf && player == this.player) {
            this.message(Message.COMMAND_PARSING_CANNOTREFERENCEYOURSELF.getMessage());
        }
        return FactionsX.getFactionsX().getPlayerManager().getFPlayer(player);
    }

    public IFaction getArgAsFaction(int index, boolean informIfNot) {
        IFaction faction = getFactionManager().getByName(getArgs().get(index));
        if (faction == null && informIfNot) this.message(Message.COMMAND_PARSING_FACTIONNOTFOUND.getMessage());
        return faction;
    }

    // Uses boxed so user can actually check if the result is null.
    public Integer getArgAsInt(int index, boolean informIfNot) {
        try {
            return Integer.parseInt(args.get(index));
        } catch (NumberFormatException ex) {
            if (informIfNot) this.message(Message.COMMAND_PARSING_ARGISNOTINT.getMessage());
        }
        return null;
    }

    // Didnt use return Boolean.getBoolean(args.get(0)); on purpose.
    // We need to know if the value is even an actual boolean, not just if they wrote true.
    // We also need to know if it was parsed correctly or not, so we used a boxed value.
    public Boolean getArgAsBoolean(int index, boolean informIfNot) {
        String rawBoolean = args.get(index).toLowerCase();
        if (rawBoolean.equals("true") || rawBoolean.equals("1")) { return true; }
        if (rawBoolean.equals("false") || rawBoolean.equals("0")) { return false; }
        return null;
    }

    public FactionManager getFactionManager() {
        return FactionsX.getFactionsX().getFactionManager();
    }


    public boolean isPlayer() {
        return player != null;
    }

    public void message(String message) {
        commandSender.sendMessage(ChatUtil.color(message));
    }

    public List<String> getArgs() { return this.args; }

    public IFPlayer getFPlayer() { return this.fPlayer; }

    public IFaction getFaction() { return this.fPlayer.getFaction(); }

    public Player getPlayer() { return this.player; }

    public CommandSender getCommandSender() { return this.commandSender; }

}
