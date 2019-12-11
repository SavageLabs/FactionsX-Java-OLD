package io.illyria.factionsx.core.relation;

import java.util.Map;
import java.util.Set;

public interface RelationParticipator {

    void sendRelationRequest(RelationParticipator relationParticipator, Relation relation);

    Map<RelationParticipator, Relation> getPendingRelationRequests();

    Map<RelationParticipator, Relation> getRelations();

    Map<RelationParticipator, Relation> getRelationsWish();

    Relation getRelationTo(RelationParticipator relationParticipator);

    Set<RelationParticipator> getFactionsWithRelation(Relation relation);

    void setRelation(RelationParticipator relationParticipator, Relation relation);

    int getRelationCount();

}
