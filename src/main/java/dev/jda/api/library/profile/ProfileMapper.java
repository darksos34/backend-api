package dev.jda.api.library.profile;

import dev.jda.model.library.dto.ProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProfileMapper {

    ProfileDTO toDto(Profile profile);

    Profile toEntity(ProfileDTO dto);

}
