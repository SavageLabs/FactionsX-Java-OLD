package io.illyria.factionsx.entity;

import io.illyria.factionsx.core.Role;
import org.bukkit.entity.Player;

public interface IFPlayer {

    //----------------------------------//
    //   PLAYER INFO RELATED METHODS    //
    //----------------------------------//

    Player getPlayer();

    String getId();

    String getName();

    int getKills();

    int getDeaths();

    IFaction getFaction();

    void setFaction(IFaction faction);

    boolean hasFaction();

    Role getRole();

    void setRole(Role role);

    String getTitle();

    void setTitle(IFPlayer fPlayer, String title);

    void takeMoney(int amount);

    void hasMoney(int amount);

    //----------------------------------//
    //   PLAYER POWER RELATED METHODS    //
    //----------------------------------//

    double getPower();

    void setPower(double power);

    double getMinPower();

    double getMaxPower();

}
