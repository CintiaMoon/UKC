package org.cargain.ukc.profile.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.cargain.ukc.profile.domain.UserProfileDTO;
import org.cargain.ukc.profile.exception.UserProfileNotFoundException;
import org.cargain.ukc.profile.mapper.UserProfileMapper;
import org.cargain.ukc.profile.model.UserProfile;
import org.cargain.ukc.profile.repository.UserProfileRepository;
import org.cargain.ukc.profile.utils.TestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserProfileServiceTest {

	@InjectMocks
    private UserProfileService userProfileService;

    @Mock
    private UserProfileRepository userProfileRepository;
    
    private static final  String PROFILE_ID = "sampleId";
    
    private TestDataBuilder tdb = new TestDataBuilder();
    
   
    @Test
    public void testCreateUserProfile() {
        // Given
        UserProfileDTO userProfileDTO = tdb.createSampleUserProfileDTO();
        UserProfile userProfile = UserProfileMapper.MAPPER.toEntity(userProfileDTO);

        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);

        // When
        UserProfileDTO createdUserProfile = userProfileService.createUserProfile(userProfileDTO);

        // Then
        assertNotNull(createdUserProfile);
        assertEquals(userProfileDTO.getBio(), createdUserProfile.getBio());
    }
    
    @Test
    public void testCreateUserProfile_error_no_bio() {
        // Given
        UserProfileDTO userProfileDTO = tdb.createSampleUserProfileDTOEmpty();
              
        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
        userProfileService.createUserProfile(userProfileDTO));

        // Then
        assertEquals("Bio cannot be empty", exception.getMessage());        
    }
    
    @Test
    public void testGetUserProfileById() {
        // Given
        UserProfile userProfile = tdb.createSampleUserProfile();
        
        when(userProfileRepository.findById(PROFILE_ID)).thenReturn(Optional.of(userProfile));

        // When
        UserProfileDTO retrievedUserProfile = userProfileService.getUserProfileById(PROFILE_ID);

        // Then
        assertNotNull(retrievedUserProfile);
        assertEquals(userProfile.getBio(), retrievedUserProfile.getBio());
        // Add more assertions as needed
    }
    
    @Test
    public void testGetAllUserProfiles() {
        // Given
        List<UserProfile> userProfiles = Arrays.asList(tdb.createSampleUserProfile(), tdb.createSampleUserProfile());
        
        when(userProfileRepository.findAll()).thenReturn(userProfiles);

        // When
        List<UserProfileDTO> retrievedUserProfiles = userProfileService.getAllUserProfiles();

        // Then
        assertNotNull(retrievedUserProfiles);
        assertEquals(userProfiles.size(), retrievedUserProfiles.size());
        // Add more assertions as needed
    }
    
    @Test
    public void testUpdateUserProfile() {
        // Given 
        UserProfileDTO userProfileDTO = tdb.createSampleUserProfileDTO();
        UserProfile existingUserProfile = tdb.createSampleUserProfile();
        
        when(userProfileRepository.findById(PROFILE_ID)).thenReturn(Optional.of(existingUserProfile));
        when(userProfileRepository.save(any(UserProfile.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        UserProfileDTO updatedUserProfile = userProfileService.updateUserProfile(PROFILE_ID, userProfileDTO);

        // Then
        assertNotNull(updatedUserProfile);
        assertEquals(userProfileDTO.getBio(), updatedUserProfile.getBio());
        // Add more assertions as needed
    }
    
    @Test
    public void testUpdateUserProfile_error_no_user() {
        // Given 
        UserProfileDTO userProfileDTO = tdb.createSampleUserProfileDTO();
        
        when(userProfileRepository.findById(PROFILE_ID)).thenReturn(Optional.empty());
                
        // When
        assertThrows(UserProfileNotFoundException.class, () ->
        userProfileService.updateUserProfile(PROFILE_ID, userProfileDTO));

        verify(userProfileRepository, times(1)).findById(PROFILE_ID);
      
    }
    
    @Test
    public void testDeleteUserProfile() {
        // Given
        UserProfile userProfile = tdb.createSampleUserProfile();
        
        when(userProfileRepository.findById(PROFILE_ID)).thenReturn(Optional.of(userProfile));
        doNothing().when(userProfileRepository).deleteById(PROFILE_ID);

        // When
        userProfileService.deleteUserProfile(PROFILE_ID);

        // Then
        verify(userProfileRepository, times(1)).deleteById(PROFILE_ID);
    }

	
}
