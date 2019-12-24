package io.illyria.factionsx.command.engine.argument;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.entity.Faction;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.manager.FactionManager;

import java.util.*;
import java.util.stream.Collectors;

public class FactionArgumentType extends ArgumentType {
    @Override
    public List<String> getPossibleValues(IFPlayer fPlayer) {
        return FactionsX.getFactionsX().getFactionManager().getAll().stream().map(IFaction::getName).collect(Collectors.toList());
    }
}
