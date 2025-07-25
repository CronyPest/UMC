package ru.mts.media.platform.umc.domain.event;

import org.mapstruct.*;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, builder = @Builder(disableBuilder = true))
public interface EventDomainServiceMapper {

    @Mapping(target = "venues", source = "venues")
    Event toEvent(SaveEventInput input, List<Venue> venues);

    @AfterMapping
    default void afterMapping(@MappingTarget Event event) {
        event.setId(UUID.randomUUID().toString());
    }
}
