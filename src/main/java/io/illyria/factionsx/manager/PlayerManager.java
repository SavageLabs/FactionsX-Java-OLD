package io.illyria.factionsx.manager;

import io.illyria.factionsx.entity.Faction;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.entity.IFaction;
import io.illyria.factionsx.persistence.PersistenceEngine;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    private Set<IFPlayer> fPlayers;

    public PlayerManager() {
        fPlayers = new HashSet<>();
    }

    public Set<IFPlayer> getFPlayers() {
        return fPlayers;
    }

    public IFPlayer getFPlayerByName(String playerName) {
        for (IFPlayer player : fPlayers) {

            if (player.getName().equals(playerName)) {
                return player;
            }

        }
        return null;
    }

    public IFPlayer getFPlayerById(String playerId) {
        for (IFPlayer player : fPlayers) {

            if (player.getName().equals(playerId)) {
                return player;
            }

        }
        return null;
    }

    public void loadPlayers() {
        fPlayers = PersistenceEngine.getInstance().getfPlayerPersistence().getAll();
    }

    public void savePlayers() {
        PersistenceEngine.getInstance().getfPlayerPersistence().saveAll();
    }

    public void savePlayer(IFPlayer player) {
        PersistenceEngine.getInstance().getfPlayerPersistence().save(player);
    }


    public void createFaction(String factionName, String ownerName) {
        IFaction faction = new Faction(factionName, ownerName);
    }


}
