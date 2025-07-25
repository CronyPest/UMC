package ru.mts.media.platform.umc.dao.postgres.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;
import ru.mts.media.platform.umc.domain.event.EventSave;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventPgDao implements EventSot {

    private final EventPgRepository repository;
    private final EventPgMapper mapper;
    private final VenuePgMapper venueMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEventsByVenue(String venueReferenceId) {
        return repository.findByVenuesReferenceId(venueReferenceId)
                .stream()
                .map(mapper::asModel)
                .toList();
    }

    @Override
    public List<Venue> getVenuesWithLatestEvents(int limit) {
        return repository.findAllWithUpcomingEvents().stream()
                .map(venueMapper::asModel)
                .limit(limit)
                .toList();
    }

    @EventListener
    public void handleEventCreatedEvent(EventSave evt) {
        evt.unwrap()
                .map(mapper::asEntity)
                .ifPresent(repository::save);
    }
}
