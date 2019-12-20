package io.illyria.factionsx.command.engine.argument;

import io.illyria.factionsx.entity.IFPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerArgumentType extends ArgumentType {

    @Override
    public List<String> getPossibleValues(IFPlayer fPlayer) {
        return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
    }
}
