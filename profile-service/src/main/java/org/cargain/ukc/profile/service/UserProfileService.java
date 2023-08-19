package org.cargain.ukc.profile.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cargain.ukc.profile.domain.UserProfileDTO;
import org.cargain.ukc.profile.exception.UserProfileNotFoundException;
import org.cargain.ukc.profile.mapper.UserProfileMapper;
import org.cargain.ukc.profile.model.UserProfile;
import org.cargain.ukc.profile.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

	private final UserProfileRepository userProfileRepository;
	
	public UserProfileDTO createUserProfile(UserProfileDTO userProfileDto) {
        validateUserProfile(userProfileDto);       
        
        UserProfile userProfile = UserProfileMapper.MAPPER.toEntity( userProfileDto );

        userProfile = userProfileRepository.save(userProfile);

        return UserProfileMapper.MAPPER.toDTO(userProfile);
    }

    public UserProfileDTO updateUserProfile(String profileId, UserProfileDTO userProfileDto) {
    	getUserProfileById(profileId);
        validateUserProfile(userProfileDto);

        UserProfile userProfile = UserProfileMapper.MAPPER.toEntity(userProfileDto);
        userProfile = userProfileRepository.save(userProfile);

        return UserProfileMapper.MAPPER.toDTO(userProfile);
    }
    
    public UserProfileDTO getUserProfileById(String profileId) {
        UserProfile userProfile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found with ID: " + profileId));
        return UserProfileMapper.MAPPER.toDTO(userProfile);
    }

    public List<UserProfileDTO> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        return userProfiles.stream()
                .map(UserProfileMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteUserProfile(String profileId) {
    	getUserProfileById(profileId);
        userProfileRepository.deleteById(profileId);
    }

    private void validateUserProfile(UserProfileDTO userProfile) {
        if (userProfile.getBio() == null || userProfile.getBio().isEmpty()) {
            throw new IllegalArgumentException("Bio cannot be empty");
        }

    }
}
