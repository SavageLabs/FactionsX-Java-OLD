package io.illyria.factionsx.manager;

import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.persistence.Persistence;
import io.illyria.factionsx.persistence.PersistenceEngine;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    private static PlayerManager playerManager;

    private Set<IFPlayer> fPlayers;
    private Persistence<IFPlayer> playerPersistence = PersistenceEngine.getInstance().getfPlayerPersistence();

    private PlayerManager() {
        fPlayers = new HashSet<>();
    }

    public static PlayerManager getInstance() {
        if (playerManager == null) {
            playerManager = new PlayerManager();
        }
        return playerManager;
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

    public IFPlayer getFPlayer(Player player) {
        if (player == null) return null;
        return getFPlayerByName(player.getName());
    }

    public void loadPlayers() {
        fPlayers = playerPersistence.getAll();
    }

    public void savePlayers() {
        playerPersistence.saveAll();
    }

    public void savePlayer(IFPlayer player) {
        playerPersistence.save(player);
    }

}
