package dev.jda.backend.api.profile;

import dev.jda.domain.models.ProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDTO toDto(Profile profile) {
        if (profile == null) {
            return null;
        }

        return ProfileDTO.builder()
                .uuid(profile.getUuid())
                .name(profile.getName())
                .code(profile.getCode())
                .build();
    }

    public Profile toEntity(ProfileDTO dto) {
        if (dto == null) {
            return null;
        }

        return Profile.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .code(dto.getCode())
                .build();
    }
}
