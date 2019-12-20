package io.illyria.factionsx.entity;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.core.Role;
import io.illyria.factionsx.manager.FactionManager;
import io.illyria.factionsx.utils.hooks.Econ;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class FPlayer implements IFPlayer {

    private String id;
    private String name;
    private String factionId;
    private double power;
    private int kills;
    private int deaths;
    private Role role;
    private boolean bypassing;

    public FPlayer() {
    }

    public FPlayer(Player player) {
        this.id = player.getUniqueId().toString();
        this.name = player.getName();
        this.power = 0;
        this.kills = player.getStatistic(Statistic.PLAYER_KILLS);
        this.deaths = player.getStatistic(Statistic.DEATHS);
        this.bypassing = false;
        this.factionId = "1";
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(UUID.fromString(id));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getKills() {
        return kills;
    }

    @Override
    public int getDeaths() {
        return deaths;
    }

    @Override
    public IFaction getFaction() {
        return FactionsX.getFactionsX().getFactionManager().getById(factionId);
    }

    @Override
    public void setFaction(IFaction faction) {
        this.factionId = faction.getId();
    }

    @Override
    public boolean hasFaction() {
        return !factionId.equals(0);
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
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
        if (hasMoney(amount)) {
            Econ.getEconomy().withdrawPlayer(this.getPlayer(), amount);
        }
    }

    @Override
    public boolean hasMoney(int amount) {
        if (Econ.getEconomy().getBalance(this.getPlayer()) >= amount) {
            return true;
        }
        return false;
    }

    @Override
    public double getPower() {
        return power;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public double getMinPower() {
        return 0;
    }

    @Override
    public double getMaxPower() {
        return 0;
    }

    @Override
    public boolean isBypassing() {
        return bypassing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FPlayer fPlayer = (FPlayer) o;
        return id.equals(fPlayer.id) &&
                name.equals(fPlayer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
