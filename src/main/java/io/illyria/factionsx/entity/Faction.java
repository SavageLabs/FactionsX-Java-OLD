package io.illyria.factionsx.entity;

import io.illyria.factionsx.core.relation.RelationParticipator;
import io.illyria.factionsx.core.Role;

import java.util.Date;
import java.util.Set;

public interface Faction extends RelationParticipator {

    //----------------------------------//
    //   FACTION INFO RELATED METHODS   //
    //----------------------------------//

    String getId();

    String getName();

    Date getFoundedDate();

    String getDescription();

    void setDescription();

    String getRules();

    void addRule(String rule);

    void removeRule(String rule);

    void removeRules();

    boolean getOpen();

    void setOpen(boolean open);

    Set<String> getAnnouncements();

    void addAnouncement(String announcementMsg);

    void removeAnnouncement(String announcementId);

    void removeAnnouncements();

    double getPower();

    void setPower();

    void setMaxPower();

    boolean isSystemFaction();
    //TODO: Warp, Home stuff

    //-----------------------------//
    //   INVITES RELATED METHODS   //
    //-----------------------------//

    void invitePlayer(FPlayer player);

    void deinvitePlayer(FPlayer player);

    Set<String> getInvites();

    boolean isInvited(FPlayer player);

    //--------------------------------//
    //   MODERATION RELATED METHODS   //
    //--------------------------------//

    void kickPlayer(FPlayer player);

    void banPlayer(FPlayer player);

    void unbanPlayer(FPlayer player);

    boolean isBanned(FPlayer player);

    Set<FPlayer> getBannedPlayers();

    //--------------------------------//
    //   PLAYER RELATED METHODS       //
    //--------------------------------//

    void addPlayer(FPlayer player);

    void removePlayer(FPlayer player);

    Set<FPlayer> getPlayers();

    FPlayer getLeader();

    Set<FPlayer> getPlayersWithRole(Role role);

    void setPlayerRole(FPlayer player, Role role);

}
