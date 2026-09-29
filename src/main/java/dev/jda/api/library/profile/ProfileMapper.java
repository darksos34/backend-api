package dev.jda.api.library.profile;

import dev.jda.model.library.dto.ProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProfileMapper {

    /**
     * Converts a {@link Profile} entity to a {@link ProfileDTO}.
     * <p>
     * Note: The {@code user} field in {@link ProfileDTO} is ignored by default.
     * If {@link ProfileDTO} expects a {@link String} identifier (e.g. username or code),
     * uncomment and adjust the mapping accordingly:
     * <pre>{@code
     * @Mapping(target = "user", source = "user.name")
     * }</pre>
     *
     * @param profile the {@link Profile} entity to map from
     * @return the mapped {@link ProfileDTO}, or {@code null} if the source profile is null
     */
    @Mapping(target = "user", ignore = true)
    ProfileDTO toDto(Profile profile);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "type", ignore = true)
    Profile toEntity(ProfileDTO dto);
}
