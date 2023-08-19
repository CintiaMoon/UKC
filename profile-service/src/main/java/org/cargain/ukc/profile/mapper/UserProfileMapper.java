package org.cargain.ukc.profile.mapper;

import org.cargain.ukc.profile.domain.EducationDTO;
import org.cargain.ukc.profile.domain.ExperienceDTO;
import org.cargain.ukc.profile.domain.SkillDTO;
import org.cargain.ukc.profile.domain.UserProfileDTO;
import org.cargain.ukc.profile.model.Education;
import org.cargain.ukc.profile.model.Experience;
import org.cargain.ukc.profile.model.Skill;
import org.cargain.ukc.profile.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
	
	UserProfileMapper MAPPER = Mappers.getMapper( UserProfileMapper.class );

    UserProfileDTO toDTO(UserProfile userProfile);

    UserProfile toEntity(UserProfileDTO userProfileDTO);

    SkillDTO toDTO(Skill skill);

    Skill toEntity(SkillDTO skillDTO);

    EducationDTO toDTO(Education education);

    Education toEntity(EducationDTO educationDTO);

    ExperienceDTO toDTO(Experience experience);

    Experience toEntity(ExperienceDTO experienceDTO);
    
//    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);
//
//    @Mapping(target = "skills", source = "skillsDTO")
//    @Mapping(target = "education", source = "educationDTO")
//    @Mapping(target = "experience", source = "experienceDTO")
//    UserProfile userProfileDTOToUserProfile(UserProfileDTO userProfileDTO);
//
//    @Mapping(target = "skillsDTO", source = "skills")
//    @Mapping(target = "educationDTO", source = "education")
//    @Mapping(target = "experienceDTO", source = "experience")
//    UserProfileDTO userProfileToUserProfileDTO(UserProfile userProfile);
}
