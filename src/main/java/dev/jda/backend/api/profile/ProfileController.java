package dev.jda.backend.api.profile;

import dev.jda.domain.models.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProfileController implements ProfileApi {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;
    private final ProfileRepresentationAssembler profileRepresentationAssembler;

    @Override
    public ProfileDTO getProfileByUuid(String uuid) {
        return profileRepresentationAssembler.toModel(profileService.getProfileByUuid(uuid));
    }

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = profileMapper.toEntity(profileDTO);
        return profileRepresentationAssembler.toModel(profileService.createProfile(profile));
    }

    @Override
    public ProfileDTO putProfileByUuid(String uuid, ProfileDTO profileDTO) {
        Profile profile = profileMapper.toEntity(profileDTO);
        return profileRepresentationAssembler.toModel(profileService.putProfileByUuid(uuid, profile));
    }

    @Override
    public ProfileDTO patchProfileByUuid(String uuid, ProfileDTO profileDTO) {
        Profile profile = profileMapper.toEntity(profileDTO);
        return profileRepresentationAssembler.toModel(profileService.patchProfileByUuid(uuid, profile));
    }

    @Override
    public void deleteProfileByUuid(String uuid) {
        profileService.deleteProfileByUuid(uuid);
    }
}
