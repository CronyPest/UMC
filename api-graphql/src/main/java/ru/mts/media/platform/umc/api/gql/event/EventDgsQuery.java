package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventDomainService;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsQuery {
    private final EventDomainService domainService;

    @DgsQuery
    public List<Event> eventsByVenue(@InputArgument String venueReferenceId) {
        return domainService.getEventsByVenue(venueReferenceId);
    }

    @DgsQuery
    public List<Venue> venuesWithLatestEvents(@InputArgument Integer limit) {
        return domainService.getVenuesWithLatestEvents(limit != null ? limit : 5);
    }
}
