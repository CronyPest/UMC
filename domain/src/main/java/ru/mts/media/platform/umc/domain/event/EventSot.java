package ru.mts.media.platform.umc.domain.event;

import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;
import java.util.Optional;

public interface EventSot {

    Optional<Event> getEventById(String id);
    List<Event> getEventsByVenue(String venueReferenceId);
    List<Venue> getVenuesWithLatestEvents(int limit);
}
