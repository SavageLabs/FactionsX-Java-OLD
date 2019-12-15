package io.illyria.factionsx.command.argument;

import io.illyria.factionsx.entity.IFPlayer;

import java.util.Collections;
import java.util.List;

public class IntArgument extends ArgumentType {
    @Override
    public List<String> getPossibleValues(IFPlayer fPlayer) {
        return Collections.singletonList("1");
    }
}
