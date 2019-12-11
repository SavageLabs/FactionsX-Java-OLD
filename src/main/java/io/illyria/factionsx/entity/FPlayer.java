package io.illyria.factionsx.entity;

import io.illyria.factionsx.core.Role;
import org.bukkit.entity.Player;

public class FPlayer implements IFPlayer {
    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getKills() {
        return 0;
    }

    @Override
    public int getDeaths() {
        return 0;
    }

    @Override
    public IFaction getFaction() {
        return null;
    }

    @Override
    public void setFaction(IFaction faction) {

    }

    @Override
    public boolean hasFaction() {
        return false;
    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    public void setRole(Role role) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(IFPlayer fPlayer, String title) {

    }

    @Override
    public void takeMoney(int amount) {

    }

    @Override
    public void hasMoney(int amount) {

    }

    @Override
    public double getPower() {
        return 0;
    }

    @Override
    public void setPower(double power) {

    }

    @Override
    public double getMinPower() {
        return 0;
    }

    @Override
    public double getMaxPower() {
        return 0;
    }
}
