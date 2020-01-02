package io.illyria.factionsx.manager;

import io.illyria.factionsx.entity.FPlayer;
import io.illyria.factionsx.entity.IFPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class PlayerManager extends AbstractManager<IFPlayer> {

    private Set<IFPlayer> fPlayers;

    public PlayerManager() {
        fPlayers = new HashSet<>();
    }

    @Override
    public Set<IFPlayer> getAll() {
        return fPlayers;
    }

    @Override
    public IFPlayer getByName(String name) {
        for (IFPlayer player : fPlayers) {

            if (player.getName().equals(name)) {
                return player;
            }

        }
        return null;
    }

    @Override
    public IFPlayer getById(String id) {
        IFPlayer fPlayer = null;

        for (IFPlayer player : fPlayers) {
            if (player.getId().equals(id)) {
                fPlayer = player;
            }
        }

        if (fPlayer == null) {
            fPlayer = createFplayer(Bukkit.getPlayer(UUID.fromString(id)));
            fPlayers.add(fPlayer);
        }

        return fPlayer;
    }

    private IFPlayer createFplayer(Player player) {
        IFPlayer fPlayer = new FPlayer(player);
        fPlayers.add(fPlayer);
        return fPlayer;
    }

    @Override
    public void save() {
        persistenceEngine.getfPlayerPersistence().saveAll();
    }

    @Override
    public void load() {
        fPlayers = persistenceEngine.getfPlayerPersistence().getAll();
    }

    public IFPlayer getFPlayer(Player player) {
        if (player == null) return null;
        return getById(player.getUniqueId().toString());
    }

}
