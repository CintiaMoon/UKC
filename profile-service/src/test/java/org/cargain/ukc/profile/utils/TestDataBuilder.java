package org.cargain.ukc.profile.utils;

import java.time.LocalDate;
import java.util.Arrays;

import org.cargain.ukc.profile.domain.EducationDTO;
import org.cargain.ukc.profile.domain.ExperienceDTO;
import org.cargain.ukc.profile.domain.SkillDTO;
import org.cargain.ukc.profile.domain.UserProfileDTO;
import org.cargain.ukc.profile.model.Education;
import org.cargain.ukc.profile.model.Experience;
import org.cargain.ukc.profile.model.Skill;
import org.cargain.ukc.profile.model.UserProfile;

public class TestDataBuilder {


	public UserProfileDTO createSampleUserProfileDTO() {
		
		UserProfileDTO userProfileDTO = UserProfileDTO.builder()                
                .userId("user123")
                .bio("Experienced software developer")
                .skills(Arrays.asList(
                    new SkillDTO("Java"),
                    new SkillDTO("JavaScript")
                ))
                .education(Arrays.asList(
                    new EducationDTO("BS Computer Science", "University A", 2020),
                    new EducationDTO("MS Computer Engineering", "University B", 2022)
                ))
                .experience(Arrays.asList(
                    new ExperienceDTO("Software Engineer", "Company X", LocalDate.of(2020, 1, 1), LocalDate.of(2022, 7, 1)),
                    new ExperienceDTO("Senior Software Engineer", "Company Y", LocalDate.of(2022, 8, 1), null)
                ))
                .build();

        return userProfileDTO;
    }
		
	public UserProfile createSampleUserProfile() {
		
		UserProfile userProfile = UserProfile.builder()                
                .userId("user123")
                .bio("Experienced software developer")
                .skills(Arrays.asList(
                    new Skill("Java"),
                    new Skill("JavaScript")
                ))
                .education(Arrays.asList(
                    new Education("BS Computer Science", "University A", 2020),
                    new Education("MS Computer Engineering", "University B", 2022)
                ))
                .experience(Arrays.asList(
                    new Experience("Software Engineer", "Company X", LocalDate.of(2020, 1, 1), LocalDate.of(2022, 7, 1)),
                    new Experience("Senior Software Engineer", "Company Y", LocalDate.of(2022, 8, 1), null)
                ))
                .build();

        return userProfile;
    }
	
	public UserProfileDTO createSampleUserProfileDTOEmpty() {

		UserProfileDTO userProfileDTO = UserProfileDTO.builder()
				.userId("user123")
				.build();
		return userProfileDTO;
	}
}
