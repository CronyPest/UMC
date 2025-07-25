package ru.mts.media.platform.umc.domain.event;

import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;

public interface EventSot {

    List<Event> getEventsByVenue(String venueReferenceId);
    List<Venue> getVenuesWithLatestEvents(int limit);
}
