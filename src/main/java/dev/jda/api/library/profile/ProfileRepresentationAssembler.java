package dev.jda.api.library.profile;

import dev.jda.model.library.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
@NullMarked
public class ProfileRepresentationAssembler
        implements RepresentationModelAssembler<Profile, ProfileDTO> {

    private final ProfileMapper profileMapper;

    @Override
    public ProfileDTO toModel(Profile profile) {

        ProfileDTO dto = profileMapper.toDto(profile);

        dto.add(
                linkTo(
                        methodOn(ProfileController.class)
                                .getProfileByUuid(profile.getUuid())
                ).withSelfRel()
        );

        return dto;
    }
}