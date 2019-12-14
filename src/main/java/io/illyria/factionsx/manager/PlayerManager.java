package io.illyria.factionsx.manager;

import io.illyria.factionsx.entity.FPlayer;
import io.illyria.factionsx.entity.IFPlayer;
import io.illyria.factionsx.persistence.Persistence;
import io.illyria.factionsx.persistence.PersistenceEngine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public final class PlayerManager {

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

        IFPlayer fPlayer = null;

        for (IFPlayer player : fPlayers) {
            if (player.getId().equals(playerId)) {
                fPlayer = player;
            }
        }

        if (fPlayer == null) {
            fPlayer = createFplayer(Bukkit.getPlayer(playerId));
            fPlayers.add(fPlayer);
        }

        return fPlayer;

    }

    public IFPlayer getFPlayer(Player player) {
        return getFPlayerById(player.getUniqueId().toString());
    }

    private IFPlayer createFplayer(Player player) {
        IFPlayer fPlayer = new FPlayer(player);
        fPlayers.add(fPlayer);
        return fPlayer;
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
