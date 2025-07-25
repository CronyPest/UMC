package ru.mts.media.platform.umc.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventDomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final EventSot sot;
    private final VenueSot venueSot;

    public Event save(SaveEventInput input) {
        Venue venue = venueSot.getVenueByReferenceId(input.getVenueReferenceId())
                .orElseThrow(() -> new IllegalArgumentException("Venue not found"));

        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setName(input.getName());
        event.setStartTime(input.getStartTime());
        event.setEndTime(input.getEndTime());
        event.setVenues(List.of(venue));

        eventPublisher.publishEvent(new EventSave(event));
        return event;
    }

    public List<Event> getEventsByVenue(String venueReferenceId) {
        return sot.getEventsByVenue(venueReferenceId);
    }

    public List<Venue> getVenuesWithLatestEvents(int limit) {
        return sot.getVenuesWithLatestEvents(limit);
    }
}
