package ru.mts.media.platform.umc.dao.postgres.event;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;

import java.util.List;

public interface EventPgRepository extends JpaRepository<EventPgEntity, String> {

    @EntityGraph(attributePaths = "venues")
    List<EventPgEntity> findByVenuesReferenceId(String venueReferenceId);

    @Query("SELECT v FROM VenuePgEntity v JOIN FETCH v.events e WHERE e.startTime > CURRENT_TIMESTAMP ORDER BY e.startTime ASC")
    List<VenuePgEntity> findAllWithUpcomingEvents();
}
