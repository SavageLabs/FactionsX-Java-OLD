package io.illyria.factionsx.entity;

import io.illyria.factionsx.core.relation.RelationParticipator;
import io.illyria.factionsx.core.Role;

import java.util.Set;

public interface IFaction extends RelationParticipator {

    //----------------------------------//
    //   FACTION INFO RELATED METHODS   //
    //----------------------------------//

    String getId();

    String getName();

    long getFoundedDate();

    String getDescription();

    void setDescription(String description);

    Set<String> getRules();

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

    void setPower(double power);

    void setMaxPower(double maxPower);

    boolean isSystemFaction();
    //TODO: Warp, Home, Claims stuff

    //-----------------------------//
    //   INVITES RELATED METHODS   //
    //-----------------------------//

    void invitePlayer(IFPlayer player);

    void uninvitePlayer(IFPlayer player);

    Set<IFPlayer> getInvites();

    boolean isInvited(IFPlayer player);

    //--------------------------------//
    //   MODERATION RELATED METHODS   //
    //--------------------------------//

    void kickPlayer(IFPlayer player);

    void banPlayer(IFPlayer player);

    void mutePlayer(IFPlayer player);

    void unmutePlayer(IFPlayer player);

    void muteAllPlayers();

    void unmuteAllPlayers();

    void unbanPlayer(IFPlayer player);

    boolean isBanned(IFPlayer player);

    Set<IFPlayer> getBannedPlayers();

    //--------------------------------//
    //   PLAYER RELATED METHODS       //
    //--------------------------------//

    void addPlayer(IFPlayer player);

    void removePlayer(IFPlayer player);

    Set<IFPlayer> getPlayers();

    int getMemberSize();

    int getMaxMemberSize();

    void setMaxMemberSize(int memberSize);

    IFPlayer getLeader();

    Set<IFPlayer> getPlayersWithRole(Role role);

    void setPlayerRole(IFPlayer player, Role role);

}
